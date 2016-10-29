package com.example.bobbyranjan.ybsandroid.service;

import java.util.ArrayList;

/**
 * Created by Shiva on 10/29/2016.
 */

public interface FirebaseMultiValueListener<T> {
    void processResults(ArrayList<T> results);
}
