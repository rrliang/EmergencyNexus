package com.flyinggeese.emergencynexus;

import javafx.scene.control.Alert;

import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ConnectToDatabase {
    private Connection crunchifyConn = null;
    private PreparedStatement crunchifyPrepareStat = null;


    public void makeJDBCConnection() throws IOException {

        FileWriter myWriter = new FileWriter("log.txt",true);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            myWriter.write(formatter.format(date) + " MySQL JDBC Driver Registered is working as expected.\n");
        } catch (ClassNotFoundException e) {
            Alert jdbcErr = new Alert(Alert.AlertType.ERROR,"ERR:  The JDBC driver could not be used to load the database. \n" +
                    "Please contact a system admin to resolve this problem.");
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            myWriter.write(formatter.format(date) + " Couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly.\n");
            jdbcErr.showAndWait();
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
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());
                myWriter.write(formatter.format(date) + " Connection successful.\n");
            } else {
                Alert jdbcErr = new Alert(Alert.AlertType.ERROR,"ERR: Failed to make connection to database.\n" +
                        "Check to see if the correct MySQL root password is inputted in the database-properties.txt.");
                jdbcErr.showAndWait();
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());
                myWriter.write(formatter.format(date) + " Failed to make database connection.\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            myWriter.write(formatter.format(date) + " There is an error in the database\n");
            return;
        } catch (FileNotFoundException e) {
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            myWriter.write(formatter.format(date) + " Failed to find database-properties.txt file.\n");
            e.printStackTrace();
        } catch (IOException e) {
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            myWriter.write(formatter.format(date) + " Stream is corrupted.\n");
            e.printStackTrace();
        }
        myWriter.close();
    }

    public Connection  getConnection() {
        return crunchifyConn;
    }
}
