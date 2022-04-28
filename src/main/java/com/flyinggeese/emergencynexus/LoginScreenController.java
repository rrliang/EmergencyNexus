package com.flyinggeese.emergencynexus;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginScreenController implements Initializable {

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
                Scene scene = null;
                switch(accountType.toLowerCase()) {
                    case "doctor":
                        fxmlLoader.setLocation(getClass().getResource("staff-user-interface.fxml"));
                        stage.setTitle("Practitioner Interface");
                        scene = new Scene(fxmlLoader.load());
                        StaffUserInterfaceController doctorController = fxmlLoader.getController();
                        doctorController.setAccountType("doctor");
                        doctorController.setUsername(loginUsername.getText());
                        break;
                    case "nurse":
                        fxmlLoader.setLocation(getClass().getResource("staff-user-interface.fxml"));
                        stage.setTitle("Nurse Interface");
                        scene = new Scene(fxmlLoader.load());
                        StaffUserInterfaceController nurseController = fxmlLoader.getController();
                        nurseController.setAccountType("nurse");
                        nurseController.setUsername(loginUsername.getText());
                        break;
                    case "admin":
                        fxmlLoader.setLocation(getClass().getResource("sysadmin-user-interface.fxml"));
                        stage.setTitle("System Admin Interface");
                        scene = new Scene(fxmlLoader.load());
                        SysAdminUserInterfaceController controller = fxmlLoader.getController();
                        controller.setPassword(loginPassword.getText());
                        controller.setUsername(loginUsername.getText());
                        scene.getStylesheets().add(String.valueOf(getClass().getResource("test.css")));
                        break;
                }
                stage.getIcons().add(new Image(EmergencyNexus.class.getResourceAsStream("logo.png")));
                stage.setScene(scene);
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            else {
                Alert loginErr = new Alert(Alert.AlertType.ERROR);
                loginErr.setContentText("ERR: could not log in with this specific username and password.");
                loginErr.showAndWait();
            }
        }
    }

    @FXML
    void registerClicked(MouseEvent event) throws IOException {
        System.out.println("TODO: admin tickets UI");
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("register-screen.fxml"));
//        /*
//         * if "fx:controller" is not set in fxml
//         * fxmlLoader.setController(NewWindowController);
//         */
//        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
//        Stage stage = new Stage();
//        stage.setTitle("Account help");
//        stage.setScene(scene);
//        stage.show();
//        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginEnterButton.setDefaultButton(true);
    }
}