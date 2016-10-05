package com.example.bobbyranjan.ybsandroid.models;

import java.util.HashMap;

/**
 * Created by hari on 24/9/16.
 */

public class Patient extends Model {

    String name;
    String husbandsName;
    String location;
    String age;
    String dateOfBirth;
    String numPregnancy;

    boolean critical;
    boolean needsMoreGuidance;

    {
        pathPrefix = PATIENT;
    }

    public Patient() {
    }

    public Patient(String name, String husbandsName, String location, String age, String dateOfBirth, String numPregnancy, boolean critical, boolean needsMoreGuidance) {
        this.name = name;
        this.husbandsName = husbandsName;
        this.location = location;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.numPregnancy = numPregnancy;
        this.critical = critical;
        this.needsMoreGuidance = needsMoreGuidance;
    }

    @Override
    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = super.toMap();
        map.put("name", name);
        map.put("husbandsName", husbandsName);
        map.put("location", location);
        map.put("age", age);
        map.put("dateOfBirth", dateOfBirth);
        map.put("numPregnancy", numPregnancy);
        map.put("critical", critical);
        map.put("needsMoreGuidance", numPregnancy);
        return map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHusbandsName() {
        return husbandsName;
    }

    public void setHusbandsName(String husbandsName) {
        this.husbandsName = husbandsName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNumPregnancy() {
        return numPregnancy;
    }

    public void setNumPregnancy(String numPregnancy) {
        this.numPregnancy = numPregnancy;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    public boolean isNeedsMoreGuidance() {
        return needsMoreGuidance;
    }

    public void setNeedsMoreGuidance(boolean needsMoreGuidance) {
        this.needsMoreGuidance = needsMoreGuidance;
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
