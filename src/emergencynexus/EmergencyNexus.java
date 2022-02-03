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
        FXMLLoader loader = new FXMLLoader(new File("C:\\EmergencyNexus\\src\\emergencynexus\\loginScreen.fxml").toURI().toURL());
        Parent root = loader.load();
        stage.setScene(new Scene(root, 300, 250));
        stage.setTitle("Demo v2");
        stage.show();
    }
}
