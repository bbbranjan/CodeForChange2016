package com.example.bobbyranjan.ybsandroid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bobbyranjan.ybsandroid.models.PatientMedicalHistory;
import com.example.bobbyranjan.ybsandroid.service.AsyncResultListener;
import com.example.bobbyranjan.ybsandroid.service.AsyncResultTask;
import com.example.bobbyranjan.ybsandroid.service.PatientMedicalHistoryService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PatientMedicalHistoryListFragment extends Fragment implements AsyncResultListener {

    private static final String ARG_COLUMN_COUNT = "column-count";
    String patientId;
    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PatientMedicalHistoryListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PatientMedicalHistoryListFragment newInstance(String patientId) {
        PatientMedicalHistoryListFragment fragment = new PatientMedicalHistoryListFragment();
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
        View view = inflater.inflate(R.layout.fragment_patient_medical_history_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            recyclerView = (RecyclerView) view;
            AsyncResultTask task = new AsyncResultTask(this);
            PatientMedicalHistoryService.getAllMedicalHistoriesForPatient(patientId, task);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    void setViewItems(List<PatientMedicalHistory> patientMedicalHistoryList) {
        recyclerView.setAdapter(new PatientMedicalHistoryListViewAdapter(patientMedicalHistoryList, mListener));
    }

    @Override
    public void processResult(Object result) {
        PatientMedicalHistory[] patients = {(PatientMedicalHistory) result};
        setViewItems(new ArrayList<PatientMedicalHistory>(Arrays.asList(patients)));
    }

    @Override
    public void processResults(Object... results) {
        PatientMedicalHistory[] patientMedicalHistories = Arrays.copyOf(results, results.length, PatientMedicalHistory[].class);
        setViewItems(new ArrayList<PatientMedicalHistory>(Arrays.asList(patientMedicalHistories)));
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
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(PatientMedicalHistory item, Constants.ActionType actionType);
    }
}
