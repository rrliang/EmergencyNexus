package emergencynexus;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class EmergencyNexus extends Application{

    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));;
        stage.setScene(new Scene(root));
        stage.setTitle("Emergency Nexus");
        stage.show();
    }
}
