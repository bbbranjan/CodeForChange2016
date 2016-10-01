package com.example.bobbyranjan.ybsandroid.service;

import android.os.AsyncTask;

/**
 * Created by hari on 1/10/16.
 */

public class AsyncResultTask extends AsyncTask {

    AsyncResultListener listener;
    Object[] results = {};

    public AsyncResultTask(AsyncResultListener listener){
        this.listener = listener;
    }


    @Override
    protected Object doInBackground(Object[] params) {
        this.results = params;
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        if(results.length==1){
            listener.processResult(results[0]);
        }else if(results.length>1){
            listener.processResults(results);
        }
    }
}
