package com.example.bobbyranjan.ybsandroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bobbyranjan.ybsandroid.models.Patient;
import com.example.bobbyranjan.ybsandroid.service.PatientService;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewPatientFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewPatientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewPatientFragment extends Fragment {

    TextView mName;
    TextView mHusbandName;
    TextView mVillage;
    TextView mDOB;
    TextView mAge;
    View view;
    private String patientId;

    public ViewPatientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param patientId Parameter 1.
     * @return A new instance of fragment ViewPatientFragment.
     */
    public static ViewPatientFragment newInstance(String patientId) {
        ViewPatientFragment fragment = new ViewPatientFragment();
        Bundle args = new Bundle();
        args.putString(Constants.PATIENT_ID, patientId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            patientId = getArguments().getString(Constants.PATIENT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_view_patient, container, false);
        mName = (TextView)view.findViewById(R.id.VNPPatientName);
        mHusbandName = (TextView)view.findViewById(R.id.VNPHusbandName);
        mVillage = (TextView)view.findViewById(R.id.VNPVillage);
        mDOB = (TextView)view.findViewById(R.id.VNPDOB);
        mAge = (TextView)view.findViewById(R.id.VNPAge);
        PatientService.getPatient(patientId, this::processResult);

        return view;
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

    public void processResult(Patient result) {
        mName.setText(result.getName());
        mHusbandName.setText(result.getHusbandsName());
        mVillage.setText(result.getLocation());
        mDOB.setText(result.getDateOfBirth());
        mAge.setText(String.valueOf(result.getAge()));
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
