package com.example.bobbyranjan.ybsandroid.service;

import com.example.bobbyranjan.ybsandroid.models.Model;
import com.example.bobbyranjan.ybsandroid.models.PatientUserMapping;

/**
 * Created by hari on 2/10/16.
 */

public class PatientUserMappingService extends Service {
    public static void persistPatientUserMapping(String userId, String patientId) {
        PatientUserMapping pum = new PatientUserMapping(patientId,userId,true);
        pum.setId("");
        persistModel(pum);
    }

    public static void getAllPatients(String userId, final FirebaseMultiValueListener<PatientUserMapping> task) {
        String path = Model.PATIENT_USER+userId;
        retrieveModels(path,PatientUserMapping.class,task);
    }

}
