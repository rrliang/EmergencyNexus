package com.flyinggeese.emergencynexus;

import javafx.scene.control.Alert;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class UserAccounts {
    private String typeOfAccount;
    private String accountEmail;
    private String accountUsername;
    private String accountPassword;
    private String holderFullName;
    private String holderHomeAddress;
    private String holderPhoneNumber;
    private String typeOfDraft;
    private String timeOfDraft;
    private String nameOfDraft;
    private String editChoice;
    private String editText;
    private ConnectToDatabase db = new ConnectToDatabase();

    public UserAccounts() throws SQLException {
        typeOfAccount = "";
        accountEmail = "";
        accountUsername = "";
        accountPassword = "";
        holderFullName = "";
        holderHomeAddress = "";
        holderPhoneNumber = "";
        typeOfDraft = "";
        timeOfDraft = "";
        nameOfDraft = "";
        editChoice = "";
        editText = "";
    }

    public UserAccounts(String typeOfAccount, String holderFullName, String accountEmail, String accountUsername, String accountPassword, String holderHomeAddress, String holderPhoneNumber) {
        this.typeOfAccount = typeOfAccount;
        this.accountEmail = accountEmail;
        this.accountUsername = accountUsername;
        this.accountPassword = accountPassword;
        this.holderFullName = holderFullName;
        this.holderHomeAddress = holderHomeAddress;
        this.holderPhoneNumber = holderPhoneNumber;
        typeOfDraft = "";
        timeOfDraft = "";
        nameOfDraft = "";
    }

    public UserAccounts(String typeOfAccount, String holderFullName, String accountEmail, String accountUsername, String accountPassword, String holderHomeAddress, String holderPhoneNumber, String typeOfDraft, String timeOfDraft, String nameOfDraft) {
        this.typeOfAccount = typeOfAccount;
        this.accountEmail = accountEmail;
        this.accountUsername = accountUsername;
        this.accountPassword = accountPassword;
        this.holderFullName = holderFullName;
        this.holderHomeAddress = holderHomeAddress;
        this.holderPhoneNumber = holderPhoneNumber;
        this.typeOfDraft = typeOfDraft;
        this.timeOfDraft = timeOfDraft;
        this.nameOfDraft = nameOfDraft;
    }

    public UserAccounts(String typeOfAccount, String holderFullName, String accountEmail, String accountUsername, String accountPassword, String holderHomeAddress, String holderPhoneNumber, String typeOfDraft, String timeOfDraft, String nameOfDraft, String editChoice, String editText) {
        this.typeOfAccount = typeOfAccount;
        this.accountEmail = accountEmail;
        this.accountUsername = accountUsername;
        this.accountPassword = accountPassword;
        this.holderFullName = holderFullName;
        this.holderHomeAddress = holderHomeAddress;
        this.holderPhoneNumber = holderPhoneNumber;
        this.typeOfDraft = typeOfDraft;
        this.timeOfDraft = timeOfDraft;
        this.nameOfDraft = nameOfDraft;
        this.editChoice = editChoice;
        this.editText = editChoice;
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

    public String getTypeOfDraft() {
        return typeOfDraft;
    }

    public void setTypeOfDraft(String typeOfDraft) {
        this.typeOfDraft = typeOfDraft;
    }

    public String getTimeOfDraft() {
        return timeOfDraft;
    }

    public void setTimeOfDraft(String timeOfDraft) {
        this.timeOfDraft = timeOfDraft;
    }

    public String getNameOfDraft() {
        return nameOfDraft;
    }

    public void setNameOfDraft(String nameOfDraft) {
        this.nameOfDraft = nameOfDraft;
    }

    public String getEditChoice() {
        return editChoice;
    }

    public void setEditChoice(String editChoice) {
        this.editChoice = editChoice;
    }

    public String getEditText() {
        return editText;
    }

    public void setEditText(String editText) {
        this.editText = editText;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == null || !(object instanceof UserAccounts)) {
            return false;
        }

        UserAccounts other = (UserAccounts) object;
        return this.typeOfAccount.equals(other.typeOfAccount) &&
                this.accountEmail.equals(other.accountEmail) &&
                this.accountUsername.equals(other.accountUsername) &&
                this.accountPassword.equals(other.accountPassword) &&
                this.holderFullName.equals(other.holderFullName) &&
                this.holderHomeAddress.equals(other.holderHomeAddress) &&
                this.holderPhoneNumber.equals(other.holderPhoneNumber) &&
                this.typeOfDraft.equals(other.typeOfDraft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeOfAccount, accountEmail, accountUsername, accountPassword, holderFullName, holderHomeAddress,
                holderPhoneNumber, typeOfDraft, timeOfDraft);
    }
}
