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
import java.util.Iterator;
import java.util.Map;

/**
 * Created by hari on 1/10/16.
 */

public class Service {

    public static FirebaseAuth auth = FirebaseAuth.getInstance();
    public static DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    static{
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    {
        //we dont want to sync users - that might be needed later
        //this keeps only the last 10MB in sync and handles purge for us
        db.child(Model.PATIENT).keepSynced(true);
        db.child(Model.PATIENT_HISTORY).keepSynced(true);
        db.child(Model.PATIENT_USER).keepSynced(true);
        db.child(Model.DOCTOR_COMMENTS).keepSynced(true);
    }

    protected static void persistModel(Model... models) {
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
            throw new IllegalArgumentException("Inavlid method for User. User user UID instead");
        }
        String db_path = model.pathPrefix;
        return db.child(db_path).push().getKey();
    }

    protected static void retrieveModel(String path, final Class model, final AsyncResultTask task) {
        db.child(path).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList results = new ArrayList();
                results.add(dataSnapshot.getValue(model));
                task.execute(results.toArray());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    protected static void retrieveModels(String path, final Class model, final AsyncResultTask task) {
        db.child(path).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList results = new ArrayList();
                Iterator<DataSnapshot> snapshots = dataSnapshot.getChildren().iterator();
                while (snapshots.hasNext()) {
                    DataSnapshot ds = snapshots.next();
                    results.add(ds.getValue(model));
                }
                task.execute(results.toArray());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
