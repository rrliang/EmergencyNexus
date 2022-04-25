package com.flyinggeese.emergencynexus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientVisitForm {
    ArrayList<String> keys;
    String[] values;
//    private String symptomsMyHurt;
//    private String symptomsIFeel;
//    private String symptomsICant;
//    private String bloodPressure;
//    private String admissionStatusCheckIn;
//    private String admissionStatusCheckout;
//    private String admissionStatusRoom;
//    private String physician;
//    private String amountOfSaline;
//    private String givenMedication;
//    private String injectionsGiven;
//    private String potentialDiagnosis;
//    private String notesAndObservations;
//    private String docRequestText;
//    private String docDiagnosis;
//    private String docDischargeInstructions;
//    private String docNotesAndObservations;
    private String typeOfDraft;
    private String nameOfDraft;
    private String timeOfDraft;
    private int patient, primaryPhysician;

    public PatientVisitForm() {
        initialize();
    }

    public PatientVisitForm(String nameOfPatient, String dateOfVisit, String symptomsIFeel, String symptomsMyHurt, String symptomsICant, String bloodPressure,
                            String admissionStatusCheckIn, String admissionStatusCheckout, String admissionStatusRoom,
                            String physician, String givenMedication, String injectionsGiven,
                            String potentialDiagnosis, String notesAndObservations, String docRequestTest, String docDiagnosis,
                            String docDischargeInstructions, String docNotesAndObservations, String lastEditedBy) {
        initialize();
        values[0] = nameOfPatient;
        values[1] = dateOfVisit;
        values[3] = symptomsMyHurt;
        values[2] = symptomsIFeel;
        values[4] = symptomsICant;
        values[5] = bloodPressure;
        values[6] = admissionStatusCheckIn;
        values[7] = admissionStatusCheckout;
        values[8] = admissionStatusRoom;
        values[9] = physician;
        values[10] = givenMedication;
        values[11] = injectionsGiven;
        values[12] = potentialDiagnosis;
        values[13] = notesAndObservations;
        values[14] = docRequestTest;
        values[15] = docDiagnosis;
        values[16] = docDischargeInstructions;
        values[17] = docNotesAndObservations;
        values[18] = lastEditedBy;
    }

    private void initialize()
    {
        keys = new ArrayList<String>();
        keys.add("patientName");
        keys.add("dateOfVisit");
        keys.add("symptomsMyHurt");
        keys.add("symptomsIFeel");
        keys.add("symptomsICant");
        keys.add("bloodPressure");
        keys.add("admissionStatusCheckIn");
        keys.add("admissionStatusCheckout");
        keys.add("admissionStatusRoom");
        keys.add("physicianName");
        keys.add("givenMedication");
        keys.add("injectionsGiven");
        keys.add("potentialDiagnosis");
        keys.add("notesAndObservations");
        keys.add("docRequestText");
        keys.add("docDiagnosis");
        keys.add("docDischargeInstructions");
        keys.add("docNotesAndObservations");
        keys.add("lasteditedby");

        values = new String[keys.size()];
        for(int i=0; i<values.length; i++)
        {
            values[i] = "";
        }
    }

    public boolean equalsSame(PatientVisitForm form) throws SQLException {
        if(form == null) {
            return false;
        }

        for(String k: keys)
        {
            for(String k2: form.getAll()) {
                if (!k.equals(k2))
                    return false;
            }
        }
        if(patient != form.getPatient() || primaryPhysician != form.getPrimaryPhysician())
            return false;
        return true;
    }

    public String getValue(String key) {
        int index = 0;
        for(String k: keys)
        {
            if(k.equals(key))
                return values[index];
            else
                index++;
        }
        return "Key Not Found";
    }

    public void setValue(String key, String value) {
        int index = 0;
        for(String k: keys)
        {
            if(k.equalsIgnoreCase(key))
            {
                values[index] = value;
                break;
            }
            else
                index++;
        }
    }

    public String[] getAll() {
        return values;
    }

    public int getPatient() {
        return patient;
    }

    public void setPatient(int patient) {
        this.patient = patient;
    }

    public int getPrimaryPhysician() {
        return primaryPhysician;
    }

    public void setPrimaryPhysician(int primaryPhysician) {
        this.primaryPhysician = primaryPhysician;
    }

    public String getTypeOfDraft() {
        return typeOfDraft;
    }

    public void setTypeOfDraft(String typeOfDraft) {
        this.typeOfDraft = typeOfDraft;
    }

    public String getNameOfDraft() {
        return nameOfDraft;
    }

    public void setNameOfDraft(String nameOfDraft) {
        this.nameOfDraft = nameOfDraft;
    }

    public String getTimeOfDraft() {
        return timeOfDraft;
    }

    public void setTimeOfDraft(String timeOfDraft) {
        this.timeOfDraft = timeOfDraft;
    }
}
