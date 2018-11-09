import auth.AuthenticationHelper;

public class Tests {

    public static void main(String[] args) throws Exception {
//        AuthenticationHelper.addUserWithPassword(1, "Alice", "password");
        System.out.println(AuthenticationHelper.authenticate("alice", "password1"));
    }
}
