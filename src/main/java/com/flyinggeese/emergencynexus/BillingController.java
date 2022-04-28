package com.flyinggeese.emergencynexus;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class BillingController implements Initializable {

    @FXML
    private Label costLabel, totalCostLabel, dischargeLabel, nameLabel, infoLabel;
    PatientVisitForm visit;

    public void getForm(PatientVisitForm visit){
        this.visit = visit;
        CalculateBilling cb = new CalculateBilling(visit);

        cb.costMedicine();
        cb.costInjections();

        String testPrice = cb.getTests();
        String medicinePrice = cb.getMedicine();
        String injectionPrice = cb.getInjections();

        String descriptionOfPrice = "";

        descriptionOfPrice += cb.getStayPrice() + "\n";
        if(!testPrice.equals(""))
            descriptionOfPrice += testPrice + "\n";
        if(!medicinePrice.equals(""))
            descriptionOfPrice += medicinePrice + "\n";
        if(!injectionPrice.equals(""))
            descriptionOfPrice += injectionPrice + "\n";

        infoLabel.setText(cb.infoToString());
        nameLabel.setText("Procedures:\nNights x" + descriptionOfPrice);
        totalCostLabel.setText("$"+Integer.toString(cb.calculateBilling() + (cb.getStayPrice()*1000)));
        costLabel.setText("Costs:\n$" + (cb.getStayPrice() * 1000) + "\n" + cb.getTotalCosts());
        dischargeLabel.setText(cb.docToString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
