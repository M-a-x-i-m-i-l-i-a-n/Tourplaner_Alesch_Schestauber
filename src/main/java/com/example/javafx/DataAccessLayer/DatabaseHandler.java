package com.example.javafx.DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.example.javafx.config.Configuration;

public class DatabaseHandler {
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    private static DatabaseHandler instance;
    Configuration db = Configuration.getInstance();
    private DatabaseHandler() {
        URL = db.get("DBUrl");
        USER = db.get("USER");
        PASSWORD = db.get("PASSWORD");
    }

    public static DatabaseHandler getInstance() {
        if (DatabaseHandler.instance == null) {
            DatabaseHandler.instance = new DatabaseHandler();
        }
        return DatabaseHandler.instance;
    }


    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
