package com.flyinggeese.emergencynexus;

import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ConnectToDatabase {
    private Connection crunchifyConn = null;
    private PreparedStatement crunchifyPrepareStat = null;

    public void makeJDBCConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Congrats - Seems your MySQL JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            System.out.println("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
            e.printStackTrace();
            return;
        }

        try {
            // DriverManager: The basic service for managing a set of JDBC drivers.
            File file = new File("database-properties.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String dbPass = "";
            dbPass = br.readLine();

            crunchifyConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/emergency_nexus?user=root?autoReconnect=true&allowPublicKeyRetrieval=true&useSSL=false", "root", dbPass);

            if (crunchifyConn != null) {
                System.out.println("Connection Successful! Enjoy. Now it's time to push data");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("MySQL Connection Failed!");
            e.printStackTrace();
            return;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Connection  getConnection() {
        return crunchifyConn;
    }
}
