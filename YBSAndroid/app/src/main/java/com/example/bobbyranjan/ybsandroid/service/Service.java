package com.example.bobbyranjan.ybsandroid.service;

import com.example.bobbyranjan.ybsandroid.models.Model;
import com.example.bobbyranjan.ybsandroid.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hari on 1/10/16.
 */

public class Service {

    public static FirebaseAuth auth = FirebaseAuth.getInstance();
    static DatabaseReference presence;
    private static DatabaseReference db;

    static {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        db = FirebaseDatabase.getInstance().getReference();
        presence = FirebaseDatabase.getInstance().getReference(".info/connected");

    }

    static {
        //we dont want to sync users - that might be needed later
        //this keeps only the last 10MB in sync and handles purge for us
        db.child(Model.PATIENT).keepSynced(true);
        db.child(Model.PATIENT_HISTORY).keepSynced(true);
        db.child(Model.PATIENT_USER).keepSynced(true);
        db.child(Model.DOCTOR_COMMENTS).keepSynced(true);
    }

    static void persistModel(Model... models) {
        Map updates = new HashMap<String, Map<String, String>>();
        for (Model model : models) {
            if (model.getId() == null) {
                throw new IllegalArgumentException("No ID set on the model!");
            }
            updates.put(model.path(), model.toMap());
        }
        db.updateChildren(updates);
    }

    protected static String getKey(Model model) {
        if (model instanceof User) {
            throw new IllegalArgumentException("Invalid method for User. User user UID instead");
        }
        String db_path = model.pathPrefix;
        return db.child(db_path).push().getKey();
    }

    public static String getKey(String path) {
        if (path.startsWith(Model.USERS)) {
            throw new IllegalArgumentException("Invalid method for '/users/'. User user UID instead");
        }
        return db.child(path).push().getKey();
    }

    static void deleteModel(String path){
        db.child(path).removeValue();
    }

    static <T> void searchModel(String path,final Class<T> model, final FirebaseMultiValueListener<T> task, String key, String pattern){
        db.child(path).orderByChild(key).startAt(pattern).endAt(pattern+"\uf8ff").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<T> results = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    T value = ds.getValue(model);
                    results.add(value);
                }
                task.processResults(results);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    static <T> void retrieveModel(String path, final Class<T> model, final FirebaseSingleValueListener<T> task) {
        db.child(path).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                task.processResult(dataSnapshot.getValue(model));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    static <T> void retrieveModels(String path, final Class<T> model, final FirebaseMultiValueListener<T> task, String orderBy) {
        if(orderBy==null){
            orderBy="id";
        }
        db.child(path).orderByChild(orderBy).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<T> results = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    results.add(ds.getValue(model));
                }
                task.processResults(results);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
