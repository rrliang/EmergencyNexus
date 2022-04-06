package com.flyinggeese.emergencynexus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SysAdminController implements Initializable {

    @FXML
    private Font x1111111111, x1, x11, x111, x1111, x11111, x111111, x1111111, x11111111, x111111111;

    @FXML
    private Color x2111111111, x2, x21, x211, x2111, x21111, x211111, x2111111, x21111111, x211111111;

    @FXML
    private ChoiceBox<Object> createAccounttypeOfAccountChoiceBox;

    @FXML
    private TextField createAccountEmailTextField, createAccountUsernameTextField, createAccountFullNameTextField, createAccountHomeAddressTextField,
            createAccountPhoneNumberTextField;

    @FXML
    private PasswordField createAccountPasswordTextField;

    @FXML
    private Button createAccountSubmitButton;

    @FXML
    private Button createAccountSaveDraftButton;

    private ArrayList<Object> draftList;

    @FXML
    void createAccountSaveDraftButtonClicked(ActionEvent event) {
        SysAdminCreateAccount account = new SysAdminCreateAccount((String) createAccounttypeOfAccountChoiceBox.getValue(),
                createAccountEmailTextField.getText(), createAccountUsernameTextField.getText(),
                createAccountPasswordTextField.getText(), createAccountFullNameTextField.getText(),
                createAccountHomeAddressTextField.getText(), createAccountPhoneNumberTextField.getText());
        draftList.add(account);
    }

    @FXML
    void createAccountSubmitButtonClicked(ActionEvent event) throws SQLException {
        ConnectToDatabase db = new ConnectToDatabase();
        db.makeJDBCConnection();
        String insertQuery = "INSERT INTO users (typeofaccount,fullname,companyemail,homeaddress,phonenumber,username,password) VALUES (?,?,?,?,?,?,?)";

        PreparedStatement smt = db.getConnection().prepareStatement(insertQuery);
        smt.setString(1, (String) createAccounttypeOfAccountChoiceBox.getValue());
        smt.setString(2, createAccountFullNameTextField.getText());
        smt.setString(3, createAccountEmailTextField.getText());
        smt.setString(4, createAccountHomeAddressTextField.getText());
        smt.setString(5, createAccountPhoneNumberTextField.getText());
        smt.setString(6, createAccountUsernameTextField.getText());
        smt.setString(7, createAccountPasswordTextField.getText());
        smt.executeUpdate();
        System.out.println("Successfully created new user");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        draftList = new ArrayList<>();
        ObservableList<Object> list = FXCollections.observableArrayList();
        list.addAll("Admin", "Nurse","Doctor");
        createAccounttypeOfAccountChoiceBox.setItems(list);
    }
}
