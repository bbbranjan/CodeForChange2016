package com.example.bobbyranjan.ybsandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;

import java.io.IOException;

/**
 * Created by Shiva on 10/30/2016.
 */
public class Util {
    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        if (image != null && !TextUtils.isEmpty(image)) {
            byte[] decodedByteArray = Base64.decode(image, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
        }
        return null;
    }
}
