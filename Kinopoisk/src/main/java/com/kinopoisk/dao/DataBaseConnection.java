package com.kinopoisk.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by alexander on 27.02.16.
 */
public class DataBaseConnection {
    private static final String address = "jdbc:postgresql://localhost:5454/webcp";
    private static final String username = "postgres";
    private static final String password = "qwerty";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(address, username, password);
    }
}
