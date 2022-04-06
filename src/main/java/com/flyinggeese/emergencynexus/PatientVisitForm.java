package com.flyinggeese.emergencynexus;

import java.util.List;

public interface PatientVisitForm {
    public void setPatient(String patient);

    public void setDate(String date);

    public void setSymptoms(String[] whatHurts, String[] iFeel, String[] iCant, String other);

    public void setBloodPressure(String bloodPressure);

    public void setAdmissionStatus(boolean admitted);

    public void setAdmissionStatus(boolean admitted, String date, int roomNumber);

    public void setPrimaryPhysician(String primaryPhysician);

    public void setIVAdministration(boolean saline, double amountSaline);

    public void setGivenMedications(String[] medications);

    public void setInjectionsGiven(boolean iM, boolean iV, boolean sC, boolean pO);

    public void setPotentialDiagnosis(List<String> diagnosis);

    public void setNotes(String notes);

    public String getPatient();

    public String getDate();

    public void getSymptoms();

    public void getBloodPressure();

    public void getAdmissionStatus();

    public void getPrimaryPhysician();

    public void getIVAdministration();

    public void getGivenMedications();

    public void getInjectionGiven();

    public void getPotentialDiagnosis();

    public void getNotes();

}