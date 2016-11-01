package com.example.bobbyranjan.ybsandroid;

import android.content.Context;
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
import com.example.bobbyranjan.ybsandroid.service.DoctorCommentsService;
import com.example.bobbyranjan.ybsandroid.service.PatientService;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddDoctorCommentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddDoctorCommentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddDoctorCommentFragment extends Fragment {

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText mComments;
    private TextView mPatientName;

    public AddDoctorCommentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param patientId Parameter 1.
     * @param historyId Parameter 2.
     * @return A new instance of fragment AddDoctorCommentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddDoctorCommentFragment newInstance(String patientId, String historyId) {
        AddDoctorCommentFragment fragment = new AddDoctorCommentFragment();
        Bundle args = new Bundle();
        args.putString(Constants.PATIENT_ID, patientId);
        args.putString(Constants.MEDICAL_HISTORY_ID, historyId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(Constants.PATIENT_ID);
            mParam2 = getArguments().getString(Constants.MEDICAL_HISTORY_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_doctor_comment, container, false);
        mComments = (EditText) view.findViewById(R.id.doctor_comments);
        Button mSubmit = (Button) view.findViewById(R.id.submitComments);
        mPatientName = (TextView) view.findViewById(R.id.andc_PatientName);
        final String patientId = mParam1;
        final String historyId = mParam2;
        PatientService.getPatient(patientId, result -> mPatientName.setText(result.getName()));
        mSubmit.setOnClickListener(view1 -> {
            String path = Model.DOCTOR_COMMENTS + patientId + "/" + historyId;
            String id = DoctorCommentsService.getKey(path);
            DoctorCommentsService.persistDoctorComments(id, patientId, historyId, mComments.getText().toString());
            this.getActivity().finish();
        });
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
