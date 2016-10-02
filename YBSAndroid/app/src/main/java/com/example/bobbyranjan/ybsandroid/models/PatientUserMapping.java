package com.example.bobbyranjan.ybsandroid.models;

import java.util.HashMap;

/**
 * Created by hari on 2/10/16.
 */

public class PatientUserMapping extends Model {

    String patientId;
    String userId;
    boolean mapped;

    {
        pathPrefix = PATIENT_USER;
        String[] keys = {"userId", "patientId"};
        pathKeys = keys;
    }

    public PatientUserMapping() {
    }

    public PatientUserMapping(String patientId, String userId, boolean mapped) {
        this.patientId = patientId;
        this.userId = userId;
        this.mapped = mapped;
    }

    @Override
    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = super.toMap();
        map.put("patientId", patientId);
        map.put("userId", userId);
        map.put("mapped", mapped);
        return map;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isMapped() {
        return mapped;
    }

    public void setMapped(boolean mapped) {
        this.mapped = mapped;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }
}
