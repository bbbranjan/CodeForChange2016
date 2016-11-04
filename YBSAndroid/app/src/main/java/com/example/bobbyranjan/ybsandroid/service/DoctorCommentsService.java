package com.example.bobbyranjan.ybsandroid.service;

import com.example.bobbyranjan.ybsandroid.models.DoctorComments;
import com.example.bobbyranjan.ybsandroid.models.Model;

/**
 * Created by hari on 3/10/16.
 */

public class DoctorCommentsService extends Service {

    public static void persistDoctorComments(String id, String patientId,String patientHistoryId,String comments){
        DoctorComments dc = new DoctorComments(patientId,patientHistoryId,comments);
        dc.setId(id);
        persistModel(dc);
    }

    public static void getComments(String patientId, String patientHistoryId, final FirebaseMultiValueListener<DoctorComments> task) {
        String path = Model.DOCTOR_COMMENTS+patientId+"/"+patientHistoryId;
        retrieveModels(path,DoctorComments.class,task,null);
    }

}
