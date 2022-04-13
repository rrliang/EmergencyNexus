package com.flyinggeese.emergencynexus;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
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
                if (accountType == null) {
                    ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);
                    ButtonType contactAdmin = new ButtonType("Contact Admin", ButtonBar.ButtonData.OK_DONE);
                    Alert noAccountType = new Alert(Alert.AlertType.CONFIRMATION, "This user is in the system, but does not have a type of account", ok, contactAdmin);
                    //noAccountType.setContentText("This user is in the system, but does not have a type of account");
                    Optional<ButtonType> result = noAccountType.showAndWait();
                    if (result.isPresent() && result.get() == contactAdmin) {
                        System.out.println("SENT EMAIL!");
                    }
                } else {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    Stage stage = new Stage();
                    Scene scene = null;
                    switch(accountType.toLowerCase()) {
                        case "doctor":
                            fxmlLoader.setLocation(getClass().getResource("practitioner-user-interface.fxml"));
                            stage.setTitle("Practitioner Interface");
                            scene = new Scene(fxmlLoader.load());
                            break;
                        case "nurse":
                            fxmlLoader.setLocation(getClass().getResource("nurse-user-interface.fxml"));
                            stage.setTitle("Nurse Interface");
                            scene = new Scene(fxmlLoader.load());
                            break;
                        case "admin":
                            fxmlLoader.setLocation(getClass().getResource("sysadmin-user-interface.fxml"));
                            stage.setTitle("System Admin Interface");
                            scene = new Scene(fxmlLoader.load());
                            SysAdminController controller = fxmlLoader.getController();
                            controller.setPassword(loginPassword.getText());
                            controller.setUsername(loginUsername.getText());
                            break;
                        case "registration":
                            fxmlLoader.setLocation(getClass().getResource("registration-user-interface.fxml"));
                            stage.setTitle("Front Desk Interface");
                            scene = new Scene(fxmlLoader.load());
                            break;
                    }
                    scene.getStylesheets().add(String.valueOf(getClass().getResource("test.css")));
                    stage.getIcons().add(new Image(EmergencyNexus.class.getResourceAsStream("logo.png")));
                    stage.setScene(scene);
                    stage.show();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                }
            }
        }

    }

    @FXML
    void registerClicked(MouseEvent event) throws IOException {
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
}