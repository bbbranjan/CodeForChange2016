package com.example.bobbyranjan.ybsandroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bobbyranjan.ybsandroid.models.DoctorComments;
import com.example.bobbyranjan.ybsandroid.models.PatientMedicalHistory;
import com.example.bobbyranjan.ybsandroid.service.DoctorCommentsService;
import com.example.bobbyranjan.ybsandroid.service.FirebaseSingleValueListener;
import com.example.bobbyranjan.ybsandroid.service.PatientMedicalHistoryService;
import com.example.bobbyranjan.ybsandroid.service.PatientService;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewMedicalHistoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewMedicalHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewMedicalHistoryFragment extends Fragment {
    @Bind(R.id.vmr_PatientName)TextView patientName;
    @Bind(R.id.vmr_Date)TextView mDate;
    @Bind(R.id.vmr_rtwt)TextView mRTWT;
    @Bind(R.id.vmr_pregField1)TextView mG;
    @Bind(R.id.vmr_pregField2)TextView mP;
    @Bind(R.id.vmr_pregField3)TextView mA;
    @Bind(R.id.vmr_pregField4)TextView mAH;
    @Bind(R.id.vmr_inspectionResults)TextView mInspectionResults;
    @Bind(R.id.vmr_UK)TextView mUK;
    @Bind(R.id.vmr_Varices)TextView mVarices;
    @Bind(R.id.vmr_Oedema)TextView mOedema;
    @Bind(R.id.vmr_WTB)TextView mWTB;
    @Bind(R.id.vmr_TD)TextView mTD;
    @Bind(R.id.vmr_LILA)TextView mLILA;
    @Bind(R.id.vmr_NumVisit)TextView mNumVisit;
    @Bind(R.id.vmr_SF)TextView mSF;
    @Bind(R.id.vmr_HPHT)TextView mHPHT;
    @Bind(R.id.vmr_TP)TextView mTP;
    @Bind(R.id.vmr_Complaints)TextView mComplaints;
    @Bind(R.id.vmr_Information)TextView mInfo;
    @Bind(R.id.docCommentsLV)ExpandableHeightListView dcListView;

    private String patientId;
    private String historyId;


    public ViewMedicalHistoryFragment() {
        // Required empty public constructor
    }

    public static ViewMedicalHistoryFragment newInstance(String param1, String param2) {
        ViewMedicalHistoryFragment fragment = new ViewMedicalHistoryFragment();
        Bundle args = new Bundle();
        args.putString(Constants.PATIENT_ID, param1);
        args.putString(Constants.MEDICAL_HISTORY_ID, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            patientId = getArguments().getString(Constants.PATIENT_ID);
            historyId = getArguments().getString(Constants.MEDICAL_HISTORY_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_medical_history, container, false);
        ButterKnife.bind(this, view);

        displayMedHist();
        displayDoctorComments();
        return view;
    }

    private void displayMedHist() {
        PatientMedicalHistoryService.getPatientMedicalHistory(patientId, historyId, new FirebaseSingleValueListener<PatientMedicalHistory>() {
            @Override
            public void processResult(PatientMedicalHistory result) {
                mRTWT.setText(result.getRt_rw());
                mG.setText(result.getG());
                mP.setText(result.getP());
                mAH.setText(result.getAh());
                mInspectionResults.setText(result.getObservations());
                mUK.setText(result.getUk());
                mVarices.setText(result.getVarices());
                mOedema.setText(result.getOdema());
                mWTB.setText(result.getBb_tb());
                mTD.setText(result.getTd());
                mLILA.setText(result.getLila());
                mNumVisit.setText(result.getVisit());
                mSF.setText(result.getSf());
                mHPHT.setText(result.getHpht());
                mTP.setText(result.getTp());
                mComplaints.setText(result.getComplaints());
                mInfo.setText(result.getInformation());

                mDate.setText(result.getDate());
                PatientService.getPatient(patientId,patient->patientName.setText(patient.getName()));
            }
        });

    }

    private void displayDoctorComments() {

        DoctorCommentsService.getComments(patientId, historyId, results -> {
            List<String> dcList = new ArrayList<>();
            for (DoctorComments o : results) {
                dcList.add(o.getComments());

            }
            ArrayAdapter<String> doctorCommentsArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dcList);
            dcListView.setAdapter(doctorCommentsArrayAdapter);
            dcListView.setExpanded(true);
        });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof OnFragmentInteractionListener)) {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
