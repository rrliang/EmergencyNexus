package com.flyinggeese.emergencynexus;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.*;

public class StaffUserInterfaceController implements Initializable {

    //MENU INSTANCE VARIABLES
    @FXML
    private JFXButton menuSaveDraftButton, menuSubmitButton, menuOpenFormButton,
            menuDeleteButton, menuClearFormButton, submitBilling, menu;

    @FXML
    private HBox uniqueMenuButtonHbox, registerPregnantHbox;

    //FXML VARIABLES FOR REGISTER FORM
    @FXML
    private TextField registerAddressText, registerOtherAllergyText, registerEmailText, registerEthnicityText,
            registerGuardianNameText, registerGuardianPhoneText, registerHeightText, registerMedicationText,
            registerNameText, registerPhoneText, registerPreConditionsText, registerPronounsText,
            registerReligionText, registerWeightText, registerDrugAllergyText;

    @FXML
    private DatePicker registerBirthDateCalendar, registerCovidBoosterVaccineCalendar, registerCovidPrimaryVaccineCalendar,
            registerCovidSecondaryVaccineCalendar;

    @FXML
    private ChoiceBox<Object> registerBloodTypeChoiceBox, registerCovidBoosterVaccineChoice, registerCovidPrimaryVaccineChoice,
            registerCovidSecondaryVaccineChoice, registerGenderChoice, registerRaceChoice,
            registerSexChoice, registerSexualChoice, registerSubstanceChoice, registerInsuranceChoice;

    @FXML
    private CheckBox registerDrugAllergyCheck, registerFoodAllergyCheck, registerInsectAllergyCheck,
            registerLatexAllergyCheck, registerMoldAllergyCheck, registerPetAllergyCheck, registerPollenAllergyCheck,
            registerInsulinCheck, registerAntibioticsCheck, registerAntiCoagulantCheck, registerIbuprofenCheck, registerNaxCheck,
            registerDrugHistoryCheck, registerHistoryAlcoholCheck, registerHistorySmokingCheck, visitMouthFeel;

    @FXML
    private RadioButton registerHispanicRadio, registerNonHispanicRadio, registerNonReligiousRadio, registerReligiousRadio,
            registerMaleRadio, registerFemaleRadio, registerSexualYesRadio, registerSexualNoRadio, registerPregnantRadio;

    //INSTANCE VARIABLES FOR DRAFT
    @FXML
    private TableView<Object> draftsTableView;

    @FXML
    private TableColumn<Object, String> draftsTypeCol, draftsTimeCol, draftsNameCol;

    //INSTANCE VARIABLES FOR VISIT

    @FXML
    private AnchorPane overallAnchorPane;

    @FXML
    private VBox overallVBox, doctorVbox;

    @FXML
    private HBox visitAdmissionStatusHbox;

    @FXML
    private CheckBox visitCantDoubleVision, visitAbHurt, visitAcuteCheck, visitArmHurt, visitAsthmaCheck, visitBackHurt,
            visitBronchitisCheck, visitCantBlindness, visitCantBlurredVision, visitCantBreath, visitCantLosingHearing,
            visitCantMoveOne, visitCantPassBowel, visitCantPassUrine, visitCantRemember, visitCantRinging, visitCantSleep,
            visitCantSmell, visitCantSoundsTooLoud, visitCantSpeak, visitCantStopPassingWater, visitCantStopScratch,
            visitCantStopSweat, visitCantSwallow, visitCantTaste, visitCantWalk, visitCantWrite, visitChestHurt,
            visitChestPainCheck, visitChronicHurt, visitConstipationCheck, visitDizzinessCheck, visitEarHurt,
            visitEpiCheck, visitFeelChills, visitFeelFever, visitFeelLight, visitFeelParesthesia, visitFeelSleepy,
            visitFeverCheck, visitFluFeel, visitGastroCheck, visitHeadHurt, visitHeadInjuryCheck, visitHeadacheCheck,
            visitIMInjectionCheck, visitLegHurt, visitLowBackCheck, visitNauseaCheck, visitNauseatedFeel,
            visitPOCheck, visitOxygenCheck, visitPelvisHurt, visitRectumHurt, visitRespInfCheck, visitSCCheck,
            visitShortOfBreathFeel, visitSkinHurt, visitStrainBackCheck, visitStrainNeckCheck, visitSweatyFeel,
            visitSyncopeCheck, visitTeethCheck, visitTheRoomSpinFeel, visitThirstyFeel, visitTiredFeel, visitToothHurt,
            visitUTICheck, visitUnspecifiedAbCheck, visitVomitFeel, visitWeakFeel, visitAboutToBlackFeel;

    @FXML CheckBox testRedCheck, testWhiteCheck, testLiverCheck, testRenalCheck, testElectrolyteTest, testXrayCheck,
            testCTCheck, testMRICheck, testUrinaryCheck, testStoolCheck;
    @FXML TextField otherTestText;

    @FXML CheckBox diagnosisPneumoniaCheck, diagnosisChestCheck, diagnosisAcuteCereCheck, diagnosisCongestiveCheck,
            diagnosisGastrointestinalCheck, diagnosisCardiacCheck, diagnosisSepticemiaCheck, diagnosisBronchCheck,
            diagnosisPulmonaryCheck, diagnosisMyocardialCheck, diagnosisDiabetesCheck, diagnosisIntracranialCheck,
            diagnosisFoodCheck, diagnosisAbdomoinalCheck, diagnosisUrinaryCheck, diagnosisCardiacArrestCheck,
            diagnosisCovidCheck;
    @FXML TextField otherDiagnosisText;

    @FXML
    private RadioButton visitAdmissionNoRadio, visitAdmissionYesRadio;

    @FXML
    private TextField visitAlbuterolText, visitAspirinText, visitAtropineText, visitBloodPressureText, visitCantOtherText,
            visitDiphenText, visitEpinephrineText, visitFlumaText, visitGlucoseText, visitHydroText, visitIVText,
            visitLorazepamText, visitMorphineText, visitNalaxoneText, visitNameText, visitNitroText, visitOtherDiagnosisText,
            visitOtherFeel, visitOtherHurtText, visitOtherMedicationText, visitOtherMedicineDosageText, visitRoomNumberText;

    @FXML
    private DatePicker visitCheckinDate, visitDate, visitCheckoutDate;

    @FXML
    private TextArea visitNotes, doctorDischargeText, doctorNotes;

    @FXML
    private ChoiceBox<Object> visitPhysicianChoice;

    //INSTANCE VARIABLES FOR RECORDS
    @FXML
    TreeTableView<PatientRecords> recordsTable;
    @FXML TreeTableColumn recordPatientCol, recordNameCol, recordBirthdayCol, recordBillCol, recordsVisitCol, recordVisitDateCol, visitLastEditorCol;


    @FXML
    private ImageView Exit, menuSignOutButton;

    @FXML
    private AnchorPane slider, center, recordsCenter, draftCenter;

    @FXML
    private JFXButton dashBoardButton, addPatientButton, addVisitButton, draftsButton;

    @FXML private BorderPane borderpane;

    @FXML
    private VBox centerVbox, rightButtons;

    @FXML
    private ScrollPane visitCenter, patientCenter;

    private String currentTab = "", pregnant;
    private boolean menuOpen, editing;
    private PatientRegistrationForm editForm;

    @FXML
    public void calcBilling(ActionEvent actionevent) throws IOException{
        Stage primaryStage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader();

        fxmlloader.setLocation(getClass().getResource("billing-interface.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        BillingController billingController = fxmlloader.getController();

        billingController.getForm(getVisitForm());


        primaryStage.setTitle("Billing");
        primaryStage.getIcons().add(new Image(EmergencyNexus.class.getResourceAsStream("logo.png")));

        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.show();
    }

    @FXML
    void dashBoardButtonClicked(ActionEvent event) throws IOException {
        currentTab = "records";
        recordsCenter.toBack();
        recordsCenter.setVisible(true);
        patientCenter.setVisible(false);
        visitCenter.setVisible(false);
        draftCenter.setVisible(false);
        setMenuButtonsForms("records");
    }

    @FXML
    void addPatientButtonClicked(ActionEvent event) throws IOException {
        currentTab = "patient";
        patientCenter.toBack();
        patientCenter.setVisible(true);
        recordsCenter.setVisible(false);
        visitCenter.setVisible(false);
        draftCenter.setVisible(false);
        setMenuButtonsForms("forms");
    }

    @FXML
    void addVisitButtonClicked(ActionEvent event) throws IOException {
        currentTab = "visit";
        visitCenter.toBack();
        visitCenter.setVisible(true);
        recordsCenter.setVisible(false);
        patientCenter.setVisible(false);
        draftCenter.setVisible(false);
        setMenuButtonsForms("forms");
    }

    @FXML
    void draftsButtonClicked(ActionEvent event) throws IOException {
        currentTab = "drafts";
        draftCenter.toBack();
        draftCenter.setVisible(true);
        visitCenter.setVisible(false);
        recordsCenter.setVisible(false);
        patientCenter.setVisible(false);
        setMenuButtonsForms("drafts");
    }

    //GLOBAL VARIABLES
    private String accountType, accountHolderName, registerEthnicity, registerReligion, registerSex, registerSexuallyActive;
    private ConnectToDatabase db;
    private ObservableList<Object> draftList;
    CheckBox[] doctorTestChecks, doctorDiagnosisChecks;

    public void setAccountType(String accountType) {
        this.accountType = accountType;
        if (accountType.equals("nurse")) {
            visitPhysicianChoice.setValue(accountHolderName);
            for (CheckBox c : doctorTestChecks) {
                c.setDisable(true);
            }
            for (CheckBox c : doctorDiagnosisChecks) {
                c.setDisable(true);
            }
            otherTestText.setDisable(true);
            otherDiagnosisText.setDisable(true);
            doctorDischargeText.setDisable(true);
            doctorNotes.setDisable(true);
        }
    }

    public void setUsername(String username) throws SQLException {
        String getName = "SELECT * FROM users WHERE username = ?";
        PreparedStatement statement = db.getConnection().prepareStatement(getName);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            accountHolderName = result.getString("fullname");
        }

    }

    private void updateRecordsTable() throws SQLException {
//        TreeTableColumn<PatientRegistrationForm, String> recordNameCol = new TreeTableColumn<>("fullname");
//        TreeTableColumn<PatientRegistrationForm, String> recordBirthdayCol = new TreeTableColumn<>("birthdate");
//        TreeTableColumn<PatientVisitForm, String> recordsVisitCol = new TreeTableColumn<>("dateOfVisit");
        TreeItem<PatientRecords> root = new TreeItem<>(new PatientRecords());
        recordNameCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("fullname"));
        recordBirthdayCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("birthdate"));
        recordVisitDateCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("dateOfVisit"));
        visitLastEditorCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("lastEditedBy"));
        recordBillCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("totalBill"));

        String getPatientQuery = "SELECT * FROM patients";
        PreparedStatement statement = db.getConnection().prepareStatement(getPatientQuery);
        ResultSet result = statement.executeQuery();
        ArrayList<TreeItem> patientTreeList = new ArrayList<>();
        ArrayList<PatientRecords> patientRecordsList = new ArrayList<>();
        while (result.next()) {
            PatientRegistrationForm patient = new PatientRegistrationForm(
                    result.getString("fullname"),
                    result.getString("dateofbirth"),
                    result.getString("phonenumber"),
                    result.getString("homeaddress"),
                    result.getString("email"),
                    result.getString("guardianname"),
                    result.getString("guardianphonenumber"),
                    result.getString("bloodtype"),
                    result.getString("healthinsuranceprovider"),
                    result.getString("covidshot1date"),
                    result.getString("covidshot1type"),
                    result.getString("covidshot2date"),
                    result.getString("covidshot2type"),
                    result.getString("boosterdate"),
                    result.getString("boostertype"),
                    result.getString("allergies"),
                    result.getString("preexistingconditions"),
                    result.getString("priormedications"),
                    result.getString("historyofsubstance"),
                    result.getString("height"),
                    result.getString("weight"),
                    result.getString("race"),
                    result.getString("ethnicity"),
                    result.getString("religion"),
                    result.getString("pregnancy"),
                    result.getString("assignedbirthsex"),
                    result.getString("genderidentity"),
                    result.getString("pronouns"),
                    result.getString("sexualactivity")
            );
            PatientRecords patientRecords = new PatientRecords(patient);
            patientRecordsList.add(patientRecords);
            TreeItem<Object> patients = new TreeItem<>(patientRecords);
            patientTreeList.add(patients);
        }

        String getVisitQuery = "SELECT * FROM visit";
        PreparedStatement visitStatement = db.getConnection().prepareStatement(getVisitQuery);
        ResultSet rs = visitStatement.executeQuery();
        ArrayList<TreeItem> visitTreeList = new ArrayList<>();
        ArrayList<PatientRecords> recordsVisitList = new ArrayList<>();
        while (rs.next()) {
            PatientVisitForm visit = new PatientVisitForm(
                    getPatientFromID(rs.getInt("patient"))[0],
                    rs.getString("dateofvisit"),
                    rs.getString("symptomsifeel"),
                    rs.getString("symptomsmyhurt"),
                    rs.getString("symptomsicant"),
                    rs.getString("bloodpressure"),
                    rs.getString("admissionstatuscheckin"),
                    rs.getString("admissionstatuscheckout"),
                    rs.getString("admissionstatusroom"),
                    getPhysicianFromID(rs.getInt("primaryphysician")),
                    rs.getString("givenmedication"),
                    rs.getString("injectionsgiven"),
                    rs.getString("potentialdiagnosis"),
                    rs.getString("notesandobservations"),
                    rs.getString("docrequesttest"),
                    rs.getString("docdiagnosis"),
                    rs.getString("docdischargeinstructions"),
                    rs.getString("docnotesandobservations"),
                    rs.getString("lasteditedby")
            );
            visit.setPatient(rs.getInt("patient"));
            visit.setPrimaryPhysician(rs.getInt("primaryphysician"));
            PatientRecords visitRecord = new PatientRecords(visit);
            recordsVisitList.add(visitRecord);
            TreeItem<Object> visits = new TreeItem<>(visitRecord);
            visitTreeList.add(visits);
        }

        for (int i = 0; i < patientRecordsList.size(); i++) {
            for (int j = 0; j < recordsVisitList.size(); j++) {
                if ((patientRecordsList.get(i).getPatient().getValue("name").equals((getPatientFromID(recordsVisitList.get(j).getVisit().getPatient()))[0]) &&
                        (patientRecordsList.get(i).getPatient().getValue("birthdate").equals((getPatientFromID(recordsVisitList.get(j).getVisit().getPatient()))[1])))) {
                    patientTreeList.get(i).getChildren().add(new TreeItem<>(recordsVisitList.get(j)));
                }
            }
        }
