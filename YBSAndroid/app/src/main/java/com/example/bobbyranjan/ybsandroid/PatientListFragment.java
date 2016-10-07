package com.example.bobbyranjan.ybsandroid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bobbyranjan.ybsandroid.models.Patient;
import com.example.bobbyranjan.ybsandroid.service.AsyncResultListener;
import com.example.bobbyranjan.ybsandroid.service.AsyncResultTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PatientListFragment extends Fragment implements AsyncResultListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PatientListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PatientListFragment newInstance(int columnCount) {
        PatientListFragment fragment = new PatientListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            AsyncResultTask task = new AsyncResultTask(this);
            //PatientService.getAllPatients(task);
            setViewItems(new ArrayList<Patient>());
        }
        return view;
    }

    void setViewItems(List<Patient> patients) {
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), mColumnCount));
        }
        addMockPatients(patients);
        recyclerView.setAdapter(new PatientListViewAdapter(patients, mListener));
    }

    private void addMockPatients(List<Patient> patients) {
        Patient patient = new Patient();
        patient.setName("test1");
        patient.setId("id1");
        patient.setHusbandsName("Husband Id1");
        patient.setAge("30");
        patients.add(patient);
        patient = new Patient();
        patient.setName("test2");
        patient.setId("id2");
        patient.setHusbandsName("Husband Id2");
        patients.add(patient);
        patient = new Patient();
        patient.setName("test3");
        patient.setId("id3");
        patient.setHusbandsName("Husband Id3");
        patients.add(patient);
        patient = new Patient();
        patient.setName("test4");
        patient.setId("id4");
        patient.setHusbandsName("Husband Id4");
        patients.add(patient);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void processResult(Object result) {

    }

    @Override
    public void processResults(Object... results) {
        Patient[] patients = Arrays.copyOf(results, results.length, Patient[].class);
        setViewItems(new ArrayList<Patient>(Arrays.<Patient>asList(patients)));
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
        void onListFragmentInteraction(Patient item, Constants.ActionType patientDetails);
    }
}
