package com.flyinggeese.emergencynexus;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginScreenController {

    @FXML
    private Button loginEnterButton;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private TextField loginUsername;

    @FXML
    void checkLogin(ActionEvent event) throws IOException, SQLException {
        ConnectToDatabase db = new ConnectToDatabase();
        db.makeJDBCConnection();
        String searchQuery = "SELECT * FROM users WHERE username = ? AND password = ?";

        PreparedStatement smt = db.getConnection().prepareStatement(searchQuery);
        smt.setString(1, loginUsername.getText());
        smt.setString(2, loginPassword.getText());
        try(ResultSet resultSet = smt.executeQuery()) {
            if (resultSet.next()) {
                String accountType = resultSet.getString("typeofaccount");
                FXMLLoader fxmlLoader = new FXMLLoader();
                Stage stage = new Stage();
                switch(accountType.toLowerCase()) {
                    case "doctor":
                        fxmlLoader.setLocation(getClass().getResource("nurse-user-interface.fxml"));
                        stage.setTitle("Practitioner Interface");
                        break;
                    case "nurse":
                        fxmlLoader.setLocation(getClass().getResource("practitioner-user-interface.fxml"));
                        stage.setTitle("Nurse Interface");
                        break;
                    case "admin":
                        fxmlLoader.setLocation(getClass().getResource("sysadmin-user-interface.fxml"));
                        stage.setTitle("System Admin Interface");
                        break;
                    default:
                        System.out.println("This user is in the system, but does not have a type of account");
                        break;
                }
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
        }

    }

    @FXML
    void registerClicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("register-screen.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Account help");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}