//        TreeItem<PatientRegistrationForm> patient = new TreeItem<>(new PatientRegistrationForm())
        for (TreeItem patients : patientTreeList) {
            root.getChildren().add(patients);
        }
        root.setExpanded(true);
        recordsTable.setRoot(root);
        recordsTable.setShowRoot(false);
    }

    private String[] getPatientFromID(int id) throws SQLException {
        String getPatient = "SELECT * FROM patients WHERE idpatients = ?";
        PreparedStatement stm = db.getConnection().prepareStatement(getPatient);
        stm.setString(1, String.valueOf(id));
        ResultSet rs = stm.executeQuery();
        String patientName = "";
        String patientBirthdate = "";
        while (rs.next()) {
            patientName = rs.getString("fullname");
            patientBirthdate = rs.getString("dateofbirth");
        }
        return new String[] {patientName, patientBirthdate};
    }

    private String getPhysicianFromID(int id) throws SQLException {
        String getPatient = "SELECT * FROM users WHERE idusers = ?";
        PreparedStatement stm = db.getConnection().prepareStatement(getPatient);
        stm.setString(1, String.valueOf(id));
        ResultSet rs = stm.executeQuery();
        String physicianName = "";
        while (rs.next()) {
            physicianName = rs.getString("fullname");
        }
        return physicianName;
    }

    private String getTests() {
        ArrayList <String> tests = new ArrayList();
        for (int i = 0; i < doctorTestChecks.length; i++) {
            if (doctorTestChecks[i].isSelected()) {
                tests.add(doctorTestChecks[i].getText());
            }
        }
        if (!otherTestText.getText().equals("")) {
            tests.add("other: " + otherTestText.getText());
        }
        return tests.toString();
    }

    private String getDiagnosis() {
        ArrayList <String> diagnoses = new ArrayList();
        for (int i = 0; i < doctorDiagnosisChecks.length; i++) {
            if (doctorDiagnosisChecks[i].isSelected()) {
                diagnoses.add(doctorDiagnosisChecks[i].getText());
            }
        }
        if (!otherDiagnosisText.getText().equals("")) {
            diagnoses.add("other: " + otherDiagnosisText.getText());
        }
        return diagnoses.toString();
    }

    @FXML
    void menuSaveDraftButtonClicked(ActionEvent event) throws SQLException {
        if (currentTab.equals("patient")) { //Register Patient
            saveRegistrationDraft();
        } else { //Visit form
            saveVisitDraft();
        }
    }

    @FXML
    void menuClearFormButtonClicked (ActionEvent event) {
        if (currentTab.equals("patient")) { //Register Patient
            clearRegistration();
            registerNameText.setDisable(false);
        } else { //Visit form
            clearVisit();
        }
    }

    private void clearVisit() {
        visitNameText.setText("");
        visitDate.getEditor().setText("");
        visitCantDoubleVision.setSelected(false);
        visitAbHurt.setSelected(false);
        visitAcuteCheck.setSelected(false);
        visitArmHurt.setSelected(false);
        visitAsthmaCheck.setSelected(false);
        visitBackHurt.setSelected(false);
        visitBronchitisCheck.setSelected(false);
        visitCantBlindness.setSelected(false);
        visitCantBlurredVision.setSelected(false);
        visitCantBreath.setSelected(false);
        visitCantLosingHearing.setSelected(false);
        visitCantMoveOne.setSelected(false);
        visitCantPassBowel.setSelected(false);
        visitCantPassUrine.setSelected(false);
        visitCantRemember.setSelected(false);
        visitCantRinging.setSelected(false);
        visitCantSleep.setSelected(false);
        visitCantSmell.setSelected(false);
        visitCantSoundsTooLoud.setSelected(false);
        visitCantSpeak.setSelected(false);
        visitCantStopPassingWater.setSelected(false);
        visitCantStopScratch.setSelected(false);
        visitCantStopSweat.setSelected(false);
        visitCantSwallow.setSelected(false);
        visitCantTaste.setSelected(false);
        visitCantWalk.setSelected(false);
        visitCantWrite.setSelected(false);
        visitMouthFeel.setSelected(false);
        visitChestHurt.setSelected(false);
        visitChestPainCheck.setSelected(false);
        visitChronicHurt.setSelected(false);
        visitConstipationCheck.setSelected(false);
        visitDizzinessCheck.setSelected(false);
        visitIVText.setText("");
        visitEarHurt.setSelected(false);
        visitEpiCheck.setSelected(false);
        visitFeelChills.setSelected(false);
        visitFeelFever.setSelected(false);
        visitFeelLight.setSelected(false);
        visitFeelParesthesia.setSelected(false);
        visitFeelSleepy.setSelected(false);
        visitFeverCheck.setSelected(false);
        visitFluFeel.setSelected(false);
        visitGastroCheck.setSelected(false);
        visitHeadHurt.setSelected(false);
        visitHeadInjuryCheck.setSelected(false);
        visitHeadacheCheck.setSelected(false);
        visitIMInjectionCheck.setSelected(false);
        visitLegHurt.setSelected(false);
        visitLowBackCheck.setSelected(false);
        visitNauseaCheck.setSelected(false);
        visitNauseatedFeel.setSelected(false);
        visitPOCheck.setSelected(false);
        visitOxygenCheck.setSelected(false);
        visitPelvisHurt.setSelected(false);
        visitRectumHurt.setSelected(false);
        visitRespInfCheck.setSelected(false);
        visitSCCheck.setSelected(false);
        visitShortOfBreathFeel.setSelected(false);
        visitSkinHurt.setSelected(false);
        visitStrainBackCheck.setSelected(false);
        visitStrainNeckCheck.setSelected(false);
        visitSweatyFeel.setSelected(false);
        visitSyncopeCheck.setSelected(false);
        visitTeethCheck.setSelected(false);
        visitTheRoomSpinFeel.setSelected(false);
        visitThirstyFeel.setSelected(false);
        visitTiredFeel.setSelected(false);
        visitToothHurt.setSelected(false);
        visitUTICheck.setSelected(false);
        visitUnspecifiedAbCheck.setSelected(false);
        visitVomitFeel.setSelected(false);
        visitWeakFeel.setSelected(false);
        visitAboutToBlackFeel.setSelected(false);
        visitPhysicianChoice.setValue("");

        //GIVEN MEDICATIONS
        visitEpinephrineText.setText("");
        visitNitroText.setText("");
        visitDiphenText.setText("");
        visitAlbuterolText.setText("");
        visitAspirinText.setText("");
        visitGlucoseText.setText("");
        visitAtropineText.setText("");
        visitHydroText.setText("");
        visitMorphineText.setText("");
        visitNalaxoneText.setText("");
        visitLorazepamText.setText("");
        visitFlumaText.setText("");

        visitOtherDiagnosisText.setText("");
        visitOtherMedicineDosageText.setText("");
        visitOtherHurtText.setText("");
        visitCantOtherText.setText("");
        visitOtherFeel.setText("");
        registerOtherAllergyText.setText("");
        visitOtherMedicationText.setText("");
        visitNotes.setText("");
        visitBloodPressureText.setText("");
        visitCheckinDate.getEditor().setText("");
        visitCheckoutDate.getEditor().setText("");
        visitRoomNumberText.setText("");
        visitAdmissionYesRadio.setSelected(false);
        visitAdmissionNoRadio.setSelected(false);
        visitAdmissionStatusHbox.setVisible(false);

        for (CheckBox c : doctorTestChecks) {
            c.setSelected(false);
        }
        for (CheckBox c : doctorDiagnosisChecks) {
            c.setSelected(false);
        }
        doctorNotes.setText("");
        doctorDischargeText.setText("");
        otherTestText.setText("");
        otherDiagnosisText.setText("");
    }

    private void saveRegistrationDraft() throws SQLException {
        String errMsg;
        PatientRegistrationForm form = getRegistrationForm();
        if (checkRegistrationEmpty()) {
            alertFormEmpty(true);
        } else if (checkIfPatientExists(form)) {
            alertPatientAlreadyExists(true, "Draft");
        } else if (draftList.contains(form)) {
            Alert alreadyExists = new Alert(Alert.AlertType.ERROR);
            alreadyExists.setContentText("ERR: Draft already exists.");
            alreadyExists.showAndWait();
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();
            form.setTypeOfDraft("registration");
            form.setNameOfDraft(nameDraft());
            form.setTimeOfDraft(formatter.format(date));
            draftList.add(form);
            updateDraftTable();
            Alert draftSaved = new Alert(Alert.AlertType.CONFIRMATION);
            draftSaved.setContentText("Draft was successfully saved.");
            draftSaved.showAndWait();
        }
    }

    private void saveVisitDraft() throws SQLException {
        String errMsg;
        PatientVisitForm form = getVisitForm();
        if (checkVisitEmpty()) {
            alertFormEmpty(true);
        } else if(draftList.contains(form)) {
            Alert alreadyExists = new Alert(Alert.AlertType.ERROR);
            alreadyExists.setContentText("ERR: Draft already exists.");
            alreadyExists.showAndWait();
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();
            form.setTypeOfDraft("visit");
            form.setNameOfDraft(nameDraft());
            form.setTimeOfDraft(formatter.format(date));
            draftList.add(form);
            updateDraftTable();
            Alert draftSaved = new Alert(Alert.AlertType.CONFIRMATION);
            draftSaved.setContentText("Draft was successfully saved.");
            draftSaved.showAndWait();
        }
    }

    private void updateDraftTable() {
        draftsTypeCol.setCellValueFactory(new PropertyValueFactory<Object,String>("typeOfDraft"));
        draftsTimeCol.setCellValueFactory(new PropertyValueFactory<Object,String>("timeOfDraft"));
        draftsNameCol.setCellValueFactory(new PropertyValueFactory<Object,String>("nameOfDraft"));
        draftsTableView.setItems(draftList);
    }

    private String nameDraft () {
        String name = "";
        TextInputDialog dialog = new TextInputDialog("default name");

        dialog.setTitle("Name this draft");
        dialog.setHeaderText("Enter your name. This will be used to find this draft later on.");
        dialog.setContentText("Name: ");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            name = result.get();
        }
        return name;
    }

    @FXML
    void menuDeleteButtonClicked(ActionEvent event) throws SQLException {
        deleteButtonClicked();
    }

    private void deleteButtonClicked() throws SQLException {
        if (currentTab.equals("drafts")) {
            if ((draftsTableView.getSelectionModel().getSelectedItem().getClass().getName().contains("PatientRegistrationForm"))) {
                PatientRegistrationForm form = (PatientRegistrationForm) draftsTableView.getSelectionModel().getSelectedItem();
                draftList.remove(form);
                updateDraftTable();
            } else {
                PatientVisitForm form = (PatientVisitForm) draftsTableView.getSelectionModel().getSelectedItem();
                draftList.remove(form);
                updateDraftTable();
            }
        }
        if (currentTab.equals("records")) {
            PatientRecords record = recordsTable.getSelectionModel().getSelectedItem().getValue();
            if (record.getPatient() != null) { //It is a patient cell
                PatientRegistrationForm patient = record.getPatient();
                String name = patient.getValue("name");
                ButtonType no = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                ButtonType yes = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
                Alert deleteWarning = new Alert(Alert.AlertType.ERROR,"WARNING: This specific patient, " + name +  ", and all of their visit forms," +
                        " will be permanently removed from our records", yes, no);
                Optional<ButtonType> result = deleteWarning.showAndWait();
                if (result.isPresent() && result.get() == yes) {
                    int patientID = 0;
                    String getPatientID = "SELECT * FROM patients WHERE fullname = ? AND dateofbirth = ?";
                    PreparedStatement getPatient = db.getConnection().prepareStatement(getPatientID);
                    getPatient.setString(1, name);
                    getPatient.setString(2, patient.getValue("birthdate"));
                    ResultSet rs = getPatient.executeQuery();
                    while (rs.next()) {
                        patientID = rs.getInt("idpatients");
                    }

                    String getPatientQuery = "DELETE patients , visit FROM patients INNER JOIN visit " +
                            "WHERE patients.idpatients = visit.patient AND patients.idpatients = ?";
                    PreparedStatement smt = db.getConnection().prepareStatement(getPatientQuery);
                    smt.setString(1, String.valueOf(patientID));
                    if (smt.executeUpdate() == 0) {
                        getPatientQuery = "DELETE FROM patients WHERE patients.idpatients = ?";
                        smt = db.getConnection().prepareStatement(getPatientQuery);
                        smt.setString(1, String.valueOf(patientID));
                        if (smt.executeUpdate() == 0) {
                            Alert error = new Alert(Alert.AlertType.WARNING, "ERROR: could not delete this patient and the corresponding visit form(s)");
                            error.showAndWait();
                        }
                    }
                }
            } else { //It is a visit form
                PatientVisitForm visit = record.getVisit();
                String deleteForm = "DELETE FROM visit WHERE visit.patient = ? AND visit.dateofvisit = ? AND visit.symptomsifeel = ? AND " +
                        "visit.symptomsmyhurt = ? AND visit.symptomsicant = ? AND visit.bloodpressure = ? AND visit.admissionstatuscheckin = ? AND " +
                        "visit.admissionstatuscheckout = ? AND visit.admissionstatusroom = ? AND visit.primaryphysician = ? AND visit.givenmedication = ? AND " +
                        "visit.injectionsgiven = ? AND visit.potentialdiagnosis = ? AND visit.notesandobservations = ? AND visit.docrequesttest = ? AND " +
                        "visit.docdiagnosis = ? AND visit.docdischargeinstructions = ? AND visit.docnotesandobservations = ?";
                PreparedStatement smt = db.getConnection().prepareStatement(deleteForm);
                String all[] = visit.getAll();
                for (int i = 2; i <= all.length-1; i++) {
                    if (i != 10) {
                        smt.setString(i, all[i - 1]);
                    }
                }

                String getIDQuery = "SELECT * FROM users WHERE fullname = ?";
                PreparedStatement statement = db.getConnection().prepareStatement(getIDQuery);
                statement.setString(1, (visit.getValue("physicianName")));
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    visit.setPrimaryPhysician(result.getInt("idusers"));
                }

                String getPatientQuery = "SELECT * FROM patients WHERE fullname = ?";
                PreparedStatement statement2 = db.getConnection().prepareStatement(getPatientQuery);
                statement2.setString(1, visit.getValue("patientName"));
                ResultSet rs = statement2.executeQuery();
                while (rs.next()) {
                    visit.setPatient(rs.getInt("idpatients"));
                }
                smt.setInt(1, visit.getPatient());
                smt.setInt(10, visit.getPrimaryPhysician());

                int exe = smt.executeUpdate();
                if (exe == -1){
                    Alert error = new Alert(Alert.AlertType.WARNING, "ERROR: could not delete this visit form");
                    error.showAndWait();
                }
            }
            updateRecordsTable();
        }
    }

    @FXML
    void menuSubmitButtonClicked(ActionEvent event) throws SQLException {
        if (currentTab.equals("patient")) { //Register Patient
            submitRegisterForm();
        } else { //Visit form
            submitVisitForm();
        }
        updateRecordsTable();
    }

    private void submitVisitForm() throws SQLException {
        String errMsg, warningMsg;
        PatientVisitForm form = getVisitForm();
        if (checkVisitEmpty()) {
            alertFormEmpty(true);
        } else if((errMsg = checkVisitError(form)).length() != 0) {
            if (errMsg.contains("does not exist")) {
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                Alert formError = new Alert(Alert.AlertType.ERROR,errMsg + "\n Want to make a new patient record?", yes, no);
                Optional<ButtonType> result = formError.showAndWait();
                if (result.isPresent() && result.get() == yes) {
                    clearRegistration();
                    registerNameText.setText(visitNameText.getText());
                    dashBoardButton.fire();
                }
            }
            Alert formError = new Alert(Alert.AlertType.ERROR);
            formError.setContentText(errMsg);
            formError.showAndWait();
        } else if((warningMsg = checkVisitWarning()).length() != 0) {
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            Alert formError = new Alert(Alert.AlertType.ERROR,warningMsg + "\n Still want to submit?", yes, no);
            Optional<ButtonType> result = formError.showAndWait();
            if (result.isPresent() && result.get() == yes) {
                alertSubmitSuccess(submitVisitForm(form));
            }
        } else {
            alertSubmitSuccess(submitVisitForm(form));
        }
    }

    private String getAllergies() {
        ArrayList<String> allergiesList = new ArrayList<>();
        if (registerDrugAllergyCheck.isSelected() && !registerDrugAllergyText.getText().equals("")) {
            allergiesList.add("drug: " + registerDrugAllergyText.getText());
        } else if (registerDrugAllergyCheck.isSelected() && registerDrugAllergyText.getText().equals("")) {
            allergiesList.add("drug");
        }
        if (registerFoodAllergyCheck.isSelected()) {
            allergiesList.add("food");
        }
        if (registerInsectAllergyCheck.isSelected()) {
            allergiesList.add("insect");
        }
        if (registerLatexAllergyCheck.isSelected()) {
            allergiesList.add("latex");
        }
        if (registerMoldAllergyCheck.isSelected()) {
            allergiesList.add("mold");
        }
        if (registerPetAllergyCheck.isSelected()) {
            allergiesList.add("pet");
        }
        if (registerPollenAllergyCheck.isSelected()) {
            allergiesList.add("pollen");
        }
        if (!registerOtherAllergyText.getText().equals("")) {
            allergiesList.add("other: " + registerOtherAllergyText.getText());
        }
        return allergiesList.toString();
    }

    private String getPriorMedications() {
        ArrayList<String> medicationsList = new ArrayList<>();
        if (registerInsulinCheck.isSelected()) {
            medicationsList.add("insulin");
        }
        if (registerAntibioticsCheck.isSelected()) {
            medicationsList.add("antibiotics");
        }
        if (registerAntiCoagulantCheck.isSelected()) {
            medicationsList.add("anticoagulant");
        }
        if (registerIbuprofenCheck.isSelected()) {
            medicationsList.add("ibuprofen");
        }
        if (registerNaxCheck.isSelected()) {
            medicationsList.add("naloxone");
        }
        return medicationsList.toString();
    }

    private String getSubstanceUse() {
        ArrayList<String> substanceUse = new ArrayList<>();
        if (registerDrugHistoryCheck.isSelected()) {
            substanceUse.add("recreational drug use");
        }
        if (registerHistoryAlcoholCheck.isSelected()) {
            substanceUse.add("alcohol");
        }
        if (registerHistorySmokingCheck.isSelected()) {
            substanceUse.add("smoking");
        }
        return substanceUse.toString();
    }

    private void submitRegisterForm() throws SQLException {
        String errMsg, warningMsg;
        PatientRegistrationForm form = getRegistrationForm();
        if (editing) {
            registerNameText.setDisable(true);
            if (checkRegistrationEmpty()) {
                alertFormEmpty(true);
            } else if (
                    ((TextField)registerBirthDateCalendar.getEditor()).getText().equals(editForm.getValue("birthdate")) &&
                    registerPhoneText.getText().equals(editForm.getValue("phone")) && registerAddressText.getText().equals(editForm.getValue("home")) && registerEmailText.getText().equals(editForm.getValue("email")) &&
                    registerGuardianNameText.getText().equals(editForm.getValue("guardianName")) && registerGuardianPhoneText.getText().equals(editForm.getValue("guardianPhone")) && registerBloodTypeChoiceBox.getValue().equals(editForm.getValue("bloodType")) &&
                    registerInsuranceChoice.getValue().equals(editForm.getValue("insurance")) && ((TextField)registerCovidPrimaryVaccineCalendar.getEditor()).getText().equals(editForm.getValue("covid1date")) &&
                    registerCovidPrimaryVaccineChoice.getValue().equals(editForm.getValue("covid1Type")) && ((TextField)registerCovidSecondaryVaccineCalendar.getEditor()).getText().equals(editForm.getValue("covid2date")) &&
                    registerCovidSecondaryVaccineChoice.getValue().equals(editForm.getValue("covid2Type")) && ((TextField)registerCovidBoosterVaccineCalendar.getEditor()).getText().equals(editForm.getValue("covid3date")) &&
                    registerCovidBoosterVaccineChoice.getValue().equals(editForm.getValue("covid3Type")) && getAllergies().equals(editForm.getValue("allergies")) && registerPreConditionsText.getText().equals(editForm.getValue("preexistingConditions")) &&
                    getPriorMedications().equals(editForm.getValue("medications")) && getSubstanceUse().equals(editForm.getValue("history")) && registerHeightText.getText().equals(editForm.getValue("height")) &&
                    registerWeightText.getText().equals(editForm.getValue("weight")) && registerRaceChoice.getValue().equals(editForm.getValue("race")) && registerEthnicity.equals(editForm.getValue("ethnicity")) &&
                    registerReligion.equals(editForm.getValue("religion")) && pregnant.equals(editForm.getValue("pregnancy")) && registerSex.equals(editForm.getValue("sex")) && registerGenderChoice.getValue().equals(editForm.getValue("gender")) &&
                    registerPronounsText.getText().equals(editForm.getValue("pronouns")) && registerSexuallyActive.equals(editForm.getValue("sexualActivity")))
            {
                Alert nothingInForm = new Alert(Alert.AlertType.CONFIRMATION);
                nothingInForm.setContentText("ERR: Nothing has been changed. Update will not be saved.");
                nothingInForm.showAndWait();
            } else {
                String updateQuery = "UPDATE patients set fullname = ?, dateofbirth = ?, phonenumber = ?, homeaddress = ?, email = ?, guardianname = ?," +
                        " guardianphonenumber = ?, bloodtype = ?, healthinsuranceprovider = ?, covidshot1date = ?, covidshot1type = ?, covidshot2date = ?," +
                        " covidshot2type = ?, boosterdate = ?, boostertype = ?, allergies = ?, preexistingconditions = ?, priormedications = ?, historyofsubstance = ?," +
                        " height = ?, weight = ?, race = ?, ethnicity = ?, religion = ?, pregnancy = ?, assignedbirthsex = ?, genderidentity = ?, pronouns = ?, sexualactivity = ?" +
                        " WHERE fullname = ? AND dateofbirth = ?";
                PreparedStatement smt = db.getConnection().prepareStatement(updateQuery);
                String all[] = form.getAll();

                for (int i = 1; i <= all.length; i++) {
                    smt.setString(i, all[i-1]);
                }
                smt.setString(all.length + 1, editForm.getValue("name"));
                smt.setString(all.length + 2, editForm.getValue("birthdate"));
                int rs = smt.executeUpdate();
                boolean worked = false;
                if(rs != 0) {
                    Alert workedAlert = new Alert(Alert.AlertType.CONFIRMATION, form.getValue("name") + " was successfully updated");
                    workedAlert.showAndWait();
                    updateRecordsTable();
                    menuClearFormButton.fire();
                    editing = false;
                }
            }
        } else {
            if (checkRegistrationEmpty()) {
                alertFormEmpty(true);
            } else if(checkIfPatientExists(form)) {
                alertPatientAlreadyExists(true, "Patient Form");
            } else if((errMsg = checkRegistrationError()).length() != 0) {
                Alert formError = new Alert(Alert.AlertType.ERROR);
                formError.setContentText(errMsg);
                formError.showAndWait();
            } else if((warningMsg = checkRegistrationWarning()).length() != 0) {
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                Alert formError = new Alert(Alert.AlertType.ERROR,warningMsg + "\n Still want to submit?", yes, no);
                Optional<ButtonType> result = formError.showAndWait();
                if (result.isPresent() && result.get() == yes) {
                    alertSubmitSuccess(submitRegistrationForm(form));
                }
            } else {
                alertSubmitSuccess(submitRegistrationForm(form));
            }
        }
    }

    private void alertFormEmpty(boolean formIsEmpty) {
        if (formIsEmpty) {
            Alert formEmpty = new Alert(Alert.AlertType.ERROR);
            formEmpty.setContentText("ERR: form is empty. Nothing will happen.");
            formEmpty.showAndWait();
        } else {
            return;
        }

    }

    private void alertPatientAlreadyExists(boolean patientExists, String type) {
        if (patientExists) {
            Alert accountAlreadyExists = new Alert(Alert.AlertType.CONFIRMATION);
            accountAlreadyExists.setContentText("ERR: That patient already exists in record. " + type + " will not be saved.");
            accountAlreadyExists.showAndWait();
        } else {
            return;
        }
    }

    private void alertPatientDoesNotExists(boolean patientDoesNot) {
        if (patientDoesNot) {
            Alert accountAlreadyExists = new Alert(Alert.AlertType.CONFIRMATION);
            accountAlreadyExists.setContentText("ERR: This patient does not exist in Record. Draft will not be saved.");
            accountAlreadyExists.showAndWait();
        } else {
            return;
        }
    }

    private void alertSubmitSuccess(boolean formIsSubmitted) {
        Alert formSubmitted = new Alert(Alert.AlertType.INFORMATION);
        if (formIsSubmitted) {
            formSubmitted.setContentText("Form was successfully submitted");
            menuClearFormButton.fire();
            dashBoardButton.fire();
        } else {
            formSubmitted.setContentText("Form was unsuccessfully submitted");
        }
        formSubmitted.showAndWait();
    }

    private boolean checkIfPatientExists(PatientRegistrationForm patient) throws SQLException {
        String checkQuery = "SELECT * FROM patients WHERE fullname = ? AND dateofbirth = ?";
        PreparedStatement smt = db.getConnection().prepareStatement(checkQuery);
        smt.setString(1, patient.getValue("Name"));
        smt.setString(2, patient.getValue("birthdate"));
        ResultSet rs = smt.executeQuery();
        boolean patientExists = false;
        while (rs.next()) {
            patientExists = true;
        }
        return patientExists;
    }

    private boolean submitRegistrationForm(PatientRegistrationForm patient) throws SQLException {
        String insertQuery = "INSERT INTO patients (fullname, dateofbirth, phonenumber, homeaddress, email, guardianname," +
                " guardianphonenumber, bloodtype, healthinsuranceprovider, covidshot1date, covidshot1type, covidshot2date," +
                " covidshot2type, boosterdate, boostertype, allergies, preexistingconditions, priormedications, historyofsubstance," +
                " height, weight, race, ethnicity, religion, pregnancy, assignedbirthsex, genderidentity, pronouns, sexualactivity)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement smt = db.getConnection().prepareStatement(insertQuery);
        String all[] = patient.getAll();

        for (int i = 1; i <= all.length; i++) {
            smt.setString(i, all[i-1]);
        }
        int rs = smt.executeUpdate();
        boolean worked = false;
        if(rs != 0) {
            worked = true;
        }
        return worked;
    }

    private boolean submitVisitForm(PatientVisitForm visit) throws SQLException {
        String insertQuery = "INSERT INTO visit (patient, dateofvisit,  symptomsmyhurt,  symptomsifeel,  symptomsicant,  " +
                "bloodpressure, admissionstatuscheckin,  admissionstatuscheckout,  admissionstatusroom, " +
                "primaryphysician,  givenmedication,  injectionsgiven, potentialdiagnosis,  notesandobservations, " +
                "docrequesttest, docdiagnosis, docdischargeinstructions, docnotesandobservations, lasteditedby)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement smt = db.getConnection().prepareStatement(insertQuery);
        String all[] = visit.getAll();
        for (int i = 2; i <= all.length; i++) {
            if (i != 10) {
                smt.setString(i, all[i - 1]);
            }
        }

        int physicianId = 0;

        String getIDQuery = "SELECT * FROM users WHERE fullname = ?";
        PreparedStatement statement = db.getConnection().prepareStatement(getIDQuery);
        statement.setString(1, (visit.getValue("physicianName")));
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            visit.setPrimaryPhysician(result.getInt("idusers"));
        }

        smt.setInt(1, visit.getPatient());
        smt.setInt(10, visit.getPrimaryPhysician());
        int rs = smt.executeUpdate();
        boolean worked = false;
        if(rs != 0) {
            worked = true;
        }
        return worked;
    }

    private String checkRegistrationError() {
        String errorMessage = "";
        if (registerNameText.getText().equals("")) {
            errorMessage += "ERR: patient name not given.\n";
        }
        if (((TextField)registerBirthDateCalendar.getEditor()).getText().equals("")) {
            errorMessage += "ERR: patient birth date not given.\n";
        }
        return errorMessage;
    }

    private String checkVisitError(PatientVisitForm visit) throws SQLException {
        String errorMessage = "";
        if (visitNameText.getText().equals("")) {
            errorMessage += "ERR: patient name not given.\n";
        } else {
            String getIDQuery = "SELECT * FROM patients WHERE fullname = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(getIDQuery);
            statement.setString(1, visitNameText.getText());
            ResultSet result = statement.executeQuery();
            boolean patientExists = false;
            while (result.next()) {
                patientExists = true;
                visit.setPatient(result.getInt("idpatients"));
            }
            if (!patientExists) {
                errorMessage += "ERR: " + visitNameText.getText() + " does not exist.\n";
            }
        }
        if (((TextField) visitDate.getEditor()).getText().equals("")) {
            errorMessage += "ERR: visit date not given.\n";
        }
        return errorMessage;
    }

    private String checkRegistrationWarning() {
        String warningMessage = "";
        if (registerPhoneText.getText().equals("")) {
            warningMessage += "WARNING: patient phone number not given.\n";
        }
        if (registerAddressText.getText().equals("")) {
            warningMessage += "WARNING: patient home address not given.\n";
        }
        if (registerEmailText.getText().equals("")) {
            warningMessage += "WARNING: patient email not given.\n";
        }
        if (registerGuardianNameText.getText().equals("")) {
            warningMessage += "WARNING: patient guardian name not given.\n";
        }
        if (registerGuardianPhoneText.getText().equals("")) {
            warningMessage += "WARNING: patient guardian phone number not given.\n";
        }
        if (registerBloodTypeChoiceBox.getValue() == null) {
            warningMessage += "WARNING: patient blood type not given.\n";
        }
        if (registerInsuranceChoice.getValue() == null) {
            warningMessage += "WARNING: patient insurance not given.\n";
        }
        if (((TextField)registerCovidPrimaryVaccineCalendar.getEditor()).getText().equals("")) {
            warningMessage += "WARNING: patient covid shot 1 date not given.\n";
        }
        if (registerCovidPrimaryVaccineChoice.getValue() == null) {
            warningMessage += "WARNING: patient covid shot 1 type not given.\n";
        }
        if (((TextField)registerCovidSecondaryVaccineCalendar.getEditor()).getText().equals("")) {
            warningMessage += "WARNING: patient covid shot 2 date not given.\n";
        }
        if (registerCovidSecondaryVaccineChoice.getValue() == null) {
            warningMessage += "WARNING: patient covid shot 2 type not given.\n";
        }
        if (((TextField)registerCovidBoosterVaccineCalendar.getEditor()).getText().equals("")) {
            warningMessage += "WARNING: patient booster date not given.\n";
        }
        if (registerCovidBoosterVaccineChoice.getValue() == null) {
            warningMessage += "WARNING: patient booster type not given.\n";
        }
        if (getAllergies().equals("[]")) {
            warningMessage += "WARNING: patient allergies not given.\n";
        }
        if (registerPreConditionsText.getText().equals("")) {
            warningMessage += "WARNING: patient pre-medical conditions not given.\n";
        }
        if (getPriorMedications().equals("[]")) {
            warningMessage += "WARNING: patient prior medications not given.\n";
        }
        if (getSubstanceUse().equals("[]")) {
            warningMessage += "WARNING: patient substance history not given.\n";
        }
        if (registerHeightText.getText().equals("")) {
            warningMessage += "WARNING: patient height not given.\n";
        }
        if (registerWeightText.getText().equals("")) {
            warningMessage += "WARNING: patient weight not given.\n";
        }
        if (registerRaceChoice.getValue() == null) {
            warningMessage += "WARNING: patient race not given.\n";
        }
        if (registerEthnicity == null) {
            warningMessage += "WARNING: patient ethnicity not given.\n";
        }
        if (registerReligion == null) {
            warningMessage += "WARNING: patient religion not given.\n";
        }
        if (registerSex == null) {
            warningMessage += "WARNING: patient birth sex not given.\n";
        }
        if (registerGenderChoice.getValue() == null) {
            warningMessage += "WARNING: patient gender not given.\n";
        }
        if (registerPronounsText.getText().equals("")) {
            warningMessage += "WARNING: patient pronouns not given.\n";
        }
        if (registerSexuallyActive == null) {
            warningMessage += "WARNING: patient sexual activity not given.\n";
        }
        return warningMessage;
    }

    private String checkVisitWarning() {
        String warningMsg = "";

        if (getHurt().equals("[]")) {
            warningMsg += "WARNING: hurt symptoms not given\n";
        }
        if (getFeel().equals("[]")) {
            warningMsg += "WARNING: feel symptoms not given\n";
        }
        if (getCant().equals("[]")) {
            warningMsg += "WARNING: cant symptoms not given\n";
        }
        if (visitBloodPressureText.getText().equals("")) {
            warningMsg += "WARNING: blood pressure not given\n";
        }
        if (!visitAdmissionYesRadio.isPressed() && !visitAdmissionNoRadio.isPressed()) {
            warningMsg += "WARNING: admission not given\n";
        } else if (visitCheckinDate.getEditor().getText().equals("")) {
            warningMsg += "WARNING: check-in date not given\n";
        } else if (visitCheckoutDate.getEditor().getText().equals("")) {
            warningMsg += "WARNING: check-out date not given\n";
        } else if (visitRoomNumberText.getText().equals("")) {
            warningMsg += "WARNING: room number not given\n";
        }
        if (visitPhysicianChoice.getValue() == null) {
            warningMsg += "WARNING: physician not given\n";
        }
        if (getMedications().equals("[]")) {
            warningMsg += "WARNING: medications not given\n";
        }
        if (getInjection().equals("[]")) {
            warningMsg += "WARNING: injections not given\n";
        }
        if (getPossibleDiagnosis().equals("[]")) {
            warningMsg += "WARNING: possible diagnosis not given\n";
        }
        if (visitNotes.getText().equals("")) {
            warningMsg += "WARNING: visit notes not given\n";
        }
        if (accountType.equals("doctor")) {
            if (getTests().equals("[]")) {
                warningMsg += "WARNING: no tests selected\n";
            }
            if (getDiagnosis().equals("[]")) {
                warningMsg += "WARNING: no diagnoses given\n";
            }
            if (doctorDischargeText.getText().equals("")) {
                warningMsg += "WARNING: discharge notes not given\n";
            }
            if (doctorNotes.getText().equals("")) {
                warningMsg += "WARNING: no additional doctor notes not given\n";
            }
        }

        return warningMsg;
    }

    private PatientRegistrationForm getRegistrationForm() {
        pregnant = "no";
        if (registerPregnantRadio.isPressed()) {
            pregnant = "yes";
        }
        return new PatientRegistrationForm(
                registerNameText.getText(), ((TextField)registerBirthDateCalendar.getEditor()).getText(), registerPhoneText.getText(),
                registerAddressText.getText(), registerEmailText.getText(), registerGuardianNameText.getText(),
                registerGuardianPhoneText.getText(), (String) registerBloodTypeChoiceBox.getValue(), (String) registerInsuranceChoice.getValue(),
                ((TextField)registerCovidPrimaryVaccineCalendar.getEditor()).getText(), (String) registerCovidPrimaryVaccineChoice.getValue(),
                ((TextField)registerCovidSecondaryVaccineCalendar.getEditor()).getText(), (String) registerCovidSecondaryVaccineChoice.getValue(),
                ((TextField)registerCovidBoosterVaccineCalendar.getEditor()).getText(), (String) registerCovidBoosterVaccineChoice.getValue(),
                getAllergies(), registerPreConditionsText.getText(), getPriorMedications(), getSubstanceUse(), registerHeightText.getText(),
                registerWeightText.getText(), (String) registerRaceChoice.getValue(), registerEthnicity, registerReligion,
                pregnant, registerSex, (String) registerGenderChoice.getValue(),
                registerPronounsText.getText(), registerSexuallyActive);
    }

    private PatientVisitForm getVisitForm() {
        return new PatientVisitForm(visitNameText.getText(),
                        visitDate.getEditor().getText(), getHurt(), getFeel(), getCant(),
                        visitBloodPressureText.getText(),
                        visitCheckinDate.getEditor().getText(),
                        visitCheckoutDate.getEditor().getText(),
                        visitRoomNumberText.getText(), (String) visitPhysicianChoice.getValue(),
                        getMedications(), getInjection(), getPossibleDiagnosis(),
                        visitNotes.getText(), getTests(), getDiagnosis(),
                        doctorDischargeText.getText(), doctorNotes.getText(), accountHolderName

        );
    }

    private boolean checkRegistrationEmpty() {
       return (registerNameText.getText().equals("") && ((TextField)registerBirthDateCalendar.getEditor()).getText().equals("") &&
               registerPhoneText.getText().equals("") && registerAddressText.getText().equals("") && registerEmailText.getText().equals("") &&
               registerGuardianNameText.getText().equals("") && registerGuardianPhoneText.getText().equals("") && registerBloodTypeChoiceBox.getValue() == null &&
               registerInsuranceChoice.getValue() == null && ((TextField)registerCovidPrimaryVaccineCalendar.getEditor()).getText().equals("") &&
               registerCovidPrimaryVaccineChoice.getValue() == null && ((TextField)registerCovidSecondaryVaccineCalendar.getEditor()).getText().equals("") &&
               registerCovidSecondaryVaccineChoice.getValue() == null && ((TextField)registerCovidBoosterVaccineCalendar.getEditor()).getText().equals("") &&
               registerCovidBoosterVaccineChoice.getValue() == null && getAllergies().equals("[other: ]") && registerPreConditionsText.getText().equals("") &&
               getPriorMedications().equals("[]") && getSubstanceUse().equals("[]") && registerHeightText.getText().equals("") &&
               registerWeightText.getText().equals("") && registerRaceChoice.getValue() == null && registerEthnicity.equals("") &&
               registerReligion.equals("") && !registerPregnantRadio.isPressed() && registerSex.equals("") && registerGenderChoice.getValue() == null &&
               registerPronounsText.getText().equals("") && registerSexuallyActive.equals(""));
    }

    private String getHurt() {
        ArrayList<String> hurts = new ArrayList<>();
        if (visitAbHurt.isSelected()) {
            hurts.add("abdomen");
        }
        if (visitBackHurt.isSelected()) {
            hurts.add("back");
        }
        if (visitChestHurt.isSelected()) {
            hurts.add("chest");
        }
        if (visitEarHurt.isSelected()) {
            hurts.add("ear");
        }
        if (visitHeadHurt.isSelected()) {
            hurts.add("head");
        }
        if (visitPelvisHurt.isSelected()) {
            hurts.add("pelvis");
        }
        if (visitToothHurt.isSelected()) {
            hurts.add("tooth");
        }
        if (visitRectumHurt.isSelected()) {
            hurts.add("rectum");
        }
        if (visitSkinHurt.isSelected()) {
            hurts.add("skin");
        }
        if (visitLegHurt.isSelected()) {
            hurts.add("leg");
        }
        if (visitArmHurt.isSelected()) {
            hurts.add("arm");
        }
        if (visitChronicHurt.isSelected()) {
            hurts.add("chronic pain");
        }
        hurts.add(visitOtherHurtText.getText());
        return hurts.toString();
    }

    private String getFeel() {
        ArrayList<String> feel = new ArrayList<>();
        if (visitFeelChills.isSelected()) {
            feel.add("chills");
        }
        if (visitFeelFever.isSelected()) {
            feel.add("fever");
        }
        if (visitFeelParesthesia.isSelected()) {
            feel.add("paresthesia");
        }
        if (visitFeelLight.isSelected()) {
            feel.add("light-headed");
        }
        if (visitAboutToBlackFeel.isSelected()) {
            feel.add("dizzy: about to black out");
        }
        if (visitTheRoomSpinFeel.isSelected()) {
            feel.add("dizzy: the room is spinning");
        }
        if (visitMouthFeel.isSelected()) {
            feel.add("mouth dry");
        }
        if (visitNauseatedFeel.isSelected()) {
            feel.add("nauseated");
        }
        if (visitShortOfBreathFeel.isSelected()) {
            feel.add("short of breath");
        }
        if (visitFluFeel.isSelected()) {
            feel.add("sick: like I have the flu");
        }
        if (visitVomitFeel.isSelected()) {
            feel.add("sick: like I have to vomit");
        }
        if (visitFeelSleepy.isSelected()) {
            feel.add("sleepy");
        }
        if (visitSweatyFeel.isSelected()) {
            feel.add("sweaty");
        }
        if (visitThirstyFeel.isSelected()) {
            feel.add("thirsty");
        }
        if (visitTiredFeel.isSelected()) {
            feel.add("tired");
        }
        if (visitWeakFeel.isSelected()) {
            feel.add("weak");
        }
        feel.add(visitOtherFeel.getText());
        return feel.toString();
    }

    private String getCant() {
        ArrayList<String> cant = new ArrayList<>();
        if (visitCantBreath.isSelected()) {
            cant.add("breathe");
        }
        if (visitCantLosingHearing.isSelected()) {
            cant.add("hear: losing hearing");
        }
        if (visitCantSoundsTooLoud.isSelected()) {
            cant.add("hear: sounds are too loud");
        }
        if (visitCantRinging.isSelected()) {
            cant.add("hear: sounds are ringing");
        }
        if (visitCantMoveOne.isSelected()) {
            cant.add("move one side (arm and or leg)");
        }
        if (visitCantPassBowel.isSelected()) {
            cant.add("pass a bowel action normally");
        }
        if (visitCantPassUrine.isSelected()) {
            cant.add("pass urine normally");
        }
        if (visitCantRemember.isSelected()) {
            cant.add("remember");
        }
        if (visitCantBlindness.isSelected()) {
            cant.add("see: blindness");
        }
        if (visitCantDoubleVision.isSelected()) {
            cant.add("see: double vision");
        }
        if (visitCantBlurredVision.isSelected()) {
            cant.add("see: blurred vision");
        }
        if (visitCantSleep.isSelected()) {
            cant.add("sleep");
        }
        if (visitCantSmell.isSelected()) {
            cant.add("smell things normally");
        }
        if (visitCantSpeak.isSelected()) {
            cant.add("speak normally");
        }
        if (visitCantStopPassingWater.isSelected()) {
            cant.add("passing watery bowl actions");
        }
        if (visitCantStopScratch.isSelected()) {
            cant.add("scratching");
        }
        if (visitCantStopSweat.isSelected()) {
            cant.add("sweating");
        }
        if (visitCantSwallow.isSelected()) {
            cant.add("swallow normally");
        }
        if (visitCantTaste.isSelected()) {
            cant.add("taste properly");
        }
        if (visitCantWalk.isSelected()) {
            cant.add("walk normally");
        }
        if (visitCantWrite.isSelected()) {
            cant.add("write normally");
        }
        cant.add(visitCantOtherText.getText());
        return cant.toString();
    }

    private String getMedications() {
        ArrayList<String> medications = new ArrayList<>();
        if (visitOxygenCheck.isSelected()) {
            medications.add("oxygen");
        }
        if (visitEpinephrineText.getText().length() != 0) {
            medications.add("Epinephrine (mg): " + visitEpinephrineText.getText());
        }
        if (visitNitroText.getText().length() != 0) {
            medications.add("Nitroglycerin (mg): " + visitNitroText.getText());
        }
        if (visitDiphenText.getText().length() != 0) {
            medications.add("Diphenhydramine (mg): " + visitDiphenText.getText());
        }
        if (visitAlbuterolText.getText().length() != 0) {
            medications.add("Albuterol/salbutamol (mg): " + visitAlbuterolText.getText());
        }
        if (visitAspirinText.getText().length() != 0) {
            medications.add("Aspirin (mg): " + visitAspirinText.getText());
        }
        if (visitGlucoseText.getText().length() != 0) {
            medications.add("Glucose (mg): " + visitGlucoseText.getText());
        }
        if (visitAtropineText.getText().length() != 0) {
            medications.add("Atropine (mg): " + visitAtropineText.getText());
        }
        if (visitHydroText.getText().length() != 0) {
            medications.add("Hydrocortisone (mg): " + visitHydroText.getText());
        }
        if (visitMorphineText.getText().length() != 0) {
            medications.add("Morphine or nitrous oxide (mg): " + visitMorphineText.getText());
        }
        if (visitNalaxoneText.getText().length() != 0) {
            medications.add("Nalaxone (mg): " + visitNalaxoneText.getText());
        }
        if (visitLorazepamText.getText().length() != 0) {
            medications.add("Lorazepam or Midazolam (mg): " + visitLorazepamText.getText());
        }
        if (visitFlumaText.getText().length() != 0) {
            medications.add("Flumazenil (mg): " + visitFlumaText.getText());
        }
        if (visitOtherMedicationText.getText().length() != 0) {
            medications.add(visitOtherMedicationText.getText() + " (mg): " + visitOtherMedicineDosageText.getText());
        }
        return medications.toString();
    }

    private String getInjection() {
        ArrayList<String> injections = new ArrayList<>();
        if (visitIMInjectionCheck.isSelected()) {
            injections.add("Intramuscular injection (IM)");
        }
        if (!visitIVText.getText().equals("")) {
            injections.add("Intravascular injection (IV): " + visitIVText.getText());
        }
        if (visitSCCheck.isSelected()) {
            injections.add("Subcutaneous Injection (SC)");
        }
        if (visitPOCheck.isSelected()) {
            injections.add("P.O (Oral medication)");
        }
        return injections.toString();
    }

    private String getPossibleDiagnosis() {
        ArrayList <String> possibleDiagnosis = new ArrayList<>();
        if (visitChestHurt.isSelected()) {
            possibleDiagnosis.add("Other chest pain; chest pain unspecified");
        }
        if (visitRespInfCheck.isSelected()) {
            possibleDiagnosis.add("Acute upper respiratory infection; unspecified");
        }
        if (visitUTICheck.isSelected()) {
            possibleDiagnosis.add("Urinary tract infection; site not specified");
        }
        if (visitHeadacheCheck.isSelected()) {
            possibleDiagnosis.add("Headache");
        }
        if (visitUnspecifiedAbCheck.isSelected()) {
            possibleDiagnosis.add("Unspecified abdominal pain");
        }
        if (visitSyncopeCheck.isSelected()) {
            possibleDiagnosis.add("Syncope and collapse");
        }
        if (visitGastroCheck.isSelected()) {
            possibleDiagnosis.add("Non-infective gastroenteritis and colitis; unspecified");
        }
        if (visitDizzinessCheck.isSelected()) {
            possibleDiagnosis.add("Dizziness and giddiness");
        }
        if (visitLowBackCheck.isSelected()) {
            possibleDiagnosis.add("Low back pain");
        }
        if (visitHeadInjuryCheck.isSelected()) {
            possibleDiagnosis.add("Unspecified injury of head; initial encounter");
        }
        if (visitNauseaCheck.isSelected()) {
            possibleDiagnosis.add("Nausea with vomiting; unspecified");
        }
        if (visitAcuteCheck.isSelected()) {
            possibleDiagnosis.add("Acute pharyngitis; unspecified");
        }
        if (visitAsthmaCheck.isSelected()) {
            possibleDiagnosis.add("Unspecified asthma with (acute) exacerbation");
        }
        if (visitConstipationCheck.isSelected()) {
            possibleDiagnosis.add("Constipation; unspecified");
        }
        if (visitBronchitisCheck.isSelected()) {
            possibleDiagnosis.add("Acute bronchitis; unspecified");
        }
        if (visitStrainNeckCheck.isSelected()) {
            possibleDiagnosis.add("Strain of muscle; fascia and tendon at neck level; initial encounter");
        }
        if (visitFeverCheck.isSelected()) {
            possibleDiagnosis.add("Fever: unspecified");
        }
        if (visitTeethCheck.isSelected()) {
            possibleDiagnosis.add("Other specified disorders of teeth and supporting structures");
        }
        if (visitEpiCheck.isSelected()) {
            possibleDiagnosis.add("Epigastric pain");
        }
        if (visitStrainBackCheck.isSelected()) {
            possibleDiagnosis.add("Strain of muscle; fascia and tendon of lower back; initial encounter");
        }
        if (visitOtherDiagnosisText.getText().length() != 0) {
            possibleDiagnosis.add(visitOtherDiagnosisText.getText());
        }
        return possibleDiagnosis.toString();
    }

    private boolean checkVisitEmpty() {
        return (visitNameText.getText().equals("") && visitDate.getEditor().getText().equals("") && getHurt().equals("[]") &&
                getFeel().equals("[]") && getCant().equals("[]") && visitBloodPressureText.getText().equals("") &&
                visitCheckinDate.getEditor().getText().equals("") && !visitAdmissionYesRadio.isPressed() &&
                visitCheckinDate.getEditor().getText().equals("") && !visitAdmissionNoRadio.isPressed() &&
                visitCheckoutDate.getEditor().getText().equals("") && visitRoomNumberText.getText().equals("") &&
                visitPhysicianChoice.getValue() == null && getMedications().equals("[]") && getInjection().equals("[]") &&
                getPossibleDiagnosis().equals("[]") && visitNotes.getText().equals("") && getTests().equals("[]") &&
                getDiagnosis().equals("[]") && doctorDischargeText.getText().equals("") && doctorNotes.getText().equals(""));
    }

    @FXML
    void menuOpenFormButtonClicked(ActionEvent event) {
        if (currentTab.equals("records")) {
            openRecordsForm();
            if (editing) {
                registerNameText.setDisable(true);
            } else {
                registerNameText.setDisable(false);
            }
        } else {
            openDraftsForm();
        }
    }

    @FXML
    void draftsTableViewClicked(MouseEvent event) {
        ContextMenu cm = new ContextMenu();
        MenuItem open = new MenuItem("Open");
        cm.getItems().add(open);
        MenuItem delete = new MenuItem("Delete");
        cm.getItems().add(delete);
        if(event.getButton() == MouseButton.SECONDARY) {
            draftsTableView.setContextMenu(cm);
            open.setOnAction((cmEvent) -> {
                menuOpenFormButton.fire();
            });
            delete.setOnAction((cmEvent) -> {
                try {
                    deleteButtonClicked();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @FXML
    void recordsTableClicked(MouseEvent event) {
        ContextMenu cm = new ContextMenu();
        MenuItem open = new MenuItem("Open");
        cm.getItems().add(open);
        MenuItem delete = new MenuItem("Delete");
        cm.getItems().add(delete);
        MenuItem edit = new MenuItem("Edit Patient");
        cm.getItems().add(edit);
        if(event.getButton() == MouseButton.SECONDARY) {
            recordsTable.setContextMenu(cm);
            open.setOnAction((cmEvent) -> {
                menuOpenFormButton.fire();
            });
            delete.setOnAction((cmEvent) -> {
                try {
                    deleteButtonClicked();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            edit.setOnAction((cmEvent) -> {
                TreeItem<PatientRecords> record = recordsTable.getSelectionModel().getSelectedItem();
                editing = true;
                editForm = record.getValue().getPatient();
                menuOpenFormButton.fire();
            });
        }
    }

    private void openDraftsForm() {
        if ((draftsTableView.getSelectionModel().getSelectedItem().getClass().getName().contains("PatientRegistrationForm"))) {
            PatientRegistrationForm form = (PatientRegistrationForm) draftsTableView.getSelectionModel().getSelectedItem();
            setRegistrationTab(form);
            addPatientButton.fire();
        } else {
            PatientVisitForm form = (PatientVisitForm) draftsTableView.getSelectionModel().getSelectedItem();
            setVisitTab(form);
            addVisitButton.fire();
        }
    }

    private void openRecordsForm() {
        TreeItem<PatientRecords> record = recordsTable.getSelectionModel().getSelectedItem();
        if (record.getValue().getPatient() != null) {
            setRegistrationTab(record.getValue().getPatient());
            addPatientButton.fire();
        } else {
            setVisitTab(record.getValue().getVisit());
            addVisitButton.fire();
        }
    }

    private void clearRegistration() {
        registerNameText.setText("");
        registerBirthDateCalendar.getEditor().setText("");
        registerPhoneText.setText("");
        registerAddressText.setText("");
        registerEmailText.setText("");
        registerGuardianNameText.setText("");
        registerGuardianPhoneText.setText("");
        registerBloodTypeChoiceBox.setValue("");
        registerInsuranceChoice.setValue("");
        registerCovidPrimaryVaccineChoice.setValue("");
        registerCovidPrimaryVaccineCalendar.getEditor().setText("");
        registerCovidSecondaryVaccineChoice.setValue("");
        registerCovidSecondaryVaccineCalendar.getEditor().setText("");
        registerCovidBoosterVaccineChoice.setValue("");
        registerCovidBoosterVaccineCalendar.getEditor().setText("");
        registerDrugAllergyText.setText("");
        registerDrugAllergyCheck.setSelected(false);
        registerFoodAllergyCheck.setSelected(false);
        registerInsectAllergyCheck.setSelected(false);
        registerLatexAllergyCheck.setSelected(false);
        registerMoldAllergyCheck.setSelected(false);
        registerPetAllergyCheck.setSelected(false);
        registerPollenAllergyCheck.setSelected(false);
        registerOtherAllergyText.setText("");
        registerPreConditionsText.setText("");
        registerInsulinCheck.setSelected(false);
        registerAntibioticsCheck.setSelected(false);
        registerAntiCoagulantCheck.setSelected(false);
        registerIbuprofenCheck.setSelected(false);
        registerNaxCheck.setSelected(false);
        registerDrugHistoryCheck.setSelected(false);
        registerHistoryAlcoholCheck.setSelected(false);
        registerHistorySmokingCheck.setSelected(false);
        registerHeightText.setText("");
        registerWeightText.setText("");
        registerRaceChoice.setValue("");

        registerHispanicRadio.setSelected(false);
        registerNonHispanicRadio.setSelected(false);
        registerMaleRadio.setSelected(false);
        registerFemaleRadio.setSelected(false);
        registerGenderChoice.setValue("");
        registerPronounsText.setText("");
        registerSexualYesRadio.setSelected(false);
        registerSexualNoRadio.setSelected(false);
        registerReligiousRadio.setSelected(false);
        registerNonReligiousRadio.setSelected(false);
        registerPregnantRadio.setSelected(false);
    }

    private void setVisitTab(PatientVisitForm form) {
        clearVisit();
        visitNameText.setText(form.getValue("patientName"));
        visitDate.getEditor().setText(form.getValue("dateOfVisit"));
            if (!form.getValue("symptomsMyHurt").equals("[]")) {
                for (String s : (form.getValue("symptomsMyHurt").substring(1,form.getValue("symptomsMyHurt").length()-1)).split(",")) {
                    if (s.trim().equals("abdomen")) {
                        visitAbHurt.fire();
                    } else if (s.trim().equals("back")) {
                        visitBackHurt.fire();
                    } else if (s.trim().equals("chest")) {
                        visitChestHurt.fire();
                    } else if (s.trim().equals("ear")) {
                        visitEarHurt.fire();
                    } else if (s.trim().equals("head")) {
                        visitHeadHurt.fire();
                    } else if (s.trim().equals("pelvis")) {
                        visitPelvisHurt.fire();
                    } else if (s.trim().equals("tooth")) {
                        visitToothHurt.fire();
                    } else if (s.trim().equals("rectum")) {
                        visitRectumHurt.fire();
                    } else if (s.trim().equals("skin")) {
                        visitSkinHurt.fire();
                    } else if (s.trim().equals("leg")) {
                        visitLegHurt.fire();
                    } else if (s.trim().equals("arm")) {
                        visitArmHurt.fire();
                    } else if (s.trim().equals("chronic pain")) {
                        visitChronicHurt.fire();
                    } else {
                        visitOtherHurtText.setText(s);
                    }
                }
            }
            if (!form.getValue("symptomsIFeel").equals("[]")) {
                for (String s : (form.getValue("symptomsIFeel").substring(1,form.getValue("symptomsIFeel").length()-1)).split(",")) {
                    if (s.trim().equals("chills")) {
                        visitFeelChills.fire();
                    } else if (s.trim().equals("fever")) {
                        visitFeelFever.fire();
                    } else if (s.trim().equals("paresthesia")) {
                        visitFeelParesthesia.fire();
                    } else if (s.trim().equals("light-headed")) {
                        visitFeelLight.fire();
                    } else if (s.trim().equals("dizzy: about to black out")) {
                        visitAboutToBlackFeel.fire();
                    } else if (s.trim().equals("dizzy: the room is spinning")) {
                        visitTheRoomSpinFeel.fire();
                    } else if (s.trim().equals("mouth dry")) {
                        visitMouthFeel.fire();
                    } else if (s.trim().equals("nauseated")) {
                        visitNauseatedFeel.fire();
                    } else if (s.trim().equals("short of breath")) {
                        visitShortOfBreathFeel.fire();
                    } else if (s.trim().equals("sick: like I have the flu")) {
                        visitFluFeel.fire();
                    } else if (s.trim().equals("sick: like I have to vomit")) {
                        visitVomitFeel.fire();
                    } else if (s.trim().equals("sleepy")) {
                        visitFeelSleepy.fire();
                    } else if (s.trim().equals("sweaty")) {
                        visitSweatyFeel.fire();
                    } else if (s.trim().equals("thirsty")) {
                        visitThirstyFeel.fire();
                    } else if (s.trim().equals("tired")) {
                        visitTiredFeel.fire();
                    } else if (s.trim().equals("weak")) {
                        visitWeakFeel.fire();
                    } else {
                        visitOtherFeel.setText(s);
                    }
                }
            }
            if (!form.getValue("symptomsICant").equals("[]")) {
                for (String s : (form.getValue("symptomsICant").substring(1,form.getValue("symptomsICant").length()-1)).split(",")) {
                    if (s.trim().equals("breathe")) {
                        visitCantBreath.fire();
                    } else if (s.trim().equals("hear: losing hearing")) {
                        visitCantLosingHearing.fire();
                    } else if (s.trim().equals("hear: sounds are too loud")) {
                        visitCantSoundsTooLoud.fire();
                    } else if (s.trim().equals("hear: sounds are ringing")) {
                        visitCantRinging.fire();
                    } else if (s.trim().equals("move one side (arm and or leg)")) {
                        visitCantMoveOne.fire();
                    } else if (s.trim().equals("pass a bowel action normally")) {
                        visitCantPassBowel.fire();
                    } else if (s.trim().equals("pass urine normally")) {
                        visitCantPassUrine.fire();
                    } else if (s.trim().equals("remember")) {
                        visitCantRemember.fire();
                    } else if (s.trim().equals("see: blindness")) {
                        visitCantBlindness.fire();
                    } else if (s.trim().equals("see: double vision")) {
                        visitCantDoubleVision.fire();
                    } else if (s.trim().equals("see: blurred vision")) {
                        visitCantBlurredVision.fire();
                    } else if (s.trim().equals("sleep")) {
                        visitCantSleep.fire();
                    } else if (s.trim().equals("smell things normally")) {
                        visitCantSmell.fire();
                    } else if (s.trim().equals("speak normally")) {
                        visitCantSpeak.fire();
                    } else if (s.trim().equals("passing watery bowl actions")) {
                        visitCantStopPassingWater.fire();
                    } else if (s.trim().equals("scratching")) {
                        visitCantStopScratch.fire();
                    } else if (s.trim().equals("sweating")) {
                        visitCantStopSweat.fire();
                    } else if (s.trim().equals("swallow normally")) {
                        visitCantSwallow.fire();
                    } else if (s.trim().equals("taste properly")) {
                        visitCantTaste.fire();
                    } else if (s.trim().equals("walk normally")) {
                        visitCantWalk.fire();
                    } else if (s.trim().equals("write normally")) {
                        visitCantWrite.fire();
                    } else {
                        visitCantOtherText.setText(s);
                    }
                }
            }
            if (!form.getValue("givenMedication").equals("[]")) {
                for (String s : (form.getValue("givenMedication").substring(1,form.getValue("givenMedication").length()-1)).split(",")) {
                    if (s.trim().contains("oxygen")) {
                        visitOxygenCheck.fire();
                    } else if (s.trim().contains("Epinephrine")) {
                        visitEpinephrineText.setText(s.substring(19));
                    } else if (s.trim().contains("Nitroglycerin")) {
                        visitNitroText.setText(s.substring(21));
                    } else if (s.trim().contains("Diphenhydramine")) {
                        visitDiphenText.setText(s.substring(23));
                    } else if (s.trim().contains("Albuterol")) {
                        visitAlbuterolText.setText(s.substring(28));
                    } else if (s.trim().contains("Aspirin")) {
                        visitAspirinText.setText(s.substring(15));
                    } else if (s.trim().contains("Glucose")) {
                        visitGlucoseText.setText(s.substring(15));
                    } else if (s.trim().contains("Atropine")) {
                        visitAtropineText.setText(s.substring(16));
                    } else if (s.trim().contains("Hydrocortisone")) {
                        visitHydroText.setText(s.substring(22));
                    } else if (s.trim().contains("Morphine")) {
                        visitMorphineText.setText(s.substring(33));
                    } else if (s.trim().contains("Nalaxone")) {
                        visitNalaxoneText.setText(s.substring(16));
                    } else if (s.trim().contains("Lorazepam")) {
                        visitLorazepamText.setText(s.substring(30));
                    } else if (s.trim().contains("Flumazenil")) {
                        visitFlumaText.setText(s.substring(18));
                    } else if (s.contains(" (mg): ")){
                        String[] otherMedArr = s.trim().split("(mg)");
                        visitOtherMedicationText.setText(otherMedArr[0].substring(0, otherMedArr[0].length()-2));
                        visitOtherMedicineDosageText.setText(otherMedArr[1].substring(3));
                    }
                }
            }
            if (!form.getValue("injectionsGiven").equals("[]")) {
                for (String s : (form.getValue("injectionsGiven").substring(1,form.getValue("injectionsGiven").length()-1)).split(",")) {
                    if (s.trim().contains("(IM)")) {
                        visitIMInjectionCheck.fire();
                    } else if (s.trim().contains("(IV)")) {
                        visitIVText.setText(s.substring(31));
                    } else if (s.trim().contains("(SC)")) {
                        visitSCCheck.fire();
                    } else if (s.trim().contains("P.O")) {
                        visitPOCheck.fire();
                    }
                }
            }
            if (!form.getValue("potentialDiagnosis").equals("[]")) {
                for (String s : (form.getValue("potentialDiagnosis").substring(1, form.getValue("potentialDiagnosis").length() - 1)).split(",")) {
                    if (s.trim().equals("Other chest pain; chest pain unspecified")) {
                        visitChestPainCheck.fire();
                    } else if (s.trim().equals("Acute upper respiratory infection; unspecified")) {
                        visitRespInfCheck.fire();
                    } else if (s.trim().equals("Urinary tract infection; site not specified")) {
                        visitUTICheck.fire();
                    } else if (s.trim().equals("Headache")) {
                        visitHeadacheCheck.fire();
                    } else if (s.trim().equals("Unspecified abdominal pain")) {
                        visitUnspecifiedAbCheck.fire();
                    } else if (s.trim().equals("Syncope and collapse")) {
                        visitSyncopeCheck.fire();
                    } else if (s.trim().equals("Non-infective gastroenteritis and colitis; unspecified")) {
                        visitGastroCheck.fire();
                    } else if (s.trim().equals("Dizziness and giddiness")) {
                        visitDizzinessCheck.fire();
                    } else if (s.trim().equals("Low back pain")) {
                        visitLowBackCheck.fire();
                    } else if (s.trim().equals("Unspecified injury of head; initial encounter")) {
                        visitHeadInjuryCheck.fire();
                    } else if (s.trim().equals("Nausea with vomiting; unspecified")) {
                        visitNauseaCheck.fire();
                    } else if (s.trim().equals("Acute pharyngitis; unspecified")) {
                        visitAcuteCheck.fire();
                    } else if (s.trim().equals("Unspecified asthma with (acute) exacerbation")) {
                        visitAsthmaCheck.fire();
                    } else if (s.trim().equals("Constipation; unspecified")) {
                        visitConstipationCheck.fire();
                    } else if (s.trim().equals("Acute bronchitis; unspecified")) {
                        visitBronchitisCheck.fire();
                    } else if (s.trim().equals("Strain of muscle; fascia and tendon at neck level; initial encounter")) {
                        visitStrainNeckCheck.fire();
                    } else if (s.trim().equals("Fever: unspecified")) {
                        visitFeverCheck.fire();
                    } else if (s.trim().equals("Other specified disorders of teeth and supporting structures")) {
                        visitTeethCheck.fire();
                    } else if (s.trim().equals("Epigastric pain")) {
                        visitEpiCheck.fire();
                    } else if (s.trim().equals("Strain of muscle; fascia and tendon of lower back; initial encounter")) {
                        visitStrainBackCheck.fire();
                    } else {
                        visitOtherDiagnosisText.setText(s);
                    }
                }
            }

        visitBloodPressureText.setText(form.getValue("bloodPressure"));
        if (form.getValue("admissionStatusCheckIn") != "") {
            visitAdmissionYesRadio.fire();
        }
        visitCheckinDate.getEditor().setText(form.getValue("admissionStatusCheckIn"));
        visitCheckoutDate.getEditor().setText(form.getValue("admissionStatusCheckout"));
        visitRoomNumberText.setText(form.getValue("admissionStatusRoom"));
        visitPhysicianChoice.setValue(form.getValue("physicianName"));
        visitNotes.setText(form.getValue("notesAndObservations"));
        if (!form.getValue("docRequestTest").equals("[]")) {
            for (String s : (form.getValue("docRequestTest").substring(1, form.getValue("docRequestTest").length() - 1)).split(",")) {
                for (CheckBox c : doctorTestChecks) {
                    if (s.contains(c.getText())) {
                        c.fire();
                    }
                }
                if (s.contains("other: ")) {
                    otherTestText.setText(s.substring(7));
                }
            }
        }
        if (!form.getValue("docDiagnosis").equals("[]")) {
            for (String s : (form.getValue("docDiagnosis").substring(1, form.getValue("docDiagnosis").length() - 1)).split(",")) {
                for (CheckBox c : doctorDiagnosisChecks) {
                    if (s.contains(c.getText())) {
                        c.fire();
                    }
                }
                if (s.contains("other: ")) {
                    otherDiagnosisText.setText(s.substring(7));
                }
            }
        }
        doctorDischargeText.setText(form.getValue("docDischargeInstructions"));
        doctorNotes.setText(form.getValue("docNotesAndObservations"));
    }

    private void setRegistrationTab(PatientRegistrationForm form) {
        clearRegistration();
        registerNameText.setText(form.getValue("name"));
        registerBirthDateCalendar.getEditor().setText(form.getValue("birthdate"));
        registerPhoneText.setText(form.getValue("phone"));
        registerAddressText.setText(form.getValue("home"));
        registerEmailText.setText(form.getValue("email"));
        registerGuardianNameText.setText(form.getValue("GuardianName"));
        registerGuardianPhoneText.setText(form.getValue("GuardianPhone"));
        registerBloodTypeChoiceBox.setValue(form.getValue("bloodType"));
        registerInsuranceChoice.setValue(form.getValue("insurance"));
        registerCovidPrimaryVaccineChoice.setValue(form.getValue("covid1Type"));
        registerCovidPrimaryVaccineCalendar.getEditor().setText(form.getValue("covid1Date"));
        registerCovidSecondaryVaccineChoice.setValue(form.getValue("covid2Type"));
        registerCovidSecondaryVaccineCalendar.getEditor().setText(form.getValue("Covid2Date"));
        registerCovidBoosterVaccineChoice.setValue(form.getValue("covid3Type"));
        registerCovidBoosterVaccineCalendar.getEditor().setText(form.getValue("covid3Date"));
        if (!form.getValue("allergies").equals("[]")) {
            for (String s : (form.getValue("allergies").substring(1,form.getValue("allergies").length()-1)).split(",")) {
                if (s.trim().equals("food")) {
                    registerFoodAllergyCheck.fire();
                }
                if (s.trim().equals("insect")) {
                    registerInsectAllergyCheck.fire();
                }
                if (s.trim().equals("latex")) {
                    registerLatexAllergyCheck.fire();
                }
                if (s.trim().equals("mold")) {
                    registerMoldAllergyCheck.fire();
                }
                if (s.trim().equals("pet")) {
                    registerPetAllergyCheck.fire();
                }
                if (s.trim().equals("pollen")) {
                    registerPollenAllergyCheck.fire();
                }
                if (s.contains("drug: ")) {
                    registerDrugAllergyText.setText(s.substring(6));
                    registerDrugAllergyCheck.fire();
                }
                if (s.equals("drug")) {
                    registerDrugAllergyCheck.fire();
                }
                if (s.contains("other: ")) {
                    registerOtherAllergyText.setText(s.substring(8));
                }
            }
        }
        registerPreConditionsText.setText(form.getValue("preexistingConditions"));
        if (!form.getValue("medications").equals("[]")) {
            for (String s : (form.getValue("Medications").substring(1,form.getValue("medications").length()-1)).split(",")) {
                if (s.trim().equals("insulin")) {
                    registerInsulinCheck.fire();
                }
                if (s.trim().equals("antibiotics")) {
                    registerAntibioticsCheck.fire();
                }
                if (s.trim().equals("anticoagulant")) {
                    registerAntiCoagulantCheck.fire();
                }
                if (s.trim().equals("ibuprofen")) {
                    registerIbuprofenCheck.fire();
                }
                if (s.trim().equals("naloxone")) {
                    registerNaxCheck.fire();
                }
            }
        }
        if (!form.getValue("history").equals("[]")) {
            for (String s : (form.getValue("History").substring(1,form.getValue("history").length()-1)).split(",")) {
                if (s.trim().contains("drug")) {
                    registerDrugHistoryCheck.fire();
                }
                if (s.trim().equals("alcohol")) {
                    registerHistoryAlcoholCheck.fire();
                }
                if (s.trim().equals("smoking")) {
                    registerHistorySmokingCheck.fire();
                }
            }
        }
        registerHeightText.setText(form.getValue("Height"));
        registerWeightText.setText(form.getValue("Weight"));
        registerRaceChoice.setValue(form.getValue("race"));
        if (form.getValue("ethnicity").equals("hispanic")) {
            registerHispanicRadio.fire();
        } else if (form.getValue("ethnicity").contains("non")) {
            registerNonHispanicRadio.fire();
        }
        if (form.getValue("sex").equals("male")) {
            registerMaleRadio.fire();
        } else if (form.getValue("sex").equals("female")) {
            registerFemaleRadio.fire();
        }
        registerGenderChoice.setValue(form.getValue("gender"));
        registerPronounsText.setText(form.getValue("pronouns"));
        if (form.getValue("sexualActivity").equals("yes")) {
            registerSexualYesRadio.fire();
        } else if (form.getValue("sexualActivity").equals("no")) {
            registerSexualNoRadio.fire();
        }
        if (form.getValue("religion").equals("religious")) {
            registerReligiousRadio.fire();
        } else if (form.getValue("religion").contains("non")) {
            registerNonReligiousRadio.fire();
        }
        if (form.getValue("pregnancy").equals("yes")) {
            registerPregnantRadio.fire();
        }

    }

    private void setMenuButtonsForms(String tab) {
        switch(tab) {
            case "forms":
                menuSubmitButton.toBack();
                menuClearFormButton.toBack();
                menuSaveDraftButton.toBack();
                if (currentTab.equals("visit")) {
                    submitBilling.toBack();
                    submitBilling.setVisible(true);
                }
                menuSaveDraftButton.setVisible(true);
                menuSubmitButton.setVisible(true);
                menuClearFormButton.setVisible(true);
                menuOpenFormButton.setVisible(false);
                menuDeleteButton.setVisible(false);
                break;
            case "records":
                menuOpenFormButton.toBack();
                menuDeleteButton.toBack();
                submitBilling.setVisible(false);
                menuSaveDraftButton.setVisible(false);
                menuSubmitButton.setVisible(false);
                menuClearFormButton.setVisible(false);
                menuOpenFormButton.setVisible(true);
                menuDeleteButton.setVisible(true);
                break;
            case "drafts":
                menuOpenFormButton.toBack();
                menuDeleteButton.toBack();
                submitBilling.setVisible(false);
                menuSaveDraftButton.setVisible(false);
                menuSubmitButton.setVisible(false);
                menuClearFormButton.setVisible(false);
                menuOpenFormButton.setVisible(true);
                menuDeleteButton.setVisible(true);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editing = false;
        menuOpen = false;
        pregnant = "";
        menu.fire();

        dashBoardButton.fire();
        center.setTranslateX(-176);
        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });

        menuSignOutButton.setOnMouseClicked(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Stage stage = new Stage();
            fxmlLoader.setLocation(getClass().getResource("login-screen.fxml"));
            stage.setTitle("Login Interface");
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            scene.getStylesheets().add(String.valueOf(getClass().getResource("test.css")));
            stage.getIcons().add(new Image(EmergencyNexus.class.getResourceAsStream("logo.png")));
            stage.setScene(scene);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });

//        Menu.setOnMouseClicked(event -> {
//            Image image = new Image(String.valueOf(getClass().getResource("Images/logo.png")));
//            ImageView view = new ImageView(image);
//            view.setFitHeight(30);
//            view.setFitWidth(30);
//            Menu.setGraphic(view);
//            TranslateTransition slide = new TranslateTransition();
//            slide.setDuration(Duration.seconds(0.4));
//            TranslateTransition cslide = new TranslateTransition();
//            cslide.setDuration(Duration.seconds(0.4));
//            cslide.setNode(center);
//
////            TranslateTransition rslide = new TranslateTransition();
////            rslide.setDuration(Duration.seconds(0.4));
////            rslide.setNode(rightButtons);
//
//            slide.setNode(slider);
//
////            rslide.setToX(0);
////            rslide.play();
//
//            cslide.setToX(0);
//            cslide.play();
//
//            slide.setToX(0);
//            slide.play();
//
//            slider.setTranslateX(-176);
//            center.setTranslateX(176);
//            slide.setOnFinished((ActionEvent e)-> {
//                Menu.setVisible(false);
//                MenuClose.setVisible(true);
//            });
//        });
//
//        MenuClose.setOnMouseClicked(event -> {
//            Image image = new Image(String.valueOf(getClass().getResource("Images/logo2.png")));
//            ImageView view = new ImageView(image);
//            view.setFitHeight(30);
//            view.setFitWidth(30);
//            MenuClose.setGraphic(view);
//            TranslateTransition slide = new TranslateTransition();
//            slide.setDuration(Duration.seconds(0.4));
//            slide.setNode(slider);
//
//            TranslateTransition cslide = new TranslateTransition();
//            cslide.setDuration(Duration.seconds(0.4));
//            cslide.setNode(center);
//
////            TranslateTransition rslide = new TranslateTransition();
////            rslide.setDuration(Duration.seconds(0.4));
////            rslide.setNode(rightButtons);
//
////            rslide.setToX(-176);
////            rslide.play();
//
//            cslide.setToX(-176);
//            cslide.play();
//
//            slide.setToX(-176);
//            slide.play();
////            center.setTranslateX(0);
//            slider.setTranslateX(0);
//
//            slide.setOnFinished((ActionEvent e)-> {
//                Menu.setVisible(true);
//                MenuClose.setVisible(false);
//            });
//        });


        registerEthnicity = "";
        registerReligion = "";
        registerSex = "";
        registerSexuallyActive = "";
        db = new ConnectToDatabase();
        try {
            db.makeJDBCConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        draftList = FXCollections.observableArrayList();
        setMenuButtonsForms("records");
        registerPregnantHbox.setVisible(false);
        visitAdmissionStatusHbox.setVisible(false);

        try {
            updateRecordsTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        doctorTestChecks = new CheckBox[]{testRedCheck, testWhiteCheck, testLiverCheck, testRenalCheck, testElectrolyteTest,
                testXrayCheck, testCTCheck, testMRICheck, testUrinaryCheck, testStoolCheck};

        doctorDiagnosisChecks= new CheckBox[]{diagnosisPneumoniaCheck, diagnosisChestCheck, diagnosisAcuteCereCheck,
                diagnosisCongestiveCheck, diagnosisGastrointestinalCheck, diagnosisCardiacCheck, diagnosisSepticemiaCheck,
                diagnosisBronchCheck, diagnosisPulmonaryCheck, diagnosisMyocardialCheck, diagnosisDiabetesCheck,
                diagnosisIntracranialCheck, diagnosisFoodCheck, diagnosisAbdomoinalCheck, diagnosisUrinaryCheck,
                diagnosisCardiacArrestCheck, diagnosisCovidCheck};

        ToggleGroup admissionStatus = new ToggleGroup();
        admissionStatus.getToggles().addAll(visitAdmissionYesRadio, visitAdmissionNoRadio);
        admissionStatus.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.equals(visitAdmissionYesRadio)) {
                visitAdmissionStatusHbox.setVisible(true);
            } else if (newValue != null && newValue.equals(visitAdmissionNoRadio)) {
                visitAdmissionStatusHbox.setVisible(false);
            }
        });

        ToggleGroup ethnicityGroup = new ToggleGroup();
        ethnicityGroup.getToggles().addAll(registerHispanicRadio, registerNonHispanicRadio);
        ethnicityGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.equals(registerHispanicRadio)) {
                registerEthnicity = "hispanic";
            } else if (newValue != null && newValue.equals(registerNonHispanicRadio)) {
                registerEthnicity = "non-hispanic";
            }
        });

        ToggleGroup sexGroup = new ToggleGroup();
        sexGroup.getToggles().addAll(registerMaleRadio, registerFemaleRadio);
        sexGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.equals(registerMaleRadio)) {
                registerSex = "male";
                registerPregnantHbox.setVisible(false);
            } else if (newValue != null && newValue.equals(registerFemaleRadio)) {
                registerSex = "female";
                registerPregnantHbox.setVisible(true);
            }
        });

        ToggleGroup sexuallyActiveGroup = new ToggleGroup();
        sexuallyActiveGroup.getToggles().addAll(registerSexualYesRadio, registerSexualNoRadio);
        sexuallyActiveGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.equals(registerSexualYesRadio)) {
                registerSexuallyActive = "yes";
            } else if (newValue != null && newValue.equals(registerSexualNoRadio)) {
                registerSexuallyActive = "no";
            }
        });

        ToggleGroup religionGroup = new ToggleGroup();
        religionGroup.getToggles().addAll(registerNonReligiousRadio, registerReligiousRadio);
        religionGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.equals(registerNonReligiousRadio)) {
                registerReligion = "non-religious";
            } else if (newValue != null && newValue.equals(registerReligiousRadio)) {
                registerReligion = "religious";
            }
        });


