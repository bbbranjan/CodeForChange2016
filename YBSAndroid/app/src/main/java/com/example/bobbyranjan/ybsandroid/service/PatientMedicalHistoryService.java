package com.example.bobbyranjan.ybsandroid.service;

import com.example.bobbyranjan.ybsandroid.models.Model;
import com.example.bobbyranjan.ybsandroid.models.PatientMedicalHistory;

/**
 * Created by hari on 3/10/16.
 */

public class PatientMedicalHistoryService extends Service {

    public static void persistPatientMedicalHistory(String id,String patientId, String date, String rt_rw, String g, String p, String a, String ah, String observations, String uk, String varices, String odema, String bb_tb, String td, String lila, String visit, String sf, String hpht, String tp, String complaints, String information) {
        PatientMedicalHistory pmh = new PatientMedicalHistory(patientId, date, rt_rw, g, p, a, ah, observations, uk, varices, odema, bb_tb, td, lila, visit, sf, hpht, tp, complaints, information);
        pmh.setId(id);
        persistModel(pmh);
    }

    public static void getPatientMedicalHistory(String patientId, String patientMedicalHistoryId, final FirebaseSingleValueListener<PatientMedicalHistory> task) {
        String path = Model.PATIENT_HISTORY+patientId+"/"+patientMedicalHistoryId;
        retrieveModel(path,PatientMedicalHistory.class,task);
    }

    public static void getAllMedicalHistoriesForPatient(String patientId, final FirebaseMultiValueListener<PatientMedicalHistory> task) {
        String path = Model.PATIENT_HISTORY+patientId;
        retrieveModels(path,PatientMedicalHistory.class,task);
    }
}
