package com.flyinggeese.emergencynexus;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class NurseUserInterfaceController implements Initializable {

    //MENU INSTANCE VARIABLES
    @FXML
    private Button menuSaveDraftButton, menuSignOutButton, menuSubmitButton;

    //FXML VARIABLES FOR REGISTER FORM
    @FXML
    private TextField registerAddressText, registerAllergiesText, registerEmailText, registerEthnicityText,
            registerGuardianNameText, registerGuardianPhoneText, registerHeightText, registerMedicationText,
            registerNameText, registerPhoneText, registerPreConditionsText, registerPronounsText,
            registerReligionText, registerWeightText, registerDrugAllergyText, registerOtherAllergyText;

    @FXML
    private DatePicker registerBirthDateCalendar, registerCovidBoosterVaccineCalendar, registerCovidPrimaryVaccineCalendar,
            registerCovidSecondaryVaccineCalendar;

    @FXML
    private ChoiceBox<Object> registerBloodTypeChoiceBox, registerCovidBoosterVaccineChoice, registerCovidPrimaryVaccineChoice,
            registerCovidSecondaryVaccineChoice, registerGenderChoice, registerPregnantChoice, registerRaceChoice,
            registerSexChoice, registerSexualChoice, registerSubstanceChoice, registerInsuranceChoice;

    @FXML
    private CheckBox registerDrugAllergyCheck, registerFoodAllergyCheck, registerInsectAllergyCheck,
            registerLatexAllergyCheck, registerMoldAllergyCheck, registerPetAllergyCheck, registerInuslinCheck,
            registerAntibioticsCheck, registerAntiCoagulantCheck, registerIbuprofenCheck, registerNaxCheck;

    @FXML
    private RadioButton registerHispanicRadio, registerNonHispanicRadio, registerNonReligiousRadio, registerReligiousRadio,
            registerMaleRadio, registerFemaleRadio, registerSexualYesRadio, registerSexualNoRadio;


    //INSTANCE VARIABLES FOR VISIT

    @FXML
    private AnchorPane overallAnchorPane;

    @FXML
    private VBox overallVBox;

    @FXML
    private TabPane tabPane;

    @FXML private Tab registerTab, visitTab, recordsTab, draftsTab;

    @FXML
    private CheckBox visitCantDoubleVision, visitAbHurt, visitAcuteCheck, visitArmHurt, visitAsthmaCheck, visitBackHurt,
            visitBronchitisCheck, visitCantBlindness, visitCantBlurredVision, visitCantBreath, visitCantLosingHearing,
            visitCantMoveOne, visitCantPassBowel, visitCantPassUrine, visitCantRemember, visitCantRinging, visitCantSleep,
            visitCantSmell, visitCantSoundsTooLoud, visitCantSpeak, visitCantStopPassingWater, visitCantStopScratch,
            visitCantStopSweat, visitCantSwallow, visitCantTaste, visitCantWalk, visitCantWrite, visitChestHurt,
            visitChestPainCheck, visitChronicHurt, visitConstipationCheck, visitDizzinessCheck, visitEarHurt,
            visitEpiCheck, visitFeelChills, visitFeelFever, visitFeelLight, visitFeelParesthesia, visitFeelSleepy,
            visitFeverCheck, visitFluFeel, visitGastroCheck, visitHeadHurt, visitHeadInjuryCheck, visitHeadacheText,
            visitIMInjectionCheck, visitLegHurt, visitLowBackCheck, visitMouthFeel, visitNauseaCheck, visitNauseatedFeel,
            visitOMCheck, visitOxygenCheck, visitPelvisHurt, visitRectumHurt, visitRespInfCheck, visitSCCheck,
            visitShortOfBreathFeel, visitSkinHurt, visitStrainBackCheck, visitStrainNeckCheck, visitSweatyFeel,
            visitSyncopeCheck, visitTeethCheck, visitTheRoomSpinFeel, visitThirstyFeel, visitTiredFeel, visitToothHurt,
            visitUTICheck, visitUnspecifiedAbCheck, visitVomitFeel, visitWeakFeel;

    @FXML
    private MenuButton visitAboutToBlackFeel, visitFeel, visitHurts;

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
    private TextArea visitNotes;

    @FXML
    private ChoiceBox<?> visitPhysicianChoice;

    private String registerEthnicity, registerReligion, registerSex, registerSexuallyActive;


    @FXML
    void menuSaveDraftButtonClicked(ActionEvent event) {

    }

    @FXML
    void menuSignOutButtonClicked(ActionEvent event) {

    }

    @FXML
    void menuSubmitButtonClicked(ActionEvent event) {

    }

    @FXML
    void visitAdmissionNoRadioClicked(ActionEvent event) {

    }

    @FXML
    void visitAdmissionYesRadioClicked(ActionEvent event) {

    }

    private void setVisibleMenuButtons(boolean visibility) {
        menuSaveDraftButton.setVisible(visibility);
        menuSubmitButton.setVisible(visibility);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        tabPane.getSelectionModel().selectedItemProperty().addListener(
//                new ChangeListener<Tab>() {
//                    @Override
//                    public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
//                        if (registerTab.isSelected() || visitTab.isSelected()) {
//                            setVisibleMenuButtons(true);
//                        } else {
//                            setVisibleMenuButtons(false);
//                        }
//                    }
//                }
//        );

        ToggleGroup ethnicityGroup = new ToggleGroup();
        ethnicityGroup.getToggles().addAll(registerHispanicRadio, registerNonHispanicRadio);
        ethnicityGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(registerHispanicRadio)) {
                registerEthnicity = "Hispanic";
            } else if (newValue.equals(registerNonHispanicRadio)) {
                registerEthnicity = "Non-Hispanic";
            }
        });

        ToggleGroup sexGroup = new ToggleGroup();
        sexGroup.getToggles().addAll(registerMaleRadio, registerFemaleRadio);
        sexGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(registerMaleRadio)) {
                registerSex = "Male";
            } else if (newValue.equals(registerFemaleRadio)) {
                registerSex = "Female";
            }
        });

        ToggleGroup sexuallyActiveGroup = new ToggleGroup();
        sexuallyActiveGroup.getToggles().addAll(registerSexualYesRadio, registerSexualNoRadio);
        sexuallyActiveGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(registerSexualYesRadio)) {
                registerSexuallyActive = "Yes";
            } else if (newValue.equals(registerSexualNoRadio)) {
                registerSexuallyActive = "No";
            }
        });

        ToggleGroup religionGroup = new ToggleGroup();
        religionGroup.getToggles().addAll(registerNonReligiousRadio, registerReligiousRadio);
        religionGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(registerNonReligiousRadio)) {
                registerReligion = "Non-religious";
            } else if (newValue.equals(registerReligiousRadio)) {
                registerReligion = "Religious";
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

        ObservableList<Object> substanceList = FXCollections.observableArrayList();
        substanceList.addAll("Drugs", "Alcohol ", "Smoking", "None");
        registerSubstanceChoice.setItems(substanceList);

        ObservableList<Object> races = FXCollections.observableArrayList();
        races.addAll("White", "Black or African American", "American Indian or Alaska Native", "Asian", "Native Hawaiian or Other Pacific Islander");
        registerRaceChoice.setItems(races);

        ObservableList<Object> genders = FXCollections.observableArrayList();
        genders.addAll("Male", "Female", "Non-binary", "other");
        registerGenderChoice.setItems(genders);

    }
}
