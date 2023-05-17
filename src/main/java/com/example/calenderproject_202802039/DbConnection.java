package com.example.calenderproject_202802039;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/calenderapp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "180302";

    public static Connection getConnection() throws SQLException {
        try {

            return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }

}
