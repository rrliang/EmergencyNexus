package com.flyinggeese.emergencynexus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class EmergencyNexus extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EmergencyNexus.class.getResource("login-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Emergency Nexus");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }




}