package com.example.bobbyranjan.ybsandroid.models;

import java.util.HashMap;

/**
 * Created by hari on 24/9/16.
 */

public class DoctorComments extends Model {
    String patientId;
    String patientHistoryId;
    String comments;

    {
        pathPrefix = DOCTOR_COMMENTS;
        String[] keys = {"patientId", "patientHistoryId", "id"};
        pathKeys = keys;

    }
    public DoctorComments() {
    }

    public DoctorComments(String patientId, String patientHistoryId, String comments) {
        this.patientId = patientId;
        this.patientHistoryId = patientHistoryId;
        this.comments = comments;
    }

    @Override
    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = super.toMap();
        map.put("patientId", patientId);
        map.put("patientHistoryId", patientHistoryId);
        map.put("comments", comments);
        return map;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientHistoryId() {
        return patientHistoryId;
    }

    public void setPatientHistoryId(String patientHistoryId) {
        this.patientHistoryId = patientHistoryId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
