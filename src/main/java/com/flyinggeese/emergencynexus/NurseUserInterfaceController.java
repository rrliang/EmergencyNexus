package com.flyinggeese.emergencynexus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    private TextField fullName, physician, icant, bp, room, saline, notes;

    @FXML
    private ChoiceBox<String> feeling, symptoms, medication, injection;

    @FXML
    private DatePicker visit, checkin, checkout;

    @FXML
    private Button submit;

    @FXML
    private CheckBox tests;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //PATIENT VISIT FORM CHOICE BOXES
        ObservableList<String> hurt = FXCollections.observableArrayList();
        hurt.addAll("Head", "Shoulders", "Chest", "Arms", "Hips", "Legs", "Knees", "Feet");
        symptoms.setItems(hurt);
        symptoms.setValue("Head");

        ObservableList<String> s = FXCollections.observableArrayList();
        s.addAll("Good", "Okay", "Bad", "Terrible");
        feeling.setItems(s);
        feeling.setValue("Okay");

        ObservableList<String> meds = FXCollections.observableArrayList();
        meds.addAll("None", "Tylenol", "Aspirin", "Insulin");
        medication.setItems(meds);
        medication.setValue("None");

        ObservableList<String> inj = FXCollections.observableArrayList();
        inj.addAll("None", "COVID-19 Vaccine", "Flu Shot");
        injection.setItems(inj);
        injection.setValue("None");
    }

    @FXML
    void submitData(ActionEvent event) throws IOException, SQLException {
        ConnectToDatabase db = new ConnectToDatabase();
        db.makeJDBCConnection();
        String query = "INSERT INTO emergency_nexus.visit (dateofvisit, symptomsmyhurt, symptomsifeel, symptomsicant, bloodpressure, admissionstatuscheckin, admissionstatuscheckout, admissionstatusroom, amountofsaline, givenmedication, injectionsgiven, notesandobservations, patient, primaryphysician) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = db.getConnection().prepareStatement(query);

        pst.setString(1, ((TextField)visit.getEditor()).getText());
        pst.setString(2, symptoms.getSelectionModel().getSelectedItem().toString());
        pst.setString(3, feeling.getSelectionModel().getSelectedItem().toString());
        pst.setString(4, icant.getText());
        pst.setString(5, bp.getText());
        pst.setString(6, ((TextField)checkin.getEditor()).getText());
        pst.setString(7, ((TextField)checkout.getEditor()).getText());
        pst.setString(8, room.getText());
        pst.setString(9, saline.getText());
        pst.setString(10, medication.getSelectionModel().getSelectedItem().toString());
        pst.setString(11, injection.getSelectionModel().getSelectedItem().toString());
        pst.setString(12, notes.getText());
        pst.setString(13, fullName.getText());
        pst.setString(14, physician.getText());

        pst.execute();
        pst.close();
        clearFields();
    }
    public void clearFields() {
        fullName.clear();
        physician.clear();
        visit.setValue(null);
        symptoms.setValue("Head");
        feeling.setValue("Okay");
        icant.clear();
        bp.clear();
        checkin.setValue(null);
        checkout.setValue(null);
        room.clear();
        saline.clear();
        medication.setValue("None");
        injection.setValue("None");
        notes.clear();
    }
    @FXML
    private TextField namelookup;
    @FXML
    private DatePicker dob;

    void lookUpPatient(ActionEvent event) throws IOException, SQLException{
        ConnectToDatabase db = new ConnectToDatabase();
        db.makeJDBCConnection();
        String searchQuery = "SELECT * FROM emergency_nexus.patients WHERE fullname = ? AND dateofbirth = ?";
        PreparedStatement pst = db.getConnection().prepareStatement(searchQuery);

        pst.setString(1, namelookup.getText());
        pst.setString(2, ((TextField)dob.getEditor()).getText());

    }
}