//        AnchorPane.setTopAnchor(tabPane, 0.0);
//        AnchorPane.setLeftAnchor(tabPane, 0.0);
//        AnchorPane.setRightAnchor(tabPane, 0.0);
//        AnchorPane.setBottomAnchor(tabPane, 0.0);
//        overallVBox.fillWidthProperty();

        ObservableList<Object> bloodTypes = FXCollections.observableArrayList();
        bloodTypes.addAll("A", "B", "AB", "O", "Unknown");
        registerBloodTypeChoiceBox.setItems(bloodTypes);

        ObservableList<Object> insurances = FXCollections.observableArrayList();
        insurances.addAll("AETNA", "AFLAC", "American Family Insurance", "American Medical Security",
                "American National Insurance Company", "Anthem Insurance", "Assurant, Inc.", "Asuris Northwest Health",
                "BlueCross BlueShield Association", "Celtic Insurance Company", "CIGNA", "College Health IPA",
                "Connecticare Inc.", "Continental General Insurance Company", "Golden Rule Insurance Company",
                "Group Health Cooperative", "Group Health Inc.", "Harvard Pilgrim Health Care", "Health Markets",
                "HUMANA", "Insurance Services of America", "Intermountain Healthcare", "Kaiser Permanente", "LifeWise Health Plan of Arizona",
                "LifeWise Health Plan of Oregon", "LifeWise Health Plan of Washington", "Medica Minnesota", "Medical Mutual",
                "Oregon Health Insurance", "Oxford Health Plans, Inc.", "Principal Financial Group, Inc.", "Shelter Insurance",
                "Unicare Health Insurance", "UnitedHealth Group Inc.", "Vista Health Plan", "Walter Jarvis Insurance Services",
                "WellPoint", "WPS Health Insurance", "None");
        registerInsuranceChoice.setItems(insurances);

        ObservableList<Object> covidVaccineTypes = FXCollections.observableArrayList();
        covidVaccineTypes.addAll("Pfizer-BioNTech", "Moderna ", "Johnson & Johnson’s Janssen", "None ");
        registerCovidPrimaryVaccineChoice.setItems(covidVaccineTypes);
        registerCovidSecondaryVaccineChoice.setItems(covidVaccineTypes);
        registerCovidBoosterVaccineChoice.setItems(covidVaccineTypes);

        ObservableList<Object> races = FXCollections.observableArrayList();
        races.addAll("White", "Black or African American", "American Indian or Alaska Native", "Asian", "Native Hawaiian or Other Pacific Islander");
        registerRaceChoice.setItems(races);

        ObservableList<Object> genders = FXCollections.observableArrayList();
        genders.addAll("Male", "Female", "Non-binary", "other");
        registerGenderChoice.setItems(genders);


        //VISIT FORM
        ObservableList<Object> primaryPhysicians = FXCollections.observableArrayList();
        String getDocQuery = "SELECT * FROM users WHERE typeofaccount = 'Doctor'";
        try {
            PreparedStatement smt = db.getConnection().prepareStatement(getDocQuery);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                primaryPhysicians.add(rs.getString("fullname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        visitPhysicianChoice.setItems(primaryPhysicians);

    }

    public void menuButtonClicked(ActionEvent actionEvent) {
        if (!menuOpen) {
            Image image = new Image(String.valueOf(getClass().getResource("Images/logo.png")));
            ImageView view = new ImageView(image);
            view.setFitHeight(30);
            view.setFitWidth(30);
            menu.setGraphic(view);
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            TranslateTransition cslide = new TranslateTransition();
            cslide.setDuration(Duration.seconds(0.4));
            cslide.setNode(center);

//            TranslateTransition rslide = new TranslateTransition();
//            rslide.setDuration(Duration.seconds(0.4));
//            rslide.setNode(rightButtons);

//            rslide.setToX(-176);
//            rslide.play();

            cslide.setToX(-176);
            cslide.play();

            slide.setToX(-176);
            slide.play();
//            center.setTranslateX(0);
            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)-> {
                menuOpen = true;
            });
        } else {
            Image image = new Image(String.valueOf(getClass().getResource("Images/logo2.png")));
            ImageView view = new ImageView(image);
            view.setFitHeight(30);
            view.setFitWidth(30);
            menu.setGraphic(view);
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            TranslateTransition cslide = new TranslateTransition();
            cslide.setDuration(Duration.seconds(0.4));
            cslide.setNode(center);

//            TranslateTransition rslide = new TranslateTransition();
//            rslide.setDuration(Duration.seconds(0.4));
//            rslide.setNode(rightButtons);

            slide.setNode(slider);

//            rslide.setToX(0);
//            rslide.play();

            cslide.setToX(0);
            cslide.play();

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);
            center.setTranslateX(176);
            slide.setOnFinished((ActionEvent e)-> {
                menuOpen = false;
            });
        }
    }
}
