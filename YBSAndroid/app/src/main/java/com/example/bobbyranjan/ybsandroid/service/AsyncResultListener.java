package com.example.bobbyranjan.ybsandroid.service;

/**
 * Created by hari on 1/10/16.
 */

public interface AsyncResultListener<T> {
    void processResult(T result);

    void processResults(T... results);
}
