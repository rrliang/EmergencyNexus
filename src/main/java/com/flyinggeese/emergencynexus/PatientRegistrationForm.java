package com.flyinggeese.emergencynexus;

import java.util.Arrays;
import java.util.Objects;
import java.util.ArrayList;

public class PatientRegistrationForm {
    ArrayList<String> keys;
    String[] values;
    String typeOfDraft;
    String timeOfDraft;
    String nameOfDraft;

    // Constructor
    public PatientRegistrationForm() {
        initialize();
    }

    public PatientRegistrationForm(String name, String birthdate, String phone, String home, String email,
                                   String guardianName, String guardianPhone, String bloodType, String insurance,
                                   String covid1date, String covid1Type, String covid2date, String covid2Type,
                                   String covid3date, String covid3Type, String allergies, String preexistingConditions,
                                   String medications, String history, String height, String weight, String race,
                                   String ethnicity, String religion, String pregnancy, String sex, String gender,
                                   String pronouns, String  sexualActivity)
    {
        initialize();
        values[0] = name;
        values[1] = birthdate;
        values[2] = phone;
        values[3] = home;
        values[4] = email;
        values[5] = guardianName;
        values[6] = guardianPhone;
        values[7] = bloodType;
        values[8] = insurance;
        values[9] = covid1date;
        values[10] = covid1Type;
        values[11] = covid2date;
        values[12] = covid2Type;
        values[13] = covid3date;
        values[14] = covid3Type;
        values[15] = allergies;
        values[16] = preexistingConditions;
        values[17] = medications;
        values[18] = history;
        values[19] = height;
        values[20] = weight;
        values[21] = race;
        values[22] = ethnicity;
        values[23] = religion;
        values[24] = pregnancy;
        values[25] = sex;
        values[26] = gender;
        values[27] = pronouns;
        values[28] = sexualActivity;
    }

    // Fill in keys array and values array
    private void initialize() {
        // Fill in keys
        keys = new ArrayList<String>();
        keys.add("name");
        keys.add("birthdate");
        keys.add("phone");
        keys.add("home");
        keys.add("email");
        keys.add("guardianName");
        keys.add("guardianPhone");
        keys.add("bloodType");
        keys.add("insurance");
        keys.add("covid1date");
        keys.add("covid1Type");
        keys.add("covid2date");
        keys.add("covid2Type");
        keys.add("covid3date");
        keys.add("covid3Type");
        keys.add("allergies");
        keys.add("preexistingConditions");
        keys.add("medications");
        keys.add("history");
        keys.add("height");
        keys.add("weight");
        keys.add("race");
        keys.add("ethnicity");
        keys.add("religion");
        keys.add("pregnancy");
        keys.add("sex");
        keys.add("gender");
        keys.add("pronouns");
        keys.add("sexualActivity");
        typeOfDraft = "";
        timeOfDraft = "";
        nameOfDraft = "";
        // Fill in values with empty strings
        values = new String[keys.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = "";
        }
    }

    public String[] getAll() {
        return values;
    }

    public boolean equalsSame(PatientRegistrationForm form)
    {
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
        return true;
    }

    // Get value by key name
    public String getValue(String key) {
        int index = 0;
        for(String k: keys)
        {
            if(k.equalsIgnoreCase(key))
                return values[index];
            else
                index++;
        }
        return "Key Not Found";
    }

    // Set value by key name
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

    public String getTypeOfDraft() {
        return typeOfDraft;
    }

    public void setTypeOfDraft(String typeOfDraft) {
        this.typeOfDraft = typeOfDraft;
    }

    public String getTimeOfDraft() {
        return timeOfDraft;
    }

    public void setTimeOfDraft(String timeOfDraft) {
        this.timeOfDraft = timeOfDraft;
    }

    public String getNameOfDraft() {
        return nameOfDraft;
    }

    public void setNameOfDraft(String nameOfDraft) {
        this.nameOfDraft = nameOfDraft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientRegistrationForm)) return false;
        PatientRegistrationForm that = (PatientRegistrationForm) o;
        return keys.equals(that.keys) && Arrays.equals(values, that.values) && Objects.equals(typeOfDraft, that.typeOfDraft);
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
