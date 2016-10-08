package com.example.bobbyranjan.ybsandroid;

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

import com.example.bobbyranjan.ybsandroid.models.Model;
import com.example.bobbyranjan.ybsandroid.service.PatientService;
import com.example.bobbyranjan.ybsandroid.service.PatientUserMappingService;
import com.example.bobbyranjan.ybsandroid.service.UserService;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddNewPatientFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddNewPatientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNewPatientFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText mName;
    EditText mHusbandsName;
    EditText mAge;
    EditText mVillage;
    EditText mDOB;
    Button mSave;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private View view;

    public AddNewPatientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddNewPatientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNewPatientFragment newInstance(String param1, String param2) {
        AddNewPatientFragment fragment = new AddNewPatientFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_new_patient, container, false);
        mName = (EditText) view.findViewById(R.id.ANPPatientName);
        mHusbandsName = (EditText) view.findViewById(R.id.ANPHusbandName);
        mVillage = (EditText) view.findViewById(R.id.ANPVillage);
        mAge = (EditText) view.findViewById(R.id.ANPAge);
        mDOB = (EditText) view.findViewById(R.id.ANPDOB);

        mSave = (Button) view.findViewById(R.id.ANPSave);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePatient();
            }
        });

        return view;
    }

    private void savePatient() {
        String name = mName.getText().toString();
        String husband = mHusbandsName.getText().toString();
        String village = mVillage.getText().toString();
        int age = Integer.valueOf(mAge.getText().toString());
        String dob = mDOB.getText().toString();

        String id = PatientService.getKey(Model.PATIENT);
        PatientService.persistPatient(id, name, husband, village, age, dob, 1, false, false);
        String user = UserService.getCurrentUserUUID();
        PatientUserMappingService.persistPatientUserMapping(user, id);
        startActivity(new Intent(view.getContext(), NavigationActivity.class));
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
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
