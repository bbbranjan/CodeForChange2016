package com.example.bobbyranjan.ybsandroid.service;

import com.example.bobbyranjan.ybsandroid.models.Model;
import com.example.bobbyranjan.ybsandroid.models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by hari on 1/10/16.
 */

public class UserService extends Service {

    public static Task<AuthResult> registerUser(String email, String password) {
        return auth.createUserWithEmailAndPassword(email, password);
    }

    public static Task<AuthResult> login(String email, String password) {
        return auth.signInWithEmailAndPassword(email, password);
    }

    public static void persistUser(String id, String name, String email, String phone, String role) {
        User user = new User(name, email, phone, role);
        user.setId(id);
        persistModel(user);
    }

    public static String getCurrentUserUUID() {
        return auth.getCurrentUser().getUid();
    }

    public static String getCurrentUserEmail() {
        return auth.getCurrentUser().getEmail();
    }

    public static void getUser(String uid, final FirebaseSingleValueListener<User> task) {
        User u = new User();
        u.setId(uid);
        retrieveModel(u.path(), User.class, task);
    }

    public static void getAllUsers(final FirebaseMultiValueListener<User> task) {
        retrieveModels(Model.USERS, User.class, task);
    }


    public static void passwordReset(String email) {
        auth.sendPasswordResetEmail(email);
    }

    public static void listenPresence(final PresenceListener listener){
        presence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    listener.connected();
                } else {
                    listener.disconnected();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

    }
}
