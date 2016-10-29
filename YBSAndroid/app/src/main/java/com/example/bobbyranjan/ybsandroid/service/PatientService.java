package com.example.bobbyranjan.ybsandroid.service;

import com.example.bobbyranjan.ybsandroid.models.Model;
import com.example.bobbyranjan.ybsandroid.models.Patient;

/**
 * Created by hari on 3/10/16.
 */
public class PatientService extends Service {
    public static void persistPatient(String id, String name, String husbandsName, String location, int age, String dob, String phone, int numPregnancy, boolean critical, boolean needsMoreGuidance) {
        Patient patient = new Patient(name, husbandsName, location, age, dob, phone, numPregnancy, critical, needsMoreGuidance);
        patient.setId(id);
        persistModel(patient);
    }

    public static void getPatient(String id, final FirebaseSingleValueListener<Patient> task) {
        String path = Model.PATIENT + id;
        retrieveModel(path, Patient.class, task);
    }

    public static void getAllPatients(final FirebaseMultiValueListener<Patient> task) {
        retrieveModels(Model.PATIENT, Patient.class, task);
    }
}
