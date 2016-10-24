package com.example.bobbyranjan.ybsandroid;

/**
 * Created by samatha on 7/10/2016.
 */

public class Constants {

    public static final String ACTION_TYPE = "fragment_type";
    public static final String PATIENT_ID = "patient_id";
    public static final String MEDICAL_HISTORY_ID = "medical_history_id";


    public enum ActionType {
        AddNewPatient,
        PatientDetails,
        ViewMedicalHistoryList,
        ViewMedicalHistory,
        AddNewMedicalRecord
    }
}
