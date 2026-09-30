package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// JDBC connection provider - URL/USER/PASSWORD ikkada change cheyyi
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
