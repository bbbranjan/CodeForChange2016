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
import java.util.List;
import java.util.Map;

/**
 * Created by hari on 1/10/16.
 */

public class Service {

    public static FirebaseAuth auth = FirebaseAuth.getInstance();
    public static DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    protected static void persistModel(Model... models){
        Map updates = new HashMap<String,Map<String,String>>();
        for(Model model:models){
            if(model.getId()==null){
                throw new IllegalArgumentException("No ID set on the model!");
            }
            updates.put(model.path(),model.toMap());
        }
        db.updateChildren(updates);
    }

    protected static String getKey(Model model){
        if(model instanceof User){
            throw new IllegalArgumentException("Inavlid method for User. User user UID instead");
        }
        String db_path = model.pathPrefix;
        return db.child(db_path).push().getKey();
    }

    protected static void retrieveModel(String path, final Class model, final AsyncResultTask task){
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


}
