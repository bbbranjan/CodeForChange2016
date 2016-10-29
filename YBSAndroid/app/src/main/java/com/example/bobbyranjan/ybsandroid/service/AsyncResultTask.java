package com.example.bobbyranjan.ybsandroid.service;

import android.os.AsyncTask;

/**
 * Created by hari on 1/10/16.
 */

public class AsyncResultTask<T> extends AsyncTask<T, Void, Void> {

    AsyncResultListener<T> listener;
    T[] results = null;

    public AsyncResultTask(AsyncResultListener<T> listener) {
        this.listener = listener;
    }


    @Override
    protected Void doInBackground(T... params) {
        this.results = params;
        return null;
    }

    @Override
    protected void onPostExecute(Void o) {
        if (results.length == 1) {
            listener.processResult(results[0]);
        } else if (results.length > 1) {
            listener.processResults(results);
        }
    }
}
