package com.example.bobbyranjan.ybsandroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bobbyranjan.ybsandroid.models.DoctorComments;
import com.example.bobbyranjan.ybsandroid.models.PatientMedicalHistory;
import com.example.bobbyranjan.ybsandroid.service.DoctorCommentsService;
import com.example.bobbyranjan.ybsandroid.service.FirebaseSingleValueListener;
import com.example.bobbyranjan.ybsandroid.service.PatientMedicalHistoryService;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewMedicalHistoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewMedicalHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewMedicalHistoryFragment extends Fragment {
    TextView mRTWT;
    TextView mG;
    TextView mP;
    TextView mA;
    TextView mAH;
    TextView mInspectionResults;
    TextView mUK;
    TextView mVarices;
    TextView mOedema;
    TextView mWTB;
    TextView mTD;
    TextView mLILA;
    TextView mNumVisit;
    TextView mSF;
    TextView mHPHT;
    TextView mTP;
    TextView mComplaints;
    TextView mInfo;
    ExpandableHeightListView dcListView;

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

        mRTWT = (TextView) view.findViewById(R.id.mr_rtwt);
        mG = (TextView) view.findViewById(R.id.mr_pregField1);
        mP = (TextView) view.findViewById(R.id.mr_pregField2);
        mA = (TextView) view.findViewById(R.id.mr_pregField3);
        mAH = (TextView) view.findViewById(R.id.mr_pregField4);
        mInspectionResults = (TextView) view.findViewById(R.id.mr_inspectionResults);
        mUK = (TextView) view.findViewById(R.id.mr_UK);
        mVarices = (TextView) view.findViewById(R.id.mr_Varices);
        mOedema = (TextView) view.findViewById(R.id.mr_Oedema);
        mWTB = (TextView) view.findViewById(R.id.mr_WTB);
        mTD = (TextView) view.findViewById(R.id.mr_TD);
        mLILA = (TextView) view.findViewById(R.id.mr_LILA);
        mNumVisit = (TextView) view.findViewById(R.id.mr_NumVisit);
        mSF = (TextView) view.findViewById(R.id.mr_SF);
        mHPHT = (TextView) view.findViewById(R.id.mr_HPHT);
        mTP = (TextView) view.findViewById(R.id.mr_TP);
        mComplaints = (TextView) view.findViewById(R.id.mr_Complaints);
        mInfo = (TextView) view.findViewById(R.id.mr_Information);

        dcListView = (ExpandableHeightListView) view.findViewById(R.id.docCommentsLV);

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
