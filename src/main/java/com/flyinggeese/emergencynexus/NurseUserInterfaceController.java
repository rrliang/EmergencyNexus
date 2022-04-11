package com.flyinggeese.emergencynexus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class NurseUserInterfaceController implements Initializable {

    @FXML
    private Font x1;

    @FXML
    private Font x11;

    @FXML
    private Font x111;

    @FXML
    private Font x1111;

    @FXML
    private Font x11111;

    @FXML
    private Font x111111;

    @FXML
    private Font x11112;

    @FXML
    private Font x111121;

    @FXML
    private Font x111122;

    @FXML
    private Font x111123;

    @FXML
    private Font x112;

    @FXML
    private Font x1121;

    @FXML
    private Font x11211;

    @FXML
    private Font x11212;

    @FXML
    private Font x1122;

    @FXML
    private Font x11221;

    @FXML
    private Color x2;

    @FXML
    private Color x21;

    @FXML
    private Color x211;

    @FXML
    private Color x2111;

    @FXML
    private Color x21111;

    @FXML
    private Color x211111;

    @FXML
    private Color x21112;

    @FXML
    private Color x211121;

    @FXML
    private Color x211122;

    @FXML
    private Color x211123;

    @FXML
    private Color x212;

    @FXML
    private Color x2121;

    @FXML
    private Color x21211;

    @FXML
    private Color x21212;

    @FXML
    private Color x2122;

    @FXML
    private Color x21221;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    private TextField firstName, lastName;
    @FXML
    private ChoiceBox<String> feeling, symptoms;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //PATIENT VISIT FORM CHOICE BOXES

        ObservableList<String> s = FXCollections.observableArrayList();
        s.addAll("Good", "Okay", "Bad", "Terrible");
        feeling.setItems(s);
        feeling.setValue("Okay");

        ObservableList<String> hurt = FXCollections.observableArrayList();
        hurt.addAll("Head", "Shoulders", "Chest", "Arms", "Hips", "Legs", "Knees", "Feet");
        symptoms.setItems(hurt);
        symptoms.setValue("Head");


    }
}
