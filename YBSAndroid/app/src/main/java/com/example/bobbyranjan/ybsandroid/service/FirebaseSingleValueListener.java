package com.example.bobbyranjan.ybsandroid.service;

/**
 * Created by Shiva on 10/29/2016.
 */

public interface FirebaseSingleValueListener<T> {
    void processResult(T result);
}
