package com.flyinggeese.emergencynexus;

public class PatientRecords {
    private PatientRegistrationForm patient;
    private String fullname, birthdate;
    private PatientVisitForm visit;
    private String dateOfVisit, lastEditedBy;

    PatientRecords() {
        fullname = "";
        birthdate = "";
        dateOfVisit = "";
        lastEditedBy = "";
    }

    PatientRecords(PatientRegistrationForm patient) {
        this.patient = patient;
        fullname = patient.getValue("name");
        birthdate = patient.getValue("birthdate");
    }

    PatientRecords(PatientVisitForm visit) {
        this.visit = visit;
        dateOfVisit = visit.getValue("dateOfVisit");
        lastEditedBy = visit.getValue("lasteditedby");
    }

    PatientRecords(PatientRegistrationForm patient, PatientVisitForm visit) {
        this.patient = patient;
        this.visit = visit;
        fullname = patient.getValue("name");
        birthdate = patient.getValue("birthdate");
        dateOfVisit = visit.getValue("dateOfVisit");
        lastEditedBy = visit.getValue("lasteditedby");
    }

    public String getFullname() {
        return fullname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getDateOfVisit() {
        return dateOfVisit;
    }

    public String getLastEditedBy() {
        return lastEditedBy;
    }

    public PatientVisitForm getVisit() {
        return visit;
    }

    public PatientRegistrationForm getPatient() {
        return patient;
    }
}
