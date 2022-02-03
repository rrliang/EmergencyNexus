package emergencynexus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginScreenController {

    @FXML
    private Button loginEnterButton;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private TextField loginUsername;

    @FXML
    void checkLogin(ActionEvent event) {

    }

    @FXML
    void registerClicked(MouseEvent event) {
        System.out.println("register!");
    }
}