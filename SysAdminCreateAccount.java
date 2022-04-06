package com.flyinggeese.emergencynexus;

import javafx.scene.control.Alert;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SysAdminCreateAccount {
    private String typeOfAccount;
    private String accountEmail;
    private String accountUsername;
    private String accountPassword;
    private String holderFullName;
    private String holderHomeAddress;
    private String holderPhoneNumber;
    private ConnectToDatabase db = new ConnectToDatabase();

    public SysAdminCreateAccount() throws SQLException {

    }

    public SysAdminCreateAccount(String typeOfAccount, String accountEmail, String accountUsername, String accountPassword, String holderFullName, String holderHomeAddress, String holderPhoneNumber) {
        this.typeOfAccount = typeOfAccount;
        this.accountEmail = accountEmail;
        this.accountUsername = accountEmail;
        this.accountPassword = accountPassword;
        this.holderFullName = holderFullName;
        this.holderHomeAddress = holderHomeAddress;
        this.holderPhoneNumber = holderPhoneNumber;
    }

    public void addAccountToDatabase() throws SQLException {
        db.makeJDBCConnection();
        String insertQuery = "INSERT INTO users (typeofaccount,fullname,companyemail,homeaddress,phonenumber,username,password) VALUES (?,?,?,?,?,?,?)";

        PreparedStatement smt = db.getConnection().prepareStatement(insertQuery);
        smt.setString(1, typeOfAccount);
        smt.setString(2, holderFullName);
        smt.setString(3, accountEmail);
        smt.setString(4, holderHomeAddress);
        smt.setString(5, holderPhoneNumber);
        smt.setString(6, accountUsername);
        smt.setString(7, accountPassword);
        smt.executeUpdate();

        Alert accountCreated = new Alert(Alert.AlertType.CONFIRMATION);
        accountCreated.setContentText("Successfully created new user!");
        accountCreated.showAndWait();
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getHolderFullName() {
        return holderFullName;
    }

    public void setHolderFullName(String holderFullName) {
        this.holderFullName = holderFullName;
    }

    public String getHolderHomeAddress() {
        return holderHomeAddress;
    }

    public void setHolderHomeAddress(String holderHomeAddress) {
        this.holderHomeAddress = holderHomeAddress;
    }

    public String getHolderPhoneNumber() {
        return holderPhoneNumber;
    }

    public void setHolderPhoneNumber(String holderPhoneNumber) {
        this.holderPhoneNumber = holderPhoneNumber;
    }
}
