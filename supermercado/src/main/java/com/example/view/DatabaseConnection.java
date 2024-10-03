package com.example.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() {
        try {
            String url = "jdbc:postgresql://localhost:5432/supermercado";
            String user = "postgres";
            String password = "postgres";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
