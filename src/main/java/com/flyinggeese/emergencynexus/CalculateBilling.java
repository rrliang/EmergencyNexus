package com.flyinggeese.emergencynexus;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CalculateBilling {
    String name, doctor, docNotes;
    String tests, medicine, injections, enterDate, exitDate; //BILLING
    int cost;

    StringBuilder totalCosts = new StringBuilder();

    public CalculateBilling(PatientVisitForm visit) {
        name = visit.getValue("patientName");
        doctor = visit.getValue("physicianName");
        docNotes = visit.getValue("docDischargeInstructions");
        tests = visit.getValue("docRequestTest");
        medicine = visit.getValue("givenMedication");
        injections = visit.getValue("injectionsGiven");
        enterDate = visit.getValue("admissionStatusCheckIn");
        exitDate = visit.getValue("admissionStatusCheckout");
        cost = 0;
    }

    //CALCULATES PRICE OF STAY AFTER CALCULATING THE # OF NIGHTS FROM UI
    public int getStayPrice() {
        long days;
        try {
            Date date1 = new SimpleDateFormat("MM/dd/yyyy").parse(enterDate);
            Date date2 = new SimpleDateFormat("MM/dd/yyyy").parse(exitDate);
            //Two methods viz parse and format are used to extract Date from the string
            long difference = date2.getTime() - date1.getTime();
            days = TimeUnit.MILLISECONDS.toDays(difference) % 365;
        } catch (Exception e) {
            return 0;
        }
        return (int) days; //$1000 per night * amount of nights
    }

    //HELPS FOR PRINTING TESTS ON FINAL BILLING STAGE
    public String getTests() {
        StringBuilder testOutput = new StringBuilder();
        String[] test = tests.split(", ");
        String[] medicines = medicine.split(", ");
        for (int i = 0; i < test.length; i++)
            testOutput.append(test[i]).append("\n");
        return testOutput.substring(1, testOutput.length()-2);
    }
    public String getMedicine() {
        StringBuilder medicineOutput = new StringBuilder();
        String[] medicines = medicine.split(", ");
        for (int i = 0; i < medicines.length; i++)
            medicineOutput.append(medicines[i]).append("\n");
        return medicineOutput.substring(1, medicineOutput.length()-2);
    }
    public String getInjections() {
        StringBuilder injectionOutput = new StringBuilder();
        String[] inj = injections.split(", ");
        for (int i = 0; i < inj.length; i++)
            injectionOutput.append(inj[i]).append("\n");
        return injectionOutput.substring(1, injectionOutput.length()-2);
    }

    //CALCULATES COST FOR JUST DOCTOR TESTS

    public int calculateBilling () {
        if (tests.contains("Red Blood Cell Test")) {
            cost += 179;
            totalCosts.append("$179").append("\n");
        }
        if (tests.contains("White Blood Cell Test")) {
            cost += 179;
            totalCosts.append("$179").append("\n");
        }
        if (tests.contains("Liver Function Test")) {
            cost += 450;
            totalCosts.append("$450").append("\n");
        }
        if (tests.contains("Renal Function Test")) {
            cost += 690;
            totalCosts.append("$690").append("\n");
        }
        if (tests.contains("Electrolyte Test")) {
            cost += 45;
            totalCosts.append("$45").append("\n");
        }
        if (tests.contains("X-Ray")) {
            cost += 410;
            totalCosts.append("$410").append("\n");
        }
        if (tests.contains("CT Scan")) {
            cost += 1560;
            totalCosts.append("$1560").append("\n");
        }
        if (tests.contains("MRI")) {
            cost += 2048;
            totalCosts.append("$2048").append("\n");
        }
        if (tests.contains("Urinary Test")) {
            cost += 250;
            totalCosts.append("$250").append("\n");
        }
        if (tests.contains("Stool Test")) {
            cost += 100;
            totalCosts.append("$100").append("\n");
        }
        return cost;
    }

    public StringBuilder getTotalCosts(){
        return totalCosts;
    }
    //CALCULATES COST FOR JUST MEDICINE
    public int costMedicine () {
        if (medicine.contains("oxygen")) {
            cost += 90;
            totalCosts.append("$90").append("\n");
        }
        if (medicine.contains("Epinephrine")) {
            cost += 335;
            totalCosts.append("$335").append("\n");
        }
        if (medicine.contains("Nitroglycerin")) {
            cost += 300;
            totalCosts.append("$300").append("\n");
        }
        if (medicine.contains("Diphenhydramine")) {
            cost += 50;
            totalCosts.append("$50").append("\n");
        }
        if (medicine.contains("Albuterol/salbutamol")) {
            cost += 50;
            totalCosts.append("$50").append("\n");
        }
        if (medicine.contains("Aspirin")) {
            cost += 30;
            totalCosts.append("$30").append("\n");
        }
        if (medicine.contains("Glucose")) {
            cost += 200;
            totalCosts.append("$200").append("\n");
        }
        if (medicine.contains("Atropine")) {
            cost += 115;
            totalCosts.append("$115").append("\n");
        }
        if (medicine.contains("Hydrocortisone")) {
            cost += 30;
            totalCosts.append("$30").append("\n");
        }
        if (medicine.contains("Morphine or nitrous oxide")) {
            cost += 50;
            totalCosts.append("$50").append("\n");
        }
        if (medicine.contains("Nalaxone")) {
            cost += 100;
            totalCosts.append("$100").append("\n");
        }
        if (medicine.contains("Lorazepam or Midazolam")) {
            cost += 25;
            totalCosts.append("$25").append("\n");
        }
        if (medicine.contains("Flumazenil")) {
            cost += 75;
            totalCosts.append("$75").append("\n");
        }
        return cost;
    }

    public int costInjections(){
        if(injections.contains("Intramuscular")){
            cost += 2000;
            totalCosts.append("$2000").append("\n");
        }
        if(injections.contains("Intravascular")){
            cost += 1700;
            totalCosts.append("$1700").append("\n");
        }
        if(injections.contains("Subcutaneous")){
            cost += 1300;
            totalCosts.append("$1300").append("\n");
        }
        if(injections.contains("Oral")){
            cost += 500;
            totalCosts.append("$500").append("\n");
        }
        return cost;
    }

    public String nameToString () {
        return getTests();
    }

    /*public String costToString(){
        String costing = "";
        for(int i = 0; i < costs.size(); i++)
            costing = costing + costs.get(i) + "\n";
        return costing;
    }*/

    public String infoToString () {
        return "Name: " + name + "\nDoctor: " + doctor + "\nCheck In: " + enterDate + "\tCheck Out: " + exitDate;
    }

    public String docToString () {
        return "Discharge Notes: " + docNotes;
    }
}
