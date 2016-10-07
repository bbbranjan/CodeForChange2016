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
import com.example.bobbyranjan.ybsandroid.models.Patient;
import com.example.bobbyranjan.ybsandroid.service.AsyncResultListener;
import com.example.bobbyranjan.ybsandroid.service.AsyncResultTask;
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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private EditText mComments;
    private TextView mPatientName;
    private TextView mPatientID;
    private Button mSubmit;

    public AddDoctorCommentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddDoctorCommentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddDoctorCommentFragment newInstance(String param1, String param2) {
        AddDoctorCommentFragment fragment = new AddDoctorCommentFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_doctor_comment, container, false);
        mComments = (EditText) view.findViewById(R.id.doctor_comments);
        mSubmit = (Button) view.findViewById(R.id.submitComments);
        mPatientName = (TextView) view.findViewById(R.id.andc_PatientName);
        mPatientID = (TextView) view.findViewById(R.id.andc_PatientID);
        final String patientId = mParam1;
        final String historyId = mParam2;
        mPatientID.setText(patientId);
        PatientService.getPatient(patientId, new AsyncResultTask(new AsyncResultListener() {
            @Override
            public void processResult(Object result) {
                Patient patient = (Patient) result;
                mPatientName.setText(patient.getName());
            }

            @Override
            public void processResults(Object... results) {

            }
        }));
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = Model.DOCTOR_COMMENTS + patientId + "/" + historyId;
                String id = DoctorCommentsService.getKey(path);
                DoctorCommentsService.persistDoctorComments(id, patientId, historyId, mComments.getText().toString());
            }
        });
        return view;
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
