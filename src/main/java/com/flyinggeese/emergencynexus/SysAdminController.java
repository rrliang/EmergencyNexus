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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class SysAdminController implements Initializable {

    private String password;
    private boolean showPassword = true;
    private UserAccounts editAccountUser;
    private boolean editAccountsEmailChanged, editAccountsUsernameChanged, editAccountsNameChanged = false;

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
    private Button createAccountSubmitButton, createAccountSaveDraftButton;

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
    private Button editAccountSelectAccountButton, editAccountSubmitButton, editAccountSaveDraftButton;

    @FXML
    private Label editAccountTypeLabel, editAccountEmailLabel, editAccountUsernameLabel, editAccountPasswordLabel, editAccountHoldersLabel,
            editAccountNameLabel, editAccountAddressLabel, editAccountNumberLabel;

    //INSTANCE VARIABLES FOR DELETE ACCOUNT TAB
    @FXML
    private ChoiceBox<Object> deleteAccountsChoiceBox;

    @FXML
    private TextField deleteAccountEmailTextField;

    @FXML
    private Button deleteAccountDeleteButton;


    protected void setPassword(String password) {
        this.password = password;
    }

    private ArrayList<Object> draftList;
    private ConnectToDatabase db = new ConnectToDatabase();

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

    private String checkIfEmpty() {
        String errorMessage = "";
        if (createAccountTypeOfAccountChoiceBox.getValue() == null) {
            errorMessage += "ERR: Type of account not chosen.\n";
        }
        if (createAccountFullNameTextField.getText() == "") {
            errorMessage += "ERR: No name given.\n";
        }
        if (createAccountEmailTextField.getText() == "") {
            errorMessage += "ERR: No email given.\n";
        }
        if (createAccountUsernameTextField.getText() == "") {
            errorMessage += "ERR: No account username given.\n";
        }
        if (createAccountPasswordTextField.getText() == "") {
            errorMessage += "ERR: No account password given.\n";
        }
        if (createAccountHomeAddressTextField.getText() == "") {
            errorMessage += "ERR: No home address given.\n";
        }
        if (createAccountPhoneNumberTextField.getText() == "") {
            errorMessage += "ERR: No phone number given.\n";
        }
        return errorMessage;
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
        String errMsg = "";
        UserAccounts user = getAccountInformation(editAccountsChoiceBox.getValue().toString(), editAccountsTextField.getText());
        if (editAccountTypeOfAccountChoiceBox.getValue().equals(user.getTypeOfAccount()) && editAccountFullNameTextField.getText().equals(user.getHolderFullName())
                && editAccountEmailTextField.getText().equals(user.getAccountEmail()) && editAccountUsernameTextField.getText().equals(user.getAccountUsername())
                && editAccountPasswordTextField.getText().equals(user.getAccountPassword()) && editAccountHomeAddressTextField.getText().equals(user.getHolderHomeAddress())
                && editAccountPhoneNumberTextField.getText().equals(user.getHolderPhoneNumber())) {
            Alert nothingInForm = new Alert(Alert.AlertType.CONFIRMATION);
            nothingInForm.setContentText("ERR: Nothing has been changed. Update will not be saved.");
            nothingInForm.showAndWait();
        } else if (editAccountsEmailChanged || editAccountsUsernameChanged || editAccountsNameChanged) {
            if ((errMsg = checkIfExists(editAccountUser.getAccountEmail(), editAccountUser.getAccountUsername(), editAccountUser.getHolderFullName())).length() != 0) {
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
                }
            }
            editAccountsChoiceBox.setValue(null);
            editAccountsTextField.setText("");
            setEditAccountVisibility(false);
            updateTable();
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
            draftList.add(editAccountUser);
            Alert accountUpdated = new Alert(Alert.AlertType.CONFIRMATION);
            accountUpdated.setContentText("Draft was successfully saved.");
            accountUpdated.showAndWait();
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
            UserAccounts account = new UserAccounts((String) createAccountTypeOfAccountChoiceBox.getValue(),
                    createAccountFullNameTextField.getText(), createAccountEmailTextField.getText(),
                    createAccountUsernameTextField.getText(), createAccountPasswordTextField.getText(),
                    createAccountHomeAddressTextField.getText(), createAccountPhoneNumberTextField.getText(),
                    "createAccount", formatter.format(date));
            draftList.add(account);
        }
    }

    @FXML
    void createAccountSubmitButtonClicked(ActionEvent event) throws SQLException {
        String emptyMsg = checkIfEmpty();
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

    }

    @FXML
    void viewAccountsSearchBarKeyReleased(KeyEvent event) {
        String searchText = viewAccountsSearchText.getText();
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
    }

    @FXML
    void deleteAccountsDeleteButtonClicked(ActionEvent event) {

    }

    private void deleteAccount(String username) throws SQLException {
        String checkQuery = "DELETE FROM users WHERE username = ?";
        PreparedStatement smt = db.getConnection().prepareStatement(checkQuery);
        smt.setString(1, username);
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
                        deleteAccount(viewAccountsTable.getSelectionModel().getSelectedItem().getAccountUsername());
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
