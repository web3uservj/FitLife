package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DBConnection {

    public static Connection getConnection() throws SQLException {
        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("database.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find database.properties");
                return null;
            }
            prop.load(input);
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("username");
            String password = prop.getProperty("password");

            Class.forName(driver); // This can be moved to a static initializer block to run once

            return DriverManager.getConnection(url, user, password);

        } catch (IOException ex) {
            System.out.println("Error reading database properties");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC Driver not found");
            ex.printStackTrace();
        }
        return null;
    }
}
