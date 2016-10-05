package com.example.bobbyranjan.ybsandroid.models;

import java.util.HashMap;

/**
 * Created by hari on 24/9/16.
 */

public class User extends Model {

    String name;
    String email;
    String phoneNumber;
    String role;

    {
        pathPrefix = USERS;
    }

    public User() {
    }

    public User(String name, String email, String phoneNumber, String role) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = super.toMap();
        map.put("name", name);
        map.put("email", email);
        map.put("phoneNumber", phoneNumber);
        map.put("role", role);
        return map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
