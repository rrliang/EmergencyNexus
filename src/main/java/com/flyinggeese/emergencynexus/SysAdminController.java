package com.flyinggeese.emergencynexus;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class SysAdminController implements Initializable {

    protected String password;
    protected String username;
    private boolean showPassword = true, showClosed = true;
    private UserAccounts editAccountUser;
    private boolean editAccountsEmailChanged, editAccountsUsernameChanged, editAccountsNameChanged = false;
    ObservableList<UserAccounts> draftList;
    private ConnectToDatabase db = new ConnectToDatabase();

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab editAccountTab, createAccountTab, viewAccountsTab;

    @FXML
    private Font x1111111111, x1, x11, x111, x1111, x11111, x111111, x1111111, x11111111, x111111111;

    @FXML
    private Color x2111111111, x2, x21, x211, x2111, x21111, x211111, x2111111, x21111111, x211111111;

    //INSTANCE VARIABLES FOR CREATE ACCOUNT TAB
    @FXML
    private ChoiceBox<Object> createAccountTypeOfAccountChoiceBox;

    @FXML
    private TextField createAccountEmailTextField, createAccountUsernameTextField, createAccountFullNameTextField, createAccountHomeAddressTextField,
            createAccountPhoneNumberTextField;

    @FXML
    private Button createAccountSubmitButton, createAccountSaveDraftButton, createAccountClearChangesButton;

    @FXML
    private PasswordField createAccountPasswordTextField;


    //INSTANCE VARIABLES FOR VIEW ACCOUNTS TAB
    @FXML
    private TableView<UserAccounts> viewAccountsTable;

    @FXML
    private TableColumn<UserAccounts, String> viewAccountsTypeColumn, viewAccountFullNameColumn, viewAccountsEmailColumn,
            viewAccountsUsernameColumn, viewAccountsPasswordColumn, viewAccountsAddressColumn, viewAccountsPhoneColumn;

    @FXML
    private Button viewAccountsClearButton, viewAccountsShowPasswordButton, viewAccountsUpdateButton;

    @FXML
    private TextField viewAccountsSearchText;


    //INSTANCE VARIABLES FOR EDIT ACCOUNT TAB
    @FXML
    private ChoiceBox<Object> editAccountsChoiceBox, editAccountTypeOfAccountChoiceBox;

    @FXML
    private TextField editAccountEmailTextField, editAccountsTextField, editAccountUsernameTextField, editAccountFullNameTextField, editAccountHomeAddressTextField,
            editAccountPhoneNumberTextField;

    @FXML
    private PasswordField editAccountPasswordTextField;

    @FXML
    private Button editAccountSelectAccountButton, editAccountSubmitButton, editAccountSaveDraftButton, editAccountDiscardChangesButton, editAccountNewUserButton;

    @FXML
    private Label editAccountTypeLabel, editAccountEmailLabel, editAccountUsernameLabel, editAccountPasswordLabel, editAccountHoldersLabel,
            editAccountNameLabel, editAccountAddressLabel, editAccountNumberLabel;

    //INSTANCE VARIABLES FOR DELETE ACCOUNT TAB
    @FXML
    private ChoiceBox<Object> deleteAccountsChoiceBox;

    @FXML
    private TextField deleteAccountsTextField;

    @FXML
    private Button deleteAccountDeleteButton;

    //INSTANCE VARIABLES FOR DRAFTS TAB
    @FXML
    private Button draftUpdateButton, draftOpenButton, draftDeleteButton;

    @FXML
    private TableView<UserAccounts> draftsTableView;

    @FXML
    private TableColumn<UserAccounts, String> draftsTypeCol, draftsTimeCol, draftsNameCol;

    //INSTANCE VARIABLES FOR TICKETS TAB
    @FXML
    private Button ticketDisplayClosedButton, ticketsAssignButton, ticketCloseButton, ticketsUpdateButton;

    @FXML
    private TableView<AdminTicket> ticketsTable;

    @FXML
    private TableColumn<AdminTicket, String> ticketEmailCol, ticketUsernameCol, ticketIssueCol, ticketStatusCol, ticketAdminCol;


    protected void setPassword(String password) {
        this.password = password;
    }

    protected void setUsername(String username) {
        this.username = username;
    }


    private void updateTicketTable(String extend) throws SQLException {
        ObservableList<AdminTicket> tickets = FXCollections.observableArrayList();
        ticketEmailCol.setCellValueFactory(new PropertyValueFactory<AdminTicket,String>("email"));
        ticketUsernameCol.setCellValueFactory(new PropertyValueFactory<AdminTicket,String>("username"));
        ticketIssueCol.setCellValueFactory(new PropertyValueFactory<AdminTicket,String>("issue"));
        ticketStatusCol.setCellValueFactory(new PropertyValueFactory<AdminTicket,String>("status"));
        ticketAdminCol.setCellValueFactory(new PropertyValueFactory<AdminTicket,String>("admin"));
        String checkQuery = "SELECT * FROM admintickets" + extend;
        PreparedStatement smt = db.getConnection().prepareStatement(checkQuery);
        ResultSet rs = smt.executeQuery();
        AdminTicket ticket;
        while (rs.next()) {
            int id = rs.getInt("idadmintickets");
            String email = rs.getString("accountemail") == null ? "N/A" : rs.getString("accountemail");
            String username = rs.getString("username") == null ? "N/A" : rs.getString("username");
            String issue = rs.getString("issue");
            String status = rs.getString("status");
            String admin = rs.getString("assigned") == null ? "N/A" : rs.getString("assigned");
            UserAccounts user;
            ticket = new AdminTicket(id, email, username, issue, status, admin);
            tickets.add(ticket);
        }
        ticketsTable.setItems(tickets);
        ticketsTable.refresh();
    }

    @FXML
    void ticketCloseButton(ActionEvent event) throws SQLException {
        AdminTicket ticket = ticketsTable.getSelectionModel().getSelectedItem();
        String status = "";
        if (ticketCloseButton.getText().equals("Close ticket")) {
            status = "closed";
        } else {
            status = "open";
        }
        String updateQuery = "UPDATE admintickets SET status = ? WHERE idadmintickets = ?";
        PreparedStatement smt = db.getConnection().prepareStatement(updateQuery);
        smt.setString(1, status);
        smt.setInt(2, ticket.getId());
        smt.executeUpdate();
        updateTicketTable("");
    }

    @FXML
    void ticketsUpdateButtonClicked(ActionEvent event) throws SQLException {
        updateTicketTable("");
    }

    @FXML
    void ticketDisplayClosedButtonClicked(ActionEvent event) throws SQLException {
        if (showClosed) {
            updateTicketTable(" WHERE status = 'open'");
            ticketDisplayClosedButton.setText("Show closed tickets");
            showClosed = false;
        } else {
            showClosed = true;
            ticketDisplayClosedButton.setText("Don't show closed tickets");
            updateTicketTable("");
        }
    }

    @FXML
    void ticketsAssignButtonClicked(ActionEvent event) throws SQLException {
        AdminTicket ticket = ticketsTable.getSelectionModel().getSelectedItem();
        String updateQuery = "UPDATE admintickets SET assigned = ? WHERE idadmintickets = ?";
        PreparedStatement smt = db.getConnection().prepareStatement(updateQuery);
        smt.setString(1, username);
        smt.setInt(2, ticket.getId());
        smt.executeUpdate();
        updateTicketTable("");
    }

    @FXML
    void ticketsTableClicked(MouseEvent event) {
        AdminTicket ticket = ticketsTable.getSelectionModel().getSelectedItem();
        if (ticket.getStatus().equals("open")) {
            ticketCloseButton.setText("Close ticket");
        } else {
            ticketCloseButton.setText("Open ticket");
        }
    }


    private void updateDraftTable() {
        ObservableList<UserAccounts> accounts = FXCollections.observableArrayList();
        draftsTypeCol.setCellValueFactory(new PropertyValueFactory<UserAccounts,String>("typeOfDraft"));
        draftsTimeCol.setCellValueFactory(new PropertyValueFactory<UserAccounts,String>("timeOfDraft"));
        draftsNameCol.setCellValueFactory(new PropertyValueFactory<UserAccounts,String>("nameOfDraft"));
        draftsTableView.setItems(draftList);
    }

    @FXML
    void draftDeleteButtonClicked(ActionEvent event) {
        UserAccounts user = draftsTableView.getSelectionModel().getSelectedItem();
        draftList.remove(user);
        updateDraftTable();
    }

    @FXML
    void draftOpenButtonClicked(ActionEvent event) {
        UserAccounts user = draftsTableView.getSelectionModel().getSelectedItem();
        if (user.getTypeOfDraft().equals("Create")) {
            setCreateAccount(user.getTypeOfAccount(), user.getAccountEmail(), user.getAccountUsername(), user.getAccountPassword(), user.getHolderFullName(),
                    user.getHolderHomeAddress(), user.getHolderPhoneNumber());
            tabPane.getSelectionModel().select(createAccountTab);
        } else if (user.getTypeOfDraft().equals("Edit")) {
            setEditAccount(user.getEditChoice(), user.getEditText(), user.getTypeOfAccount(), user.getAccountEmail(), user.getAccountUsername(),
                    user.getAccountPassword(), user.getHolderFullName(), user.getHolderHomeAddress(), user.getHolderPhoneNumber());
            setEditAccountVisibility(true);
            tabPane.getSelectionModel().select(editAccountTab);
        }

    }

    @FXML
    void draftUpdateButtonClicked(ActionEvent event) {
        updateDraftTable();
    }

    @FXML
    void draftsTableViewClicked(MouseEvent event) {
        draftVisibility(true);
    }

    private void draftVisibility(boolean visibility) {
        draftOpenButton.setVisible(visibility);
        draftDeleteButton.setVisible(visibility);
    }

    private void setEditAccount(String choice, String text, String type, String email, String username, String password, String name, String address, String phone) {
        editAccountsChoiceBox.setValue(choice);
        editAccountsTextField.setText(text);
        editAccountTypeOfAccountChoiceBox.setValue(type);
        editAccountEmailTextField.setText(email);
        editAccountUsernameTextField.setText(username);
        editAccountPasswordTextField.setText(password);
        editAccountFullNameTextField.setText(name);
        editAccountHomeAddressTextField.setText(address);
        editAccountPhoneNumberTextField.setText(phone);
    }

    @FXML
    void editAccountNewUserButtonClicked(ActionEvent event) {
        setEditAccount("","","","","","","","","");
        setEditAccountVisibility(false);
    }

    @FXML
    void editAccountDiscardChangesButtonClicked(ActionEvent event) throws SQLException {
        UserAccounts user = getAccountInformation(editAccountsChoiceBox.getValue().toString(), editAccountsTextField.getText());
        if (editAccountTypeOfAccountChoiceBox.getValue().equals(user.getTypeOfAccount()) && editAccountFullNameTextField.getText().equals(user.getHolderFullName())
                && editAccountEmailTextField.getText().equals(user.getAccountEmail()) && editAccountUsernameTextField.getText().equals(user.getAccountUsername())
                && editAccountPasswordTextField.getText().equals(user.getAccountPassword()) && editAccountHomeAddressTextField.getText().equals(user.getHolderHomeAddress())
                && editAccountPhoneNumberTextField.getText().equals(user.getHolderPhoneNumber())) {
            Alert nothingInForm = new Alert(Alert.AlertType.CONFIRMATION);
            nothingInForm.setContentText("ERR: Nothing has changed, nothing will be discarded.");
            nothingInForm.showAndWait();
        } else {
            editAccountTypeOfAccountChoiceBox.setValue(user.getTypeOfAccount());
            editAccountEmailTextField.setText(user.getAccountEmail());
            editAccountUsernameTextField.setText(user.getAccountUsername());
            editAccountPasswordTextField.setText(user.getAccountPassword());
            editAccountFullNameTextField.setText(user.getHolderFullName());
            editAccountHomeAddressTextField.setText(user.getHolderHomeAddress());
            editAccountPhoneNumberTextField.setText(user.getHolderPhoneNumber());
        }
    }

    @FXML
    void createAccountClearChangesButtonClicked(ActionEvent event) {
        setCreateAccount("","","","","", "","");
    }

    private void setCreateAccount(String type, String email, String username, String password, String name, String address, String phone) {
        createAccountTypeOfAccountChoiceBox.setValue(type);
        createAccountEmailTextField.setText(email);
        createAccountUsernameTextField.setText(username);
        createAccountPasswordTextField.setText(password);
        createAccountFullNameTextField.setText(name);
        createAccountHomeAddressTextField.setText(address);
        createAccountPhoneNumberTextField.setText(phone);
    }

    private String checkIfEditEmailExists(String originalEmail, String emailAddress) throws SQLException {
        String errMsg = "";
        if (!originalEmail.equals(emailAddress)) {
            String checkQuery = "SELECT * FROM users WHERE companyemail = ?";
            PreparedStatement smt = db.getConnection().prepareStatement(checkQuery);
            smt.setString(1, emailAddress);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                if (editAccountsEmailChanged && emailAddress.equals(rs.getString("companyemail"))) {
                    errMsg += "ERR: Account email " + emailAddress + " already has an existing account\n";
                }
            }
        }
        return errMsg;
    }

    private String checkIfEditUserExists(String originalUsername, String username) throws SQLException {
        String errMsg = "";
        if (!originalUsername.equals(username)) {
            String checkQuery = "SELECT * FROM users WHERE username = ?";
            PreparedStatement smt = db.getConnection().prepareStatement(checkQuery);
            smt.setString(1, username);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                if (editAccountsUsernameChanged && username.equals(rs.getString("companyemail"))) {
                    errMsg += "ERR: Account username " + username + " already has an existing account\n";
                }
            }
        }
        return errMsg;
    }

    private String checkIfEditNameExists(String originalName, String name) throws SQLException {
        String errMsg = "";
        if (!originalName.equals(name)) {
            String checkQuery = "SELECT * FROM users WHERE fullname = ?";
            PreparedStatement smt = db.getConnection().prepareStatement(checkQuery);
            smt.setString(1, name);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                if (editAccountsNameChanged && name.equals(rs.getString("companyemail"))) {
                    errMsg += "ERR: Account name " + name + " already has an existing account\n";
                }
            }
        }
        return errMsg;
    }

    private String checkIfExists(String accountEmail, String username, String fullName) throws SQLException {
        String errMsg = "";
        String checkQuery = "SELECT * FROM users WHERE companyemail = ? OR username = ? OR fullname = ?";
        PreparedStatement smt = db.getConnection().prepareStatement(checkQuery);
        smt.setString(1, accountEmail);
        smt.setString(2, username);
        smt.setString(3, fullName);
        ResultSet rs = smt.executeQuery();
        while (rs.next()) {
            if (accountEmail.equals(rs.getString("companyemail"))) {
                errMsg += "ERR: Account email " + accountEmail + " already has an existing account\n";
            }
            if (username.equals(rs.getString("username"))) {
                errMsg += "ERR: Account username " + username + " already has an existing account\n";
            }
            if (fullName.equals(rs.getString("fullname"))) {
                errMsg += "ERR: Account holder " + fullName + " already has an existing account\n";
            }
        }
        return errMsg;
    }

    private String checkIfEmpty(Object type, String name, String email, String username, String password, String address, String phone) {
        String errorMessage = "";
        if (type == null) {
            errorMessage += "ERR: Type of account not chosen.\n";
        }
        if (name.equals("")) {
            errorMessage += "ERR: No name given.\n";
        }
        if (email.equals("")) {
            errorMessage += "ERR: No email given.\n";
        }
        if (username.equals("")) {
            errorMessage += "ERR: No account username given.\n";
        }
        if (password.equals("")) {
            errorMessage += "ERR: No account password given.\n";
        }
        if (address.equals("")) {
            errorMessage += "ERR: No home address given.\n";
        }
        if (phone.equals("")) {
            errorMessage += "ERR: No phone number given.\n";
        }
        return errorMessage;
    }

    private void deleteAccountHelper(String accountChoice, String accountText) throws SQLException {
        UserAccounts user;
        if ((user = getAccountInformation(accountChoice, accountText)) == null) {
            Alert accountCreated = new Alert(Alert.AlertType.CONFIRMATION);
            accountCreated.setContentText("There is no account associated with that " + accountChoice + " to delete.");
            accountCreated.showAndWait();
        } else {
            if (authenticatePassword("Enter the password associated with this admin account.")) {
                try {
                    switch(accountChoice) {
                        case "Username":
                            deleteAccountWithUser(accountText);
                            break;
                        case "Email":
                            deleteAccountWithEmail(accountText);
                            break;
                        case "Full Name":
                            deleteAccountWithName(accountText);
                            break;
                    }
                    Alert successDelete = new Alert(Alert.AlertType.CONFIRMATION);
                    successDelete.setContentText("The account associated with the" + accountChoice + " " + accountText + " successfully deleted.");
                    successDelete.showAndWait();
                    clearDelete();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                Alert wrongPass = new Alert(Alert.AlertType.ERROR);
                wrongPass.setContentText("ERR: Wrong password.");
                wrongPass.showAndWait();
            }
        }
    }

    private void clearDelete() {
        deleteAccountsChoiceBox.setValue("");
        deleteAccountsTextField.setText("");
    }

    private void editAccountHelper(String accountChoice, String accountText) throws SQLException {
        UserAccounts user;
        if ((user = getAccountInformation(accountChoice, accountText)) == null) {
            Alert accountCreated = new Alert(Alert.AlertType.CONFIRMATION);
            accountCreated.setContentText("There is no account associated with that " + editAccountsChoiceBox.getValue().toString() + " to edit.");
            accountCreated.showAndWait();
        } else {
            editAccountTypeOfAccountChoiceBox.setValue(user.getTypeOfAccount());
            editAccountEmailTextField.setText(user.getAccountEmail());
            editAccountUsernameTextField.setText(user.getAccountUsername());
            editAccountPasswordTextField.setText(user.getAccountPassword());
            editAccountFullNameTextField.setText(user.getHolderFullName());
            editAccountHomeAddressTextField.setText(user.getHolderHomeAddress());
            editAccountPhoneNumberTextField.setText(user.getHolderPhoneNumber());
            setEditAccountVisibility(true);
            editAccountsNameChanged = false;
            editAccountsUsernameChanged = false;
            editAccountsEmailChanged = false;
        }
    }

    @FXML
    void editAccountSelectAccountButtonClicked(ActionEvent event) throws SQLException {
        editAccountHelper(editAccountsChoiceBox.getValue().toString(), editAccountsTextField.getText());
    }

    private UserAccounts getAccountInformation (String userIdentification, String userInfo) throws SQLException {
        String errMsg = "";
        PreparedStatement smt = null;
        String checkQuery = "";
        switch(userIdentification) {
            case "Username":
                checkQuery = "SELECT * FROM users WHERE username = ?";
                break;
            case "Email":
                checkQuery = "SELECT * FROM users WHERE companyemail = ?";
                break;
            case "Full Name":
                checkQuery = "SELECT * FROM users WHERE fullname = ?";
                break;
        }
        smt = db.getConnection().prepareStatement(checkQuery);
        smt.setString(1, userInfo);

        int count = 0;
        ResultSet rs = smt.executeQuery();
        UserAccounts user = null;
        while (rs.next()) {
            String accountType = rs.getString("typeofaccount");
            String fullName = rs.getString("fullname");
            String email = rs.getString("companyemail");
            String address = rs.getString("homeaddress");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String phone = rs.getString("phonenumber");
            user = new UserAccounts(accountType, fullName, email, username, password, address, phone);
        }
        return user;
    }

    @FXML
    void editAccountSubmitButtonClicked(ActionEvent event) throws SQLException {
        String errMsg = "", emptyText = "";
        UserAccounts user = getAccountInformation(editAccountsChoiceBox.getValue().toString(), editAccountsTextField.getText());
        errMsg += checkIfEditEmailExists(user.getAccountEmail(), editAccountUser.getAccountEmail());
        errMsg += checkIfEditUserExists(user.getAccountUsername(), editAccountUser.getAccountUsername());
        errMsg += checkIfEditNameExists(user.getHolderFullName(), editAccountUser.getHolderFullName());
        if (editAccountTypeOfAccountChoiceBox.getValue().equals(user.getTypeOfAccount()) && editAccountFullNameTextField.getText().equals(user.getHolderFullName())
                && editAccountEmailTextField.getText().equals(user.getAccountEmail()) && editAccountUsernameTextField.getText().equals(user.getAccountUsername())
                && editAccountPasswordTextField.getText().equals(user.getAccountPassword()) && editAccountHomeAddressTextField.getText().equals(user.getHolderHomeAddress())
                && editAccountPhoneNumberTextField.getText().equals(user.getHolderPhoneNumber())) {
            Alert nothingInForm = new Alert(Alert.AlertType.CONFIRMATION);
            nothingInForm.setContentText("ERR: Nothing has been changed. Update will not be saved.");
            nothingInForm.showAndWait();
        } else if ((emptyText = checkIfEmpty(editAccountTypeOfAccountChoiceBox.getValue(), editAccountFullNameTextField.getText(),
                editAccountEmailTextField.getText(), editAccountUsernameTextField.getText(), editAccountPasswordTextField.getText(),
                editAccountHomeAddressTextField.getText(), editAccountPhoneNumberTextField.getText())).length() != 0) {
            Alert blankSomething = new Alert(Alert.AlertType.CONFIRMATION);
            blankSomething.setContentText(emptyText);
            blankSomething.showAndWait();
        } else if (errMsg.length() != 0) {
                Alert accountAlreadyExists = new Alert(Alert.AlertType.CONFIRMATION);
                accountAlreadyExists.setContentText(errMsg);
                accountAlreadyExists.showAndWait();
        } else {
            String type = "";
            switch (editAccountsChoiceBox.getValue().toString()) {
                case "Username":
                    type = "WHERE (username = ?)";
                    break;
                case "Email":
                    type = "WHERE (companyemail = ?)";
                    break;
                case "Full Name":
                    type = "WHERE (fullname = ?)";
                    break;
            }
            String updateQuery = "UPDATE users set typeofaccount = ? , fullname = ? , companyemail = ? , homeaddress = ? , phonenumber = ? , username = ? , password = ? " + type;

            PreparedStatement smt = db.getConnection().prepareStatement(updateQuery);
            smt.setString(1, editAccountUser.getTypeOfAccount());
            smt.setString(2, editAccountUser.getHolderFullName());
            smt.setString(3, editAccountUser.getAccountEmail());
            smt.setString(4, editAccountUser.getHolderHomeAddress());
            smt.setString(5, editAccountUser.getHolderPhoneNumber());
            smt.setString(6, editAccountUser.getAccountUsername());
            smt.setString(7, editAccountUser.getAccountPassword());
            smt.setString(8, editAccountsTextField.getText());
            if (smt.executeUpdate() != 0) {
                Alert accountUpdated = new Alert(Alert.AlertType.CONFIRMATION);
                accountUpdated.setContentText("Account with the " + editAccountsChoiceBox.getValue().toString().toLowerCase() + " " + editAccountsTextField.getText() + " was successfully updated.");
                accountUpdated.showAndWait();
                editAccountsChoiceBox.setValue(null);
                editAccountsTextField.setText("");
                setEditAccountVisibility(false);
                updateTable();
            }
        }
    }

    @FXML
    void editAccountSaveDraftButtonClicked(ActionEvent event) throws SQLException {
        UserAccounts user = getAccountInformation(editAccountsChoiceBox.getValue().toString(), editAccountsTextField.getText());
        if (editAccountTypeOfAccountChoiceBox.getValue().equals(user.getTypeOfAccount()) && editAccountFullNameTextField.getText().equals(user.getHolderFullName())
                && editAccountEmailTextField.getText().equals(user.getAccountEmail()) && editAccountUsernameTextField.getText().equals(user.getAccountUsername())
                && editAccountPasswordTextField.getText().equals(user.getAccountPassword()) && editAccountHomeAddressTextField.getText().equals(user.getHolderHomeAddress())
                && editAccountPhoneNumberTextField.getText().equals(user.getHolderPhoneNumber())) {
            Alert nothingInForm = new Alert(Alert.AlertType.CONFIRMATION);
            nothingInForm.setContentText("ERR: Nothing has been changed. Update will not be saved.");
            nothingInForm.showAndWait();
        } else {
            editAccountUser.setTypeOfDraft("Edit");
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();
            editAccountUser.setTimeOfDraft(formatter.format(date));
            editAccountUser.setNameOfDraft(nameDraft());
            editAccountUser.setEditChoice((String) editAccountsChoiceBox.getValue());
            editAccountUser.setEditText(editAccountsTextField.getText());
            if (!checkDrafts(editAccountUser)) {
                draftList.add(editAccountUser);
                updateDraftTable();
                Alert draftSaved = new Alert(Alert.AlertType.CONFIRMATION);
                draftSaved.setContentText("Draft was successfully saved.");
                draftSaved.showAndWait();
            } else {
                Alert nothingInForm = new Alert(Alert.AlertType.ERROR);
                nothingInForm.setContentText("ERR: Draft already exists.");
                nothingInForm.showAndWait();
            }
        }
    }

    @FXML
    void createAccountSaveDraftButtonClicked(ActionEvent event) throws SQLException{
        String errMsg;
        if (createAccountTypeOfAccountChoiceBox.getValue() == null && createAccountFullNameTextField.getText() == ""
                && createAccountEmailTextField.getText() == "" && createAccountUsernameTextField.getText() == ""
                && createAccountPasswordTextField.getText() == "" && createAccountHomeAddressTextField.getText() == ""
                && createAccountPhoneNumberTextField.getText() == "") {
            Alert nothingInForm = new Alert(Alert.AlertType.CONFIRMATION);
            nothingInForm.setContentText("ERR: Nothing has been changed. Draft will not be saved.");
            nothingInForm.showAndWait();
        } else if((errMsg = checkIfExists(createAccountEmailTextField.getText(), createAccountUsernameTextField.getText(), createAccountFullNameTextField.getText())).length() != 0) {
            Alert accountAlreadyExists = new Alert(Alert.AlertType.CONFIRMATION);
            accountAlreadyExists.setContentText(errMsg);
            accountAlreadyExists.showAndWait();
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();
            String type = "";
            try {
                type = createAccountTypeOfAccountChoiceBox.getValue().toString();
            } catch (NullPointerException e) {
                type = "";
            }

            UserAccounts account = new UserAccounts(type,
                    createAccountFullNameTextField.getText(), createAccountEmailTextField.getText(),
                    createAccountUsernameTextField.getText(), createAccountPasswordTextField.getText(),
                    createAccountHomeAddressTextField.getText(), createAccountPhoneNumberTextField.getText(),
                    "Create", formatter.format(date), "");
            if (!checkDrafts(account)) {
                account.setNameOfDraft(nameDraft());
                draftList.add(account);
                updateDraftTable();
                Alert draftSaved = new Alert(Alert.AlertType.CONFIRMATION);
                draftSaved.setContentText("Draft was successfully saved.");
                draftSaved.showAndWait();
            } else {
                Alert nothingInForm = new Alert(Alert.AlertType.ERROR);
                nothingInForm.setContentText("ERR: Draft already exists.");
                nothingInForm.showAndWait();
            }
        }
    }

    private boolean checkDrafts (UserAccounts user) {
        return draftList.contains(user);
    }

    @FXML
    void createAccountSubmitButtonClicked(ActionEvent event) throws SQLException {
        String emptyMsg = checkIfEmpty(createAccountTypeOfAccountChoiceBox.getValue(), createAccountFullNameTextField.getText(),
                createAccountEmailTextField.getText(), createAccountUsernameTextField.getText(), createAccountPasswordTextField.getText(),
                createAccountHomeAddressTextField.getText(), createAccountPhoneNumberTextField.getText());
        String errMsg;
        if((errMsg = checkIfExists(createAccountEmailTextField.getText(), createAccountUsernameTextField.getText(), createAccountFullNameTextField.getText())).length() != 0) {
            Alert accountCreated = new Alert(Alert.AlertType.CONFIRMATION);
            accountCreated.setContentText(errMsg);
            accountCreated.showAndWait();
        } else if (emptyMsg.length() != 0) {
            Alert missingAlert = new Alert(Alert.AlertType.ERROR);
            missingAlert.setContentText(emptyMsg);
            missingAlert.showAndWait();
        }else {
            String insertQuery = "INSERT INTO users (typeofaccount,fullname,companyemail,homeaddress,phonenumber,username,password) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement smt = db.getConnection().prepareStatement(insertQuery);
            smt.setString(1, (String) createAccountTypeOfAccountChoiceBox.getValue());
            smt.setString(2, createAccountFullNameTextField.getText());
            smt.setString(3, createAccountEmailTextField.getText());
            smt.setString(4, createAccountHomeAddressTextField.getText());
            smt.setString(5, createAccountPhoneNumberTextField.getText());
            smt.setString(6, createAccountUsernameTextField.getText());
            smt.setString(7, createAccountPasswordTextField.getText());
            smt.executeUpdate();

            Alert accountCreated = new Alert(Alert.AlertType.CONFIRMATION);
            accountCreated.setContentText("Successfully created new user!");
            accountCreated.showAndWait();
        }
        updateTable();
    }

    @FXML
    void viewAccountsClearButtonClicked(ActionEvent event) {
        //TODO
    }

    @FXML
    void viewAccountsSearchBarKeyReleased(KeyEvent event) {
        String searchText = viewAccountsSearchText.getText();
    }

    public String nameDraft () {
        String name = "";
        TextInputDialog dialog = new TextInputDialog("default name");

        dialog.setTitle("Name this draft");
        dialog.setHeaderText("Enter your name. This will be used to find this draft later on.");
        dialog.setContentText("Name: ");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            name = result.get();
        }
        return name;
    }

    public boolean authenticatePassword(String headerText) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Check Authenticity");
        dialog.setHeaderText(headerText);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        PasswordField pwd = new PasswordField();
        HBox content = new HBox();
        content.setAlignment(Pos.CENTER_LEFT);
        content.setSpacing(20);
        content.getChildren().addAll(new Label("Password: "), pwd);
        dialog.getDialogPane().setContent(content);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return pwd.getText();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (result.get().equals(password)) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    @FXML
    void viewAccountsShowPasswordClicked(ActionEvent event) throws SQLException, IOException {
        if (showPassword) {
            showPassword = false;
            viewAccountsShowPasswordButton.setText("Show password");
            updateTable();
        } else {
            if (authenticatePassword("Enter the password associated with this admin account.")) {
                showPassword = true;
                viewAccountsShowPasswordButton.setText("Hide password");
                updateTable();
            } else {
                Alert wrongPass = new Alert(Alert.AlertType.ERROR);
                wrongPass.setContentText("ERR: Wrong password.");
                wrongPass.showAndWait();
            }
        }
    }

    @FXML
    void viewAccountsUpdateClicked(ActionEvent event) {
        try {
            updateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        draftVisibility(false);

        draftList = FXCollections.observableArrayList();
        db.makeJDBCConnection();
        ObservableList<Object> accountList = FXCollections.observableArrayList();
        accountList.addAll("Admin", "Nurse", "Doctor");
        createAccountTypeOfAccountChoiceBox.setItems(accountList);
        editAccountTypeOfAccountChoiceBox.setItems(accountList);

        ObservableList<Object> accountIdentifierList = FXCollections.observableArrayList();
        accountIdentifierList.addAll("Username", "Email", "Full Name");
        editAccountsChoiceBox.setItems(accountIdentifierList);
        deleteAccountsChoiceBox.setItems(accountIdentifierList);
        setEditAccountVisibility(false);
        try {
            updateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            editAccountUser = new UserAccounts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            updateTicketTable("");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        editAccountTypeOfAccountChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                editAccountUser.setTypeOfAccount((String) editAccountTypeOfAccountChoiceBox.getItems().get((Integer) number2));
            }
        });

        editAccountEmailTextField.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                editAccountUser.setAccountEmail(newValue);
                if (oldValue != newValue) {
                    editAccountsEmailChanged = true;
                }
            }
        });

        editAccountUsernameTextField.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                editAccountUser.setAccountUsername(newValue);
                if (oldValue != newValue) {
                    editAccountsUsernameChanged = true;
                }
            }
        });

        editAccountPasswordTextField.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                editAccountUser.setAccountPassword(newValue);
            }
        });

        editAccountFullNameTextField.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                editAccountUser.setHolderFullName(newValue);
                if (oldValue != newValue) {
                    editAccountsNameChanged = true;
                }
            }
        });

        editAccountHomeAddressTextField.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                editAccountUser.setHolderHomeAddress(newValue);
            }
        });

        editAccountPhoneNumberTextField.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                editAccountUser.setHolderPhoneNumber(newValue);
            }
        });
    }

    private void setEditAccountVisibility(boolean visibility) {
        editAccountTypeOfAccountChoiceBox.setVisible(visibility);
        editAccountEmailTextField.setVisible(visibility);
        editAccountUsernameTextField.setVisible(visibility);
        editAccountPasswordTextField.setVisible(visibility);
        editAccountFullNameTextField.setVisible(visibility);
        editAccountHomeAddressTextField.setVisible(visibility);
        editAccountPhoneNumberTextField.setVisible(visibility);
        editAccountTypeLabel.setVisible(visibility);
        editAccountEmailLabel.setVisible(visibility);
        editAccountUsernameLabel.setVisible(visibility);
        editAccountPasswordLabel.setVisible(visibility);
        editAccountHoldersLabel.setVisible(visibility);
        editAccountNameLabel.setVisible(visibility);
        editAccountAddressLabel.setVisible(visibility);
        editAccountNumberLabel.setVisible(visibility);
        editAccountSubmitButton.setVisible(visibility);
        editAccountSaveDraftButton.setVisible(visibility);
        editAccountDiscardChangesButton.setVisible(visibility);
        editAccountNewUserButton.setVisible(visibility);
    }

    private void updateTable() throws SQLException {
        ObservableList<UserAccounts> accounts = FXCollections.observableArrayList();
        viewAccountsTypeColumn.setCellValueFactory(new PropertyValueFactory<UserAccounts,String>("typeOfAccount"));
        viewAccountFullNameColumn.setCellValueFactory(new PropertyValueFactory<UserAccounts,String>("holderFullName"));
        viewAccountsEmailColumn.setCellValueFactory(new PropertyValueFactory<UserAccounts,String>("accountEmail"));
        viewAccountsUsernameColumn.setCellValueFactory(new PropertyValueFactory<UserAccounts,String>("accountUsername"));
        viewAccountsPasswordColumn.setCellValueFactory(new PropertyValueFactory<UserAccounts,String>("accountPassword"));
        viewAccountsAddressColumn.setCellValueFactory(new PropertyValueFactory<UserAccounts,String>("holderHomeAddress"));
        viewAccountsPhoneColumn.setCellValueFactory(new PropertyValueFactory<UserAccounts,String>("holderPhoneNumber"));
        String checkQuery = "SELECT * FROM users";
        PreparedStatement smt = db.getConnection().prepareStatement(checkQuery);
        ResultSet rs = smt.executeQuery();
        while (rs.next()) {
            String accountType = rs.getString("typeofaccount");
            String fullName = rs.getString("fullname");
            String email = rs.getString("companyemail");
            String address = rs.getString("homeaddress");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String phone = rs.getString("phonenumber");
            UserAccounts user;
            if (showPassword) {
                user = new UserAccounts(accountType, fullName, email, username, password, address, phone);
            } else {
                password = "*".repeat(password.length());
                user = new UserAccounts(accountType, fullName, email, username, password, address, phone);
            }
            accounts.add(user);
        }
        viewAccountsTable.setItems(accounts);
        viewAccountsTable.refresh();
    }

    @FXML
    void deleteAccountsDeleteButtonClicked(ActionEvent event) throws SQLException {
        deleteAccountHelper(deleteAccountsChoiceBox.getValue().toString(), deleteAccountsTextField.getText());
    }

    private void deleteAccountWithUser(String username) throws SQLException {
        String checkQuery = "DELETE FROM users WHERE username = ?";
        PreparedStatement smt = db.getConnection().prepareStatement(checkQuery);
        smt.setString(1, username);
        smt.execute();
        updateTable();
    }

    private void deleteAccountWithEmail(String email) throws SQLException {
        String checkQuery = "DELETE FROM users WHERE companyemail = ?";
        PreparedStatement smt = db.getConnection().prepareStatement(checkQuery);
        smt.setString(1, email);
        smt.execute();
        updateTable();
    }

    private void deleteAccountWithName(String name) throws SQLException {
        String checkQuery = "DELETE FROM users WHERE fullname = ?";
        PreparedStatement smt = db.getConnection().prepareStatement(checkQuery);
        smt.setString(1, name);
        smt.execute();
        updateTable();
    }

    @FXML
    public void viewAccountsTableClicked(MouseEvent event) {
        ContextMenu cm = new ContextMenu();
        MenuItem edit = new MenuItem("Edit");
        cm.getItems().add(edit);
        MenuItem delete = new MenuItem("Delete");
        cm.getItems().add(delete);
        if(event.getButton() == MouseButton.SECONDARY) {
            viewAccountsTable.setContextMenu(cm);
            edit.setOnAction((cmEvent) -> {
                try {
                    editAccountsChoiceBox.setValue("Username");
                    editAccountsTextField.setText(viewAccountsTable.getSelectionModel().getSelectedItem().getAccountUsername());
                    editAccountHelper("Username", viewAccountsTable.getSelectionModel().getSelectedItem().getAccountUsername());
                    tabPane.getSelectionModel().select(editAccountTab);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            delete.setOnAction((cmEvent) -> {
                if (authenticatePassword("Enter the password associated with this admin account.")) {
                    try {
                        deleteAccountWithUser(viewAccountsTable.getSelectionModel().getSelectedItem().getAccountUsername());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    Alert wrongPass = new Alert(Alert.AlertType.ERROR);
                    wrongPass.setContentText("ERR: Wrong password.");
                    wrongPass.showAndWait();
                }
            });
        }
    }
}
