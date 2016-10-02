package com.example.bobbyranjan.ybsandroid.models;

import java.util.HashMap;

/**
 * Created by hari on 24/9/16.
 */
public class Model {

    public static String USERS = "/users/";
    public static String PATIENT = "/patients/";
    public static String PATIENT_HISTORY = "/patienthistory/";
    public static String PATIENT_USER = "/patientusermap/";
    public static String DOCTOR_COMMENTS = "/doctorcomments/";
    //fix this in each sub class
    public String pathPrefix = "/unknown/";
    public String[] pathKeys = {"id"};
    String id = null;

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        return map;
    }

    public String path() {
        String path = pathPrefix;
        HashMap<String, Object> map = this.toMap();
        for (String key : pathKeys) {
            path = path + map.get(key) + "/";
        }
        return path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPathPrefix() {
        return pathPrefix;
    }
}
