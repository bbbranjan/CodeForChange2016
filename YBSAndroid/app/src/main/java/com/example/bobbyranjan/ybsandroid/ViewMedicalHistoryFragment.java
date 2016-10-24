package com.example.bobbyranjan.ybsandroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bobbyranjan.ybsandroid.models.DoctorComments;
import com.example.bobbyranjan.ybsandroid.models.Model;
import com.example.bobbyranjan.ybsandroid.models.PatientMedicalHistory;
import com.example.bobbyranjan.ybsandroid.service.AsyncResultListener;
import com.example.bobbyranjan.ybsandroid.service.AsyncResultTask;
import com.example.bobbyranjan.ybsandroid.service.DoctorCommentsService;
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



    private OnFragmentInteractionListener mListener;
    private View view;
    public ViewMedicalHistoryFragment() {
        // Required empty public constructor
    }

    public static ViewMedicalHistoryFragment newInstance(String param1, String param2) {
        ViewMedicalHistoryFragment fragment = new ViewMedicalHistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            patientId = getArguments().getString(ARG_PARAM1);
            historyId = getArguments().getString(ARG_PARAM2);
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
        PatientMedicalHistoryService.getPatientMedicalHistory(patientId, historyId, new AsyncResultTask(new AsyncResultListener() {
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

    private void displayDoctorComments() {

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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
