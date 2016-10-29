package com.example.bobbyranjan.ybsandroid;

public class Constants {

    public static final String PATIENT_ID = "patient_id";
    static final String ACTION_TYPE = "fragment_type";
    static final String MEDICAL_HISTORY_ID = "medical_history_id";


    enum ActionType {
        AddNewPatient,
        PatientDetails,
        ViewMedicalHistoryList,
        ViewMedicalHistory,
        AddNewMedicalRecord
    }
}
