package com.example.bobbyranjan.ybsandroid.models;

import java.util.HashMap;

/**
 * Created by hari on 24/9/16.
 */
public class Model {
    String id;

    //fix this in each sub class
    public String pathPrefix="/unknown/";
    public String[] pathKeys = {"id"};

    public HashMap<String,String> toMap(){
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("id",id);
        return map;
    }

    public String path(){
        String path = pathPrefix;
        HashMap<String,String> map = this.toMap();
        for(String key:pathKeys){
            path = path+map.get(key)+"/";
        }
        return path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
