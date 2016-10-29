package com.example.bobbyranjan.ybsandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bobbyranjan.ybsandroid.models.Model;
import com.example.bobbyranjan.ybsandroid.service.PatientMedicalHistoryService;
import com.example.bobbyranjan.ybsandroid.service.PatientService;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddMedicalHistoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddMedicalHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddMedicalHistoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView patientName;
    EditText mRTWT;
    EditText mG;
    EditText mP;
    EditText mA;
    EditText mAH;
    EditText mInspectionResults;
    EditText mUK;
    EditText mVarices;
    EditText mOedema;
    EditText mWTB;
    EditText mTD;
    EditText mLILA;
    EditText mNumVisit;
    EditText mSF;
    EditText mHPHT;
    EditText mTP;
    EditText mComplaints;
    EditText mInfo;

    Button mSave;

    // TODO: Rename and change types of parameters
    private String patientId;


    private View view;
    public AddMedicalHistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddMedicalHistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddMedicalHistoryFragment newInstance(String param1, String param2) {
        AddMedicalHistoryFragment fragment = new AddMedicalHistoryFragment();
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
            patientId = getArguments().getString("patientId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_medical_history, container, false);

        mRTWT = (EditText) view.findViewById(R.id.mr_rtwt);
        mG = (EditText) view.findViewById(R.id.mr_pregField1);
        mP = (EditText) view.findViewById(R.id.mr_pregField2);
        mA = (EditText) view.findViewById(R.id.mr_pregField3);
        mAH = (EditText) view.findViewById(R.id.mr_pregField4);
        mInspectionResults = (EditText) view.findViewById(R.id.mr_inspectionResults);
        mUK = (EditText) view.findViewById(R.id.mr_UK);
        mVarices = (EditText) view.findViewById(R.id.mr_Varices);
        mOedema = (EditText) view.findViewById(R.id.mr_Oedema);
        mWTB = (EditText) view.findViewById(R.id.mr_WTB);
        mTD = (EditText) view.findViewById(R.id.mr_TD);
        mLILA = (EditText) view.findViewById(R.id.mr_LILA);
        mNumVisit = (EditText) view.findViewById(R.id.mr_NumVisit);
        mSF = (EditText) view.findViewById(R.id.mr_SF);
        mHPHT = (EditText) view.findViewById(R.id.mr_HPHT);
        mTP = (EditText) view.findViewById(R.id.mr_TP);
        mComplaints = (EditText) view.findViewById(R.id.mr_Complaints);
        mInfo = (EditText) view.findViewById(R.id.mr_Information);
        patientName = (TextView) view.findViewById(R.id.med_PatientName);

        mSave = (Button) view.findViewById(R.id.mr_saveButton);

        mSave.setOnClickListener(v -> saveMedHist());

        PatientService.getPatient(patientId, result -> patientName.setText(result.getName()));

        return view;
    }

    private void saveMedHist() {
        String rtwt = mRTWT.getText().toString();
        String pG = mG.getText().toString();
        String pP = mP.getText().toString();
        String pA = mP.getText().toString();
        String pAH = mAH.getText().toString();
        String IR = mInspectionResults.getText().toString();
        String UK = mUK.getText().toString();
        String Varices = mVarices.getText().toString();
        String Oedema = mOedema.getText().toString();
        String WTB = mWTB.getText().toString();
        String TD = mTD.getText().toString();
        String LILA = mLILA.getText().toString();
        String numberOfVisits = mNumVisit.getText().toString();
        String SF = mSF.getText().toString();
        String HPHT = mHPHT.getText().toString();
        String TP = mTP.getText().toString();
        String complaints = mComplaints.getText().toString();
        String info = mInfo.getText().toString();

        Calendar c = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String currDate;
        currDate = df.format(c.getTime());

        String id = PatientMedicalHistoryService.getKey(Model.PATIENT_HISTORY+patientId);
        PatientMedicalHistoryService.persistPatientMedicalHistory(id, patientId, currDate, rtwt, pG, pP, pA, pAH, IR, UK, Varices, Oedema, WTB, TD, LILA, numberOfVisits, SF, HPHT, TP, complaints, info);
        Intent intent = new Intent(view.getContext(), PatientMedicalHistoryActivity.class);
        intent.putExtra(Constants.PATIENT_ID,patientId);
        startActivity(intent);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof OnFragmentInteractionListener)) {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddNewPatient");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
