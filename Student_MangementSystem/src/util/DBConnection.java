package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Viraj@0074";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}