package com.flyinggeese.emergencynexus;

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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StaffUserInterfaceController implements Initializable {

    //MENU INSTANCE VARIABLES
    @FXML
    private Button menuSaveDraftButton, menuSignOutButton, menuSubmitButton, menuOpenFormButton, menuOnlyShowFormButton,
            menuDeleteButton, menuClearFormButton;

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
    private TabPane tabPane;

    @FXML
    private HBox visitAdmissionStatusHbox;

    @FXML private Tab registrationTab, visitTab, recordsTab, draftsTab;

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
    TreeTableView<Object> recordsTable;
    @FXML TreeTableColumn recordPatientCol, recordNameCol, recordBirthdayCol, recordsVisitCol, recordVisitDateCol, visitLastEditorCol;

    //GLOBAL VARIABLES
    private String accountType, accountHolderName, registerEthnicity, registerReligion, registerSex, registerSexuallyActive;
    private ConnectToDatabase db;
    private ObservableList<Object> draftList;
    CheckBox[] doctorTestChecks, doctorDiagnosisChecks;

    public void setAccountType(String accountType) {
        this.accountType = accountType;
        if (accountType.equals("nurse")) {
            doctorVbox.setVisible(false);
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
        if (accountType.equals("doctor")) {
            visitPhysicianChoice.setValue(accountHolderName);
        }
    }

    private void setRecordsTable() throws SQLException {
//        TreeTableColumn<PatientRegistrationForm, String> recordNameCol = new TreeTableColumn<>("fullname");
//        TreeTableColumn<PatientRegistrationForm, String> recordBirthdayCol = new TreeTableColumn<>("birthdate");
//        TreeTableColumn<PatientVisitForm, String> recordsVisitCol = new TreeTableColumn<>("dateOfVisit");
        TreeItem<Object> root = new TreeItem<>(new Object());
        recordNameCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("fullname"));
        recordBirthdayCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("birthdate"));
        recordVisitDateCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("dateOfVisit"));
        visitLastEditorCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("lastEditedBy"));

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
                    "",
                    rs.getString("dateofvisit"),
                    rs.getString("symptomsifeel"),
                    rs.getString("symptomsmyhurt"),
                    rs.getString("symptomsicant"),
                    rs.getString("bloodpressure"),
                    rs.getString("admissionstatuscheckin"),
                    rs.getString("admissionstatuscheckout"),
                    rs.getString("admissionstatusroom"),
                    "",
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
                if (patientRecordsList.get(i).getFullname().equals(getPatientFromID(recordsVisitList.get(j).getVisit().getPatient()))) {
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

    private String getPatientFromID(int id) throws SQLException {
        String getPatient = "SELECT * FROM patients WHERE idpatients = ?";
        PreparedStatement stm = db.getConnection().prepareStatement(getPatient);
        stm.setString(1, String.valueOf(id));
        ResultSet rs = stm.executeQuery();
        String patientName = "";
        while (rs.next()) {
            patientName = rs.getString("fullname");
        }
        return patientName;
    }

    private String getPhysicianFromID(int id) throws SQLException {
        String getPatient = "SELECT * FROM users WHERE userid = ?";
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
        if (tabPane.getSelectionModel().getSelectedIndex() == 0) { //Register Patient
            saveRegistrationDraft();
        } else { //Visit form
            saveVisitDraft();
        }
    }

    @FXML
    void menuClearFormButtonClicked (ActionEvent event) {
        if (tabPane.getSelectionModel().getSelectedItem().equals(registrationTab)) { //Register Patient
            clearRegistration();
        } else { //Visit form
            clearVisit();
        }
    }

    private void clearVisit() {
        visitNameText.setText("");
        visitDate.getEditor().setText("");
        visitCantDoubleVision.disarm();
        visitAbHurt.disarm();
        visitAcuteCheck.disarm();
        visitArmHurt.disarm();
        visitAsthmaCheck.disarm();
        visitBackHurt.disarm();
        visitBronchitisCheck.disarm();
        visitCantBlindness.disarm();
        visitCantBlurredVision.disarm();
        visitCantBreath.disarm();
        visitCantLosingHearing.disarm();
        visitCantMoveOne.disarm();
        visitCantPassBowel.disarm();
        visitCantPassUrine.disarm();
        visitCantRemember.disarm();
        visitCantRinging.disarm();
        visitCantSleep.disarm();
        visitCantSmell.disarm();
        visitCantSoundsTooLoud.disarm();
        visitCantSpeak.disarm();
        visitCantStopPassingWater.disarm();
        visitCantStopScratch.disarm();
        visitCantStopSweat.disarm();
        visitCantSwallow.disarm();
        visitCantTaste.disarm();
        visitCantWalk.disarm();
        visitCantWrite.disarm();
        visitChestHurt.disarm();
        visitChestPainCheck.disarm();
        visitChronicHurt.disarm();
        visitConstipationCheck.disarm();
        visitDizzinessCheck.disarm();
        visitEarHurt.disarm();
        visitEpiCheck.disarm();
        visitFeelChills.disarm();
        visitFeelFever.disarm();
        visitFeelLight.disarm();
        visitFeelParesthesia.disarm();
        visitFeelSleepy.disarm();
        visitFeverCheck.disarm();
        visitFluFeel.disarm();
        visitGastroCheck.disarm();
        visitHeadHurt.disarm();
        visitHeadInjuryCheck.disarm();
        visitHeadacheCheck.disarm();
        visitIMInjectionCheck.disarm();
        visitLegHurt.disarm();
        visitLowBackCheck.disarm();
        visitNauseaCheck.disarm();
        visitNauseatedFeel.disarm();
        visitPOCheck.disarm();
        visitOxygenCheck.disarm();
        visitPelvisHurt.disarm();
        visitRectumHurt.disarm();
        visitRespInfCheck.disarm();
        visitSCCheck.disarm();
        visitShortOfBreathFeel.disarm();
        visitSkinHurt.disarm();
        visitStrainBackCheck.disarm();
        visitStrainNeckCheck.disarm();
        visitSweatyFeel.disarm();
        visitSyncopeCheck.disarm();
        visitTeethCheck.disarm();
        visitTheRoomSpinFeel.disarm();
        visitThirstyFeel.disarm();
        visitTiredFeel.disarm();
        visitToothHurt.disarm();
        visitUTICheck.disarm();
        visitUnspecifiedAbCheck.disarm();
        visitVomitFeel.disarm();
        visitWeakFeel.disarm();
        visitAboutToBlackFeel.disarm();
        visitPhysicianChoice.setValue("");
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
        visitAdmissionYesRadio.disarm();
        visitAdmissionNoRadio.disarm();
    }

    private void saveRegistrationDraft() throws SQLException {
        String errMsg;
        PatientRegistrationForm form = getRegistrationForm();
        if (checkRegistrationEmpty()) {
            alertFormEmpty(true);
        } else if(checkIfPatientExists(form)) {
            alertPatientAlreadyExists(true);
        } else if(draftList.contains(form)) {
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
    void menuSignOutButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Stage stage = new Stage();
        fxmlLoader.setLocation(getClass().getResource("login-screen.fxml"));
        stage.setTitle("Login Interface");
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(String.valueOf(getClass().getResource("test.css")));
        stage.getIcons().add(new Image(EmergencyNexus.class.getResourceAsStream("logo.png")));
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void menuDeleteButtonClicked(ActionEvent event) {
        deleteButtonClicked();
    }

    private void deleteButtonClicked() {
        if (tabPane.getSelectionModel().isSelected(3)) {
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
    }

    @FXML
    void menuSubmitButtonClicked(ActionEvent event) throws SQLException {
        if (tabPane.getSelectionModel().getSelectedItem().equals(registrationTab)) { //Register Patient
            submitRegisterForm();
        } else { //Visit form
            submitVisitForm();
        }
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
                    tabPane.getSelectionModel().select(registrationTab);
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
        if (registerOtherAllergyText.getText().equals("")) {
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
        if (checkRegistrationEmpty()) {
            alertFormEmpty(true);
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
                alertSubmitSuccess(submitRegistrationForm(getRegistrationForm()));
            }
        } else {
            alertSubmitSuccess(submitRegistrationForm(getRegistrationForm()));
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

    private void alertPatientAlreadyExists(boolean patientExists) {
        if (patientExists) {
            Alert accountAlreadyExists = new Alert(Alert.AlertType.CONFIRMATION);
            accountAlreadyExists.setContentText("ERR: That patient already exists in record. Draft will not be saved.");
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
        String pregnant = "no";
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
            feel.add("dizzy, about to black out");
        }
        if (visitTheRoomSpinFeel.isSelected()) {
            feel.add("dizzy, the room is spinning");
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
            feel.add("sick, like I have the flu");
        }
        if (visitVomitFeel.isSelected()) {
            feel.add("sick, like I have to vomit");
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
        feel.add(visitOtherHurtText.getText());
        return feel.toString();
    }

    private String getCant() {
        ArrayList<String> cant = new ArrayList<>();
        if (visitCantBreath.isSelected()) {
            cant.add("breathe");
        }
        if (visitCantLosingHearing.isSelected()) {
            cant.add("hear, losing hearing");
        }
        if (visitCantSoundsTooLoud.isSelected()) {
            cant.add("hear, sounds are too loud");
        }
        if (visitCantRinging.isSelected()) {
            cant.add("hear, sounds are ringing");
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
            cant.add("see, blindness");
        }
        if (visitCantDoubleVision.isSelected()) {
            cant.add("see, double vision");
        }
        if (visitCantBlurredVision.isSelected()) {
            cant.add("see, blurred vision");
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
            possibleDiagnosis.add("Other chest pain, chest pain unspecified");
        }
        if (visitRespInfCheck.isSelected()) {
            possibleDiagnosis.add("Acute upper respiratory infection, unspecified");
        }
        if (visitUTICheck.isSelected()) {
            possibleDiagnosis.add("Urinary tract infection, site not specified");
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
            possibleDiagnosis.add("Non-infective gastroenteritis and colitis, unspecified");
        }
        if (visitDizzinessCheck.isSelected()) {
            possibleDiagnosis.add("Dizziness and giddiness");
        }
        if (visitLowBackCheck.isSelected()) {
            possibleDiagnosis.add("Low back pain");
        }
        if (visitHeadInjuryCheck.isSelected()) {
            possibleDiagnosis.add("Unspecified injury of head, initial encounter");
        }
        if (visitNauseaCheck.isSelected()) {
            possibleDiagnosis.add("Nausea with vomiting, unspecified");
        }
        if (visitAcuteCheck.isSelected()) {
            possibleDiagnosis.add("Acute pharyngitis, unspecified");
        }
        if (visitAsthmaCheck.isSelected()) {
            possibleDiagnosis.add("Unspecified asthma with (acute) exacerbation");
        }
        if (visitConstipationCheck.isSelected()) {
            possibleDiagnosis.add("Constipation, unspecified");
        }
        if (visitBronchitisCheck.isSelected()) {
            possibleDiagnosis.add("Acute bronchitis, unspecified");
        }
        if (visitStrainNeckCheck.isSelected()) {
            possibleDiagnosis.add("Strain of muscle, fascia and tendon at neck level, initial encounter");
        }
        if (visitFeverCheck.isSelected()) {
            possibleDiagnosis.add("Fever, unspecified");
        }
        if (visitTeethCheck.isSelected()) {
            possibleDiagnosis.add("Other specified disorders of teeth and supporting structures");
        }
        if (visitEpiCheck.isSelected()) {
            possibleDiagnosis.add("Epigastric pain");
        }
        if (visitStrainBackCheck.isSelected()) {
            possibleDiagnosis.add("Strain of muscle, fascia and tendon of lower back, initial encounter");
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
    void menuOnlyShowFormButton(ActionEvent event) {

    }

    @FXML
    void menuOpenFormButtonClicked(ActionEvent event) {
        openDraftsForm();
        tabPane.getSelectionModel().select(registrationTab);
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
                menuOpenFormButton.arm();
            });
            delete.setOnAction((cmEvent) -> {
                deleteButtonClicked();
            });
        }
    }

    private void openDraftsForm() {

        if ((draftsTableView.getSelectionModel().getSelectedItem().getClass().getName().contains("PatientRegistrationForm"))) {
            PatientRegistrationForm form = (PatientRegistrationForm) draftsTableView.getSelectionModel().getSelectedItem();
            setRegistrationTab(form);
        } else {
            PatientVisitForm form = (PatientVisitForm) draftsTableView.getSelectionModel().getSelectedItem();
            setVisitTab(form);
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
        registerDrugHistoryCheck.disarm();
        registerFoodAllergyCheck.disarm();
        registerInsectAllergyCheck.disarm();
        registerLatexAllergyCheck.disarm();
        registerMoldAllergyCheck.disarm();
        registerPetAllergyCheck.disarm();
        registerPollenAllergyCheck.disarm();
        registerOtherAllergyText.setText("");
        registerPreConditionsText.setText("");
        registerInsulinCheck.disarm();
        registerAntibioticsCheck.disarm();
        registerAntiCoagulantCheck.disarm();
        registerIbuprofenCheck.disarm();
        registerNaxCheck.disarm();
        registerDrugHistoryCheck.disarm();
        registerHistoryAlcoholCheck.disarm();
        registerHistorySmokingCheck.disarm();
        registerHeightText.setText("");
        registerWeightText.setText("");
        registerRaceChoice.setValue("");
        registerHispanicRadio.disarm();
        registerNonHispanicRadio.disarm();
        registerMaleRadio.disarm();
        registerFemaleRadio.disarm();
        registerGenderChoice.setValue("");
        registerPronounsText.setText("");
        registerSexualYesRadio.disarm();
        registerSexualNoRadio.disarm();
        registerReligiousRadio.disarm();
        registerNonReligiousRadio.disarm();
    }

    private void setVisitTab(PatientVisitForm form) {
        clearVisit();
        visitNameText.setText(form.getValue("patientName"));
        visitDate.getEditor().setText("dateOfVisit");
        if (!form.getValue("symptomsMyHurt").equals("[]")) {
            for (String s : (form.getValue("symptomsMyHurt").substring(1,getAllergies().length()-1)).split(",")) {
                if (s.toLowerCase().contains("abdomen")) {
                    visitAbHurt.arm();
                } else if (s.toLowerCase().contains("back")) {
                    visitBackHurt.arm();
                } else if (s.toLowerCase().contains("chest")) {
                    visitChestHurt.arm();
                } else if (s.toLowerCase().contains("ear")) {
                    visitEarHurt.arm();
                } else if (s.toLowerCase().contains("head")) {
                    visitHeadHurt.arm();
                } else if (s.toLowerCase().contains("pelvis")) {
                    visitPelvisHurt.arm();
                } else if (s.toLowerCase().equals("tooth")) {
                    visitToothHurt.arm();
                } else if (s.toLowerCase().equals("rectum")) {
                    visitRectumHurt.arm();
                } else if (s.toLowerCase().equals("skin")) {
                    visitSkinHurt.arm();
                } else if (s.toLowerCase().equals("leg")) {
                    visitLegHurt.arm();
                } else if (s.toLowerCase().equals("arm")) {
                    visitArmHurt.arm();
                } else if (s.toLowerCase().equals("chronic pain")) {
                    visitChronicHurt.arm();
                } else {
                    visitOtherHurtText.setText(s);
                }
            }
        }
        if (!form.getValue("symptomsIFeel").equals("[]")) {
            for (String s : (form.getValue("symptomsIFeel").substring(1,getAllergies().length()-1)).split(",")) {
                if (s.toLowerCase().contains("chills")) {
                    visitFeelChills.arm();
                } else if (s.toLowerCase().contains("paresthesia")) {
                    visitFeelParesthesia.arm();
                } else if (s.toLowerCase().contains("light-headed")) {
                    visitFeelLight.arm();
                } else if (s.toLowerCase().contains("dizzy, about to black out")) {
                    visitAboutToBlackFeel.arm();
                } else if (s.toLowerCase().contains("dizzy, the room is spinning")) {
                    visitTheRoomSpinFeel.arm();
                } else if (s.toLowerCase().contains("mouth dry")) {
                    visitMouthFeel.arm();
                } else if (s.toLowerCase().equals("nauseated")) {
                    visitNauseatedFeel.arm();
                } else if (s.toLowerCase().equals("short of breath")) {
                    visitShortOfBreathFeel.arm();
                } else if (s.toLowerCase().equals("sick, like I have the flu")) {
                    visitFluFeel.arm();
                } else if (s.toLowerCase().equals("sick, like I have to vomit")) {
                    visitVomitFeel.arm();
                } else if (s.toLowerCase().equals("sleepy")) {
                    visitFeelSleepy.arm();
                } else if (s.toLowerCase().equals("sweaty")) {
                    visitSweatyFeel.arm();
                } else if (s.toLowerCase().equals("thirsty")) {
                    visitThirstyFeel.arm();
                } else if (s.toLowerCase().equals("tired")) {
                    visitTiredFeel.arm();
                } else if (s.toLowerCase().equals("weak")) {
                    visitWeakFeel.arm();
                } else {
                    visitOtherFeel.setText(s);
                }
            }
        }
        if (!form.getValue("symptomsICant").equals("[]")) {
            for (String s : (form.getValue("symptomsICant").substring(1,getAllergies().length()-1)).split(",")) {
                if (s.toLowerCase().contains("breathe")) {
                    visitCantBreath.arm();
                } else if (s.toLowerCase().contains("hear, losing hearing")) {
                    visitCantLosingHearing.arm();
                } else if (s.toLowerCase().contains("hear, sounds are too loud")) {
                    visitCantSoundsTooLoud.arm();
                } else if (s.toLowerCase().contains("hear, sounds are ringing")) {
                    visitCantRinging.arm();
                } else if (s.toLowerCase().contains("move one side (arm and or leg)")) {
                    visitCantMoveOne.arm();
                } else if (s.toLowerCase().contains("pass a bowel action normally")) {
                    visitCantPassBowel.arm();
                } else if (s.toLowerCase().equals("pass urine normally")) {
                    visitCantPassUrine.arm();
                } else if (s.toLowerCase().equals("remember")) {
                    visitCantRemember.arm();
                } else if (s.toLowerCase().equals("see, blindness")) {
                    visitCantBlindness.arm();
                } else if (s.toLowerCase().equals("see, double vision")) {
                    visitCantDoubleVision.arm();
                } else if (s.toLowerCase().equals("see, blurred vision")) {
                    visitCantBlurredVision.arm();
                } else if (s.toLowerCase().equals("sleep")) {
                    visitCantSleep.arm();
                } else if (s.toLowerCase().equals("smell things normally")) {
                    visitCantSmell.arm();
                } else if (s.toLowerCase().equals("speak normally")) {
                    visitCantSpeak.arm();
                } else if (s.toLowerCase().equals("passing watery bowl actions")) {
                    visitCantStopPassingWater.arm();
                } else if (s.toLowerCase().equals("scratching")) {
                    visitCantStopScratch.arm();
                } else if (s.toLowerCase().equals("sweating")) {
                    visitCantStopSweat.arm();
                } else if (s.toLowerCase().equals("swallow normally")) {
                    visitCantSwallow.arm();
                } else if (s.toLowerCase().equals("taste properly")) {
                    visitCantTaste.arm();
                } else if (s.toLowerCase().equals("walk normally")) {
                    visitCantWalk.arm();
                } else if (s.toLowerCase().equals("write normally")) {
                    visitCantWrite.arm();
                } else {
                    visitCantOtherText.setText(s);
                }
            }
        }
        visitBloodPressureText.setText(form.getValue("bloodPressure"));
        visitCheckinDate.getEditor().setText(form.getValue("admissionStatusCheckIn"));
        visitCheckoutDate.getEditor().setText(form.getValue("admissionStatusCheckout"));
        visitRoomNumberText.setText(form.getValue("admissionStatusRoom"));
        visitPhysicianChoice.setValue(form.getValue("physicianName"));
        if (!form.getValue("givenMedication").equals("[]")) {
            for (String s : (form.getValue("givenMedication").substring(1,getAllergies().length()-1)).split(",")) {
                if (s.toLowerCase().contains("oxygen")) {
                    visitOxygenCheck.arm();
                } else if (s.toLowerCase().contains("Epinephrine")) {
                    visitEpinephrineText.setText(s);
                } else if (s.toLowerCase().contains("Nitroglycerin")) {
                    visitNitroText.setText(s);
                } else if (s.toLowerCase().contains("Diphenhydramine")) {
                    visitDiphenText.setText(s);
                } else if (s.toLowerCase().contains("Albuterol")) {
                    visitAlbuterolText.setText(s);
                } else if (s.toLowerCase().contains("Aspirin")) {
                    visitAspirinText.setText(s);
                } else if (s.toLowerCase().contains("Glucose")) {
                    visitGlucoseText.setText(s);
                } else if (s.toLowerCase().contains("Atropine")) {
                    visitAtropineText.setText(s);
                } else if (s.toLowerCase().contains("Hydrocortisone")) {
                    visitHydroText.setText(s);
                } else if (s.toLowerCase().contains("Morphine")) {
                    visitMorphineText.setText(s);
                } else if (s.toLowerCase().contains("Nalaxone")) {
                    visitNalaxoneText.setText(s);
                } else if (s.toLowerCase().contains("Lorazepam")) {
                    visitLorazepamText.setText(s);
                } else if (s.toLowerCase().contains("Flumazenil")) {
                    visitFlumaText.setText(s);
                } else if (!s.toLowerCase().equals(" (mg): ")){
                    String[] otherMedArr = s.split(" (mg): ");
                    System.out.println(otherMedArr[0]);
                    visitOtherMedicationText.setText(otherMedArr[0]);
                    visitOtherMedicineDosageText.setText(otherMedArr[1]);
                }
            }
        }
        if (!form.getValue("injectionsGiven").equals("[]")) {
            for (String s : (form.getValue("injectionsGiven").substring(1,getAllergies().length()-1)).split(",")) {
                if (s.toLowerCase().contains("oxygen")) {
                    visitOxygenCheck.arm();
                } else if (s.toLowerCase().contains("(IM)")) {
                    visitIMInjectionCheck.arm();
                } else if (s.toLowerCase().contains("(IV)")) {
                    visitIVText.setText(s);
                } else if (s.toLowerCase().contains("(SC)")) {
                    visitSCCheck.arm();
                } else if (s.toLowerCase().contains("P.O")) {
                    visitPOCheck.arm();
                }
            }
        }
        if (!form.getValue("potentialDiagnosis").equals("[]")) {
            for (String s : (form.getValue("potentialDiagnosis").substring(1, getAllergies().length() - 1)).split(",")) {
                if (s.toLowerCase().contains("Other chest pain, chest pain unspecified")) {
                    visitChestHurt.arm();
                } else if (s.toLowerCase().contains("Urinary tract infection, site not specified")) {
                    visitUTICheck.arm();
                } else if (s.toLowerCase().contains("headache")) {
                    visitHeadacheCheck.arm();
                } else if (s.toLowerCase().contains("Unspecified abdominal pain")) {
                    visitUnspecifiedAbCheck.arm();
                } else if (s.toLowerCase().contains("Syncope and collapse")) {
                    visitSyncopeCheck.arm();
                } else if (s.toLowerCase().contains("Non-infective gastroenteritis and colitis, unspecified")) {
                    visitGastroCheck.arm();
                } else if (s.toLowerCase().equals("Dizziness and giddiness")) {
                    visitDizzinessCheck.arm();
                } else if (s.toLowerCase().equals("Low back pain")) {
                    visitLowBackCheck.arm();
                } else if (s.toLowerCase().equals("Unspecified injury of head, initial encounter")) {
                    visitHeadInjuryCheck.arm();
                } else if (s.toLowerCase().equals("Nausea with vomiting, unspecified")) {
                    visitNauseaCheck.arm();
                } else if (s.toLowerCase().equals("Acute pharyngitis, unspecified")) {
                    visitAcuteCheck.arm();
                } else if (s.toLowerCase().equals("Unspecified asthma with (acute) exacerbation")) {
                    visitAsthmaCheck.arm();
                } else if (s.toLowerCase().equals("Constipation, unspecified")) {
                    visitConstipationCheck.arm();
                } else if (s.toLowerCase().equals("Acute bronchitis, unspecified")) {
                    visitBronchitisCheck.arm();
                } else if (s.toLowerCase().equals("Strain of muscle, fascia and tendon at neck level, initial encounter")) {
                    visitStrainNeckCheck.arm();
                } else if (s.toLowerCase().equals("Fever, unspecified")) {
                    visitFeverCheck.arm();
                } else if (s.toLowerCase().equals("Other specified disorders of teeth and supporting structures")) {
                    visitTeethCheck.arm();
                } else if (s.toLowerCase().equals("Epigastric pain")) {
                    visitEpiCheck.arm();
                } else if (s.toLowerCase().equals("Strain of muscle, fascia and tendon of lower back, initial encounter")) {
                    visitStrainBackCheck.arm();
                } else {
                    visitOtherDiagnosisText.setText(s);
                }
            }
        }
        visitNotes.setText(form.getValue("notesAndObservations"));
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
        if (!form.getValue("Allergies").equals("[]")) {
            for (String s : (form.getValue("Allergies").substring(1,getAllergies().length()-2)).split(",")) {
                if (s.toLowerCase().contains("food")) {
                    registerFoodAllergyCheck.arm();
                }
                if (s.toLowerCase().contains("insect")) {
                    registerInsectAllergyCheck.arm();
                }
                if (s.toLowerCase().contains("latex")) {
                    registerLatexAllergyCheck.arm();
                }
                if (s.toLowerCase().contains("mold")) {
                    registerMoldAllergyCheck.arm();
                }
                if (s.toLowerCase().contains("pet")) {
                    registerPetAllergyCheck.arm();
                }
                if (s.toLowerCase().contains("pollen")) {
                    registerPollenAllergyCheck.arm();
                }
                if (s.toLowerCase().contains("drug: ")) {
                    registerDrugAllergyText.setText(s.substring(5));
                    registerDrugAllergyCheck.arm();
                }
                if (s.toLowerCase().equals("drug")) {
                    registerDrugAllergyCheck.arm();
                }
                if (s.toLowerCase().contains("other: ")) {
                    registerOtherAllergyText.setText(s);
                }
            }
        }
        registerPreConditionsText.setText(form.getValue("preexistingConditions"));
        if (!form.getValue("Medications").equals("[]")) {
            for (String s : (form.getValue("Medications").substring(1,getAllergies().length()-1)).split(",")) {
                if (s.toLowerCase().contains("insulin")) {
                    registerInsulinCheck.arm();
                }
                if (s.toLowerCase().contains("antibiotics")) {
                    registerAntibioticsCheck.arm();
                }
                if (s.toLowerCase().contains("anticoagulant")) {
                    registerAntiCoagulantCheck.arm();
                }
                if (s.toLowerCase().contains("ibuprofen")) {
                    registerIbuprofenCheck.arm();
                }
                if (s.toLowerCase().contains("naloxone")) {
                    registerNaxCheck.arm();
                }
            }
        }
        if (!form.getValue("History").equals("[]")) {
            for (String s : (form.getValue("History").substring(1,getAllergies().length()-1)).split(",")) {
                if (s.toLowerCase().contains("drug")) {
                    registerDrugHistoryCheck.arm();
                }
                if (s.toLowerCase().contains("alcohol")) {
                    registerHistoryAlcoholCheck.arm();
                }
                if (s.toLowerCase().contains("smoking")) {
                    registerHistorySmokingCheck.arm();
                }
            }
        }
        registerHeightText.setText(form.getValue("Height"));
        registerWeightText.setText(form.getValue("Weight"));
        registerRaceChoice.setValue(form.getValue("race"));
        if (registerEthnicity.equals("hispanic")) {
            registerHispanicRadio.arm();
        } else if (registerEthnicity.contains("non")) {
            registerNonHispanicRadio.arm();
        }
        if (registerSex.equals("male")) {
            registerMaleRadio.arm();
        } else if (registerSex.equals("female")) {
            registerFemaleRadio.arm();
        }
        registerGenderChoice.setValue(form.getValue("gender"));
        registerPronounsText.setText(form.getValue("pronouns"));
        if (registerSexuallyActive.equals("yes")) {
            registerSexualYesRadio.arm();
        } else if (registerSexuallyActive.equals("no")) {
            registerSexualNoRadio.arm();
        }
        if (registerReligion.equals("religious")) {
            registerReligiousRadio.arm();
        } else if (registerSexuallyActive.contains("non")) {
            registerNonReligiousRadio.arm();
        }
    }

    private void setMenuButtonsForms(String tab) {
        switch(tab) {
            case "forms":
                menuClearFormButton.toBack();
                menuSaveDraftButton.toBack();
                menuSubmitButton.toBack();
                menuSaveDraftButton.setVisible(true);
                menuSubmitButton.setVisible(true);
                menuClearFormButton.setVisible(true);
                menuOpenFormButton.setVisible(false);
                menuOnlyShowFormButton.setVisible(false);
                menuDeleteButton.setVisible(false);
                break;
            case "records":
                menuOnlyShowFormButton.toBack();
                menuOpenFormButton.toBack();
                menuSaveDraftButton.setVisible(false);
                menuSubmitButton.setVisible(false);
                menuClearFormButton.setVisible(false);
                menuOpenFormButton.setVisible(true);
                menuOnlyShowFormButton.setVisible(true);
                menuDeleteButton.setVisible(false);
                break;
            case "drafts":
                menuDeleteButton.toBack();
                menuOpenFormButton.toBack();
                menuSaveDraftButton.setVisible(false);
                menuSubmitButton.setVisible(false);
                menuClearFormButton.setVisible(false);
                menuOpenFormButton.setVisible(true);
                menuOnlyShowFormButton.setVisible(false);
                menuDeleteButton.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerEthnicity = "";
        registerReligion = "";
        registerSex = "";
        registerSexuallyActive = "";
        db = new ConnectToDatabase();
        db.makeJDBCConnection();
        draftList = FXCollections.observableArrayList();
        setMenuButtonsForms("records");
        registerPregnantHbox.setVisible(false);
        visitAdmissionStatusHbox.setVisible(false);

        try {
            setRecordsTable();
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

        tabPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                        if (tabPane.getSelectionModel().getSelectedItem().equals(registrationTab) || tabPane.getSelectionModel().getSelectedItem().equals(visitTab)) {
                            setMenuButtonsForms("forms");
                        } else if (tabPane.getSelectionModel().getSelectedItem().equals(recordsTab)){
                            setMenuButtonsForms("records");
                        } else {
                            setMenuButtonsForms("drafts");
                        }
                    }
                }
        );

        ToggleGroup admissionStatus = new ToggleGroup();
        admissionStatus.getToggles().addAll(visitAdmissionYesRadio, visitAdmissionNoRadio);
        admissionStatus.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(visitAdmissionYesRadio)) {
                visitAdmissionStatusHbox.setVisible(true);
            } else if (newValue.equals(visitAdmissionNoRadio)) {
                visitAdmissionStatusHbox.setVisible(false);
            }
        });

        ToggleGroup ethnicityGroup = new ToggleGroup();
        ethnicityGroup.getToggles().addAll(registerHispanicRadio, registerNonHispanicRadio);
        ethnicityGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(registerHispanicRadio)) {
                registerEthnicity = "hispanic";
            } else if (newValue.equals(registerNonHispanicRadio)) {
                registerEthnicity = "non-hispanic";
            }
        });

        ToggleGroup sexGroup = new ToggleGroup();
        sexGroup.getToggles().addAll(registerMaleRadio, registerFemaleRadio);
        sexGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(registerMaleRadio)) {
                registerSex = "male";
                registerPregnantHbox.setVisible(false);
            } else if (newValue.equals(registerFemaleRadio)) {
                registerSex = "female";
                registerPregnantHbox.setVisible(true);
            }
        });

        ToggleGroup sexuallyActiveGroup = new ToggleGroup();
        sexuallyActiveGroup.getToggles().addAll(registerSexualYesRadio, registerSexualNoRadio);
        sexuallyActiveGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(registerSexualYesRadio)) {
                registerSexuallyActive = "yes";
            } else if (newValue.equals(registerSexualNoRadio)) {
                registerSexuallyActive = "no";
            }
        });

        ToggleGroup religionGroup = new ToggleGroup();
        religionGroup.getToggles().addAll(registerNonReligiousRadio, registerReligiousRadio);
        religionGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(registerNonReligiousRadio)) {
                registerReligion = "non-religious";
            } else if (newValue.equals(registerReligiousRadio)) {
                registerReligion = "religious";
            }
        });


        AnchorPane.setTopAnchor(tabPane, 0.0);
        AnchorPane.setLeftAnchor(tabPane, 0.0);
        AnchorPane.setRightAnchor(tabPane, 0.0);
        AnchorPane.setBottomAnchor(tabPane, 0.0);
        overallVBox.fillWidthProperty();

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
}