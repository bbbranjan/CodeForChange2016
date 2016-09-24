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

    public Patient(){
        pathPrefix="/patients/";
    }

    @Override
    public HashMap<String, String> toMap() {
        HashMap<String,String> map =  super.toMap();
        map.put("name",name);
        map.put("husbandsName",husbandsName);
        map.put("location",location);
        map.put("age",age);
        map.put("dateOfBirth",dateOfBirth);
        map.put("numPregnancy",numPregnancy);
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


}
