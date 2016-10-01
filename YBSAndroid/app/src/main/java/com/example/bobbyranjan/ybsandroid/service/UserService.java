package com.example.bobbyranjan.ybsandroid.service;

import com.example.bobbyranjan.ybsandroid.models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.List;

/**
 * Created by hari on 1/10/16.
 */

public class UserService extends Service {

    public static Task<AuthResult> registerUser(String email, String password){
        return auth.createUserWithEmailAndPassword(email, password);
    }

    public static Task<AuthResult> login(String email, String password) {
        return auth.signInWithEmailAndPassword(email, password);
    }

    public static void persistUser(String id,String name, String email,String phone,String role){
        User user = new User(name, email, phone, role);
        user.setId(id);
        persistModel(user);
    }

    public static String getCurrentUserUUID() {
        return auth.getCurrentUser().getUid();
    }

}
