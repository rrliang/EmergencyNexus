package com.flyinggeese.emergencynexus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.IOException;

public class EmergencyNexus extends Application {

    static Connection crunchifyConn = null;
    static PreparedStatement crunchifyPrepareStat = null;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EmergencyNexus.class.getResource("login-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Emergency Nexus");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        makeJDBCConnection();
        launch();
    }

    private static void makeJDBCConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            log("Congrats - Seems your MySQL JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            log("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
            e.printStackTrace();
            return;
        }

        try {
            // DriverManager: The basic service for managing a set of JDBC drivers.
            crunchifyConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ayo", "root", "");
            if (crunchifyConn != null) {
                log("Connection Successful! Enjoy. Now it's time to push data");
            } else {
                log("Failed to make connection!");
            }
        } catch (SQLException e) {
            log("MySQL Connection Failed!");
            e.printStackTrace();
            return;
        }

    }

    private static void addDataToDB(String companyName, String address, int totalEmployee, String webSite) {

        try {
            String insertQueryStatement = "INSERT  INTO  Employee  VALUES  (?,?,?,?)";

            crunchifyPrepareStat = crunchifyConn.prepareStatement(insertQueryStatement);
            crunchifyPrepareStat.setString(1, companyName);
            crunchifyPrepareStat.setString(2, address);
            crunchifyPrepareStat.setInt(3, totalEmployee);
            crunchifyPrepareStat.setString(4, webSite);

            // execute insert SQL statement
            crunchifyPrepareStat.executeUpdate();
            log(companyName + " added successfully");
        } catch (

                SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getDataFromDB() {

        try {
            // MySQL Select Query Tutorial
            String getQueryStatement = "SELECT * FROM employee";

            crunchifyPrepareStat = crunchifyConn.prepareStatement(getQueryStatement);

            // Execute the Query, and get a java ResultSet
            ResultSet rs = crunchifyPrepareStat.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) {
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                int employeeCount = rs.getInt("EmployeeCount");
                String website = rs.getString("Website");

                // Simply Print the results
                System.out.format("%s, %s, %s, %s\n", name, address, employeeCount, website);
            }

        } catch (

                SQLException e) {
            e.printStackTrace();
        }

    }

    // Simple log utility
    private static void log(String string) {
        System.out.println(string);

    }
}