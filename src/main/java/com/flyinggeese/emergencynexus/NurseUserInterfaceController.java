package com.flyinggeese.emergencynexus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
            registerGuardianNameText, registerGuardianPhoneText, registerHeightText, registerInsuranceChoice,
            registerMedicationText, registerNameText, registerPhoneText, registerPreConditionsText, registerPronounsText,
            registerReligionText, registerWeightText;

    @FXML
    private DatePicker registerBirthDateCalendar, registerCovidBoosterVaccineCalendar, registerCovidPrimaryVaccineCalendar,
            registerCovidSecondaryVaccineCalendar;

    @FXML
    private ChoiceBox<?> registerBloodTypeChoiceBox, registerCovidBoosterVaccineChoice, registerCovidPrimaryVaccineChoice,
            registerCovidSecondaryVaccineChoice, registerGenderChoice, registerPregnantChoice, registerRaceChoice,
            registerSexChoice, registerSexualChoice, registerSubstanceChoice;


    //INSTANCE VARIABLES FOR VISIT

    @FXML
    private AnchorPane overallAnchorPane;

    @FXML
    private VBox overallVBox;

    @FXML
    private TabPane tabPane;

    @FXML
    private CheckBox visitCantDoubleVision, visitAbHurt, visitAcuteCheck, visitArmHurt, visitAsthmaCheck, visitBackHurt,
            visitBronchitisCheck, visitCantBlindness, visitCantBlurredVision, visitCantBreath, visitCantLosingHearing,
            visitCantMoveOne, visitCantPassBowel, visitCantPassUrine, visitCantRemember, visitCantRinging, visitCantSleep,
            visitCantSmell, visitCantSoundsTooLoud, visitCantSpeak, visitCantStopPassingWater, visitCantStopScratch,
            visitCantStopSweat, visitCantSwallow, visitCantTaste, visitCantWalk, visitCantWrite, visitChestHurt,
            visitChestPainCheck, visitChronicHurt, visitConstipationCheck, visitDizziinessCheck, visitEarHurt,
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
            visitLorazepamText, visitMoprhineText, visitNalaxoneText, visitNameText, visitNitroText, visitOtherDiagnosisText,
            visitOtherFeel, visitOtherHurtText, visitOtherMedicationText, visitOtherMedicineDosageText, visitRoomNumberText;

    @FXML
    private DatePicker visitCheckinDate, visitDate, visitCheckoutDate;

    @FXML
    private TextArea visitNotes;

    @FXML
    private ChoiceBox<?> visitPhysicianChoice;



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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane.setTopAnchor(tabPane, 0.0);
        AnchorPane.setLeftAnchor(tabPane, 0.0);
        AnchorPane.setRightAnchor(tabPane, 0.0);
        AnchorPane.setBottomAnchor(tabPane, 0.0);
        overallVBox.fillWidthProperty();

    }
}
