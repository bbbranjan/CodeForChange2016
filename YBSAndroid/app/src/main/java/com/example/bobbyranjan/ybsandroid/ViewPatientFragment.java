package com.example.bobbyranjan.ybsandroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bobbyranjan.ybsandroid.models.Patient;
import com.example.bobbyranjan.ybsandroid.service.PatientService;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewPatientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewPatientFragment extends Fragment {
    View view;
    @Bind(R.id.civProfileImage) CircleImageView civProfileImage;
    @Bind(R.id.tvPatientName) TextView tvPatientName;
    @Bind(R.id.tvAge) TextView tvAge;
    @Bind(R.id.tvHusbandName) TextView tvHusbandName;
    @Bind(R.id.tvDateOfBirth) TextView tvDateOfBirth;
    @Bind(R.id.tvPhoneNumber) TextView tvPhoneNumber;
    @Bind(R.id.tvLocation) TextView tvLocation;
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
        view = inflater.inflate(R.layout.fragment_view_patient, container, false);
        ButterKnife.bind(this, view);
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

    public void processResult(Patient patient) {
        try {
            String profileImage = patient.getProfileImage();
            if (profileImage != null && !TextUtils.isEmpty(profileImage)) {
                civProfileImage.setImageBitmap(Util.decodeFromFirebaseBase64(profileImage));
            } else {
                civProfileImage.setImageResource(R.drawable.ic_pregnant_woman_64dp);
            }
        } catch (IOException e) {
            civProfileImage.setImageResource(R.drawable.ic_pregnant_woman_64dp);
        }
        tvPatientName.setText(patient.getName());
        tvHusbandName.setText(patient.getHusbandsName());
        tvLocation.setText(patient.getLocation());
        tvDateOfBirth.setText(patient.getDateOfBirth());
        tvAge.setText(String.valueOf(patient.getAge()));
        tvPhoneNumber.setText(String.valueOf(patient.getPhone()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
