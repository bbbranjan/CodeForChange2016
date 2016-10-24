package com.example.bobbyranjan.ybsandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
=======
>>>>>>> master
import android.widget.TextView;

import com.example.bobbyranjan.ybsandroid.models.DoctorComments;
import com.example.bobbyranjan.ybsandroid.models.Model;
import com.example.bobbyranjan.ybsandroid.models.PatientMedicalHistory;
import com.example.bobbyranjan.ybsandroid.service.AsyncResultListener;
import com.example.bobbyranjan.ybsandroid.service.AsyncResultTask;
import com.example.bobbyranjan.ybsandroid.service.PatientMedicalHistoryService;

<<<<<<< HEAD
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
=======
>>>>>>> master

public class ViewMedicalHistoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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

    ListView dcListView;

    private String patientId;
    private String historyId;



    private View view;
    public ViewMedicalHistoryFragment() {
        // Required empty public constructor
    }

    public static ViewMedicalHistoryFragment newInstance(String patientId, String pmhId) {
        ViewMedicalHistoryFragment fragment = new ViewMedicalHistoryFragment();
        Bundle args = new Bundle();
        args.putString(Constants.PATIENT_ID, patientId);
        args.putString(Constants.MEDICAL_HISTORY_ID, pmhId);
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
        view = inflater.inflate(R.layout.fragment_add_medical_history, container, false);

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

        dcListView = (ListView) view.findViewById(R.id.docCommentsLV);

        displayMedHist();

        displayDoctorComments();

        return view;
    }

    private void displayMedHist() {

        final String path = Model.PATIENT_HISTORY + patientId + "/" + historyId;
<<<<<<< HEAD
        String id = PatientMedicalHistoryService.getKey(path);
        PatientMedicalHistoryService.getPatientMedicalHistory(patientId, id, new AsyncResultTask(new AsyncResultListener() {
=======
        PatientMedicalHistoryService.getPatientMedicalHistory(patientId, historyId, new AsyncResultTask(new AsyncResultListener() {
>>>>>>> master
            @Override
            public void processResult(Object result) {
                PatientMedicalHistory patientMedicalHistory = (PatientMedicalHistory) result;
                mRTWT.setText(patientMedicalHistory.getRt_rw());
                mG.setText(patientMedicalHistory.getG());
                mP.setText(patientMedicalHistory.getP());
                mAH.setText(patientMedicalHistory.getAh());
                mInspectionResults.setText(patientMedicalHistory.getObservations());
                mUK.setText(patientMedicalHistory.getUk());
                mVarices.setText(patientMedicalHistory.getVarices());
                mOedema.setText(patientMedicalHistory.getOdema());
                mWTB.setText(patientMedicalHistory.getBb_tb());
                mTD.setText(patientMedicalHistory.getTd());
                mLILA.setText(patientMedicalHistory.getLila());
                mNumVisit.setText(patientMedicalHistory.getVisit());
                mSF.setText(patientMedicalHistory.getSf());
                mHPHT.setText(patientMedicalHistory.getHpht());
                mTP.setText(patientMedicalHistory.getTp());
                mComplaints.setText(patientMedicalHistory.getComplaints());
                mInfo.setText(patientMedicalHistory.getInformation());
            }

            @Override
            public void processResults(Object... results) {

            }
        }));

    }

<<<<<<< HEAD
    private void displayDoctorComments() {

        final String path = Model.DOCTOR_COMMENTS + patientId + "/" + historyId;

        DoctorCommentsService.getComments(patientId, historyId, new AsyncResultTask(new AsyncResultListener() {
            @Override
            public void processResult(Object result) {

            }

            @Override
            public void processResults(Object... results) {
                List<String> dcList = new ArrayList<>();
                for(Object o:results) {
                    dcList.add(((DoctorComments) o).getComments());

                }
                ArrayAdapter<String> doctorCommentsArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.fragment_view_medical_history, dcList);
                dcListView.setAdapter(doctorCommentsArrayAdapter);
            }
        }));

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddNewPatient");
        }
    }
=======
>>>>>>> master

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
