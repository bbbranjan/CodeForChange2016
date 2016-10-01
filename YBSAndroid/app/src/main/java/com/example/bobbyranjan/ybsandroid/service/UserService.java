package com.example.bobbyranjan.ybsandroid.service;

import com.example.bobbyranjan.ybsandroid.models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
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

    public static void getUser(String uid,final AsyncResultTask task){
        User u = new User();
        u.setId(uid);
        retrieveModel(u.path(),User.class,task);
    }


}
