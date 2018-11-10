package db;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DatabaseHelper implements Closeable {

    private static DatabaseHelper instance;

    private Connection connection;

    private DatabaseHelper() throws Exception {
        String[] details = getLoginInfo();
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("jdbc:mysql://" + details[0] + ":3306/" + details[1]);
        connection = DriverManager.getConnection("jdbc:mysql://" + details[0] + ":3306/" + details[1], details[2], details[3]);
    }

    public static DatabaseHelper instance() throws Exception {
        if(instance == null) {
            instance = new DatabaseHelper();
        }
        return instance;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    private static String[] getLoginInfo() throws IOException {
        String[] data = new String[4];
        List<String> lines = Files.readAllLines(new File("mysqlconnection.txt").toPath());
        if(lines.size() < 4) throw new IOException("Invalid login Info");

        for(int i = 0; i < 4; i++) data[i] = lines.get(i);
        return data;
    }
}
