package com.example.bobbyranjan.ybsandroid;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bobbyranjan.ybsandroid.PatientListFragment.OnListFragmentInteractionListener;
import com.example.bobbyranjan.ybsandroid.models.Patient;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Patient} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PatientListViewAdapter extends RecyclerView.Adapter<PatientListViewAdapter.ViewHolder> {

    private final List<Patient> mValues;
    private final OnListFragmentInteractionListener mListener;

    public PatientListViewAdapter(List<Patient> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_patient_list_view_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Patient patient = mValues.get(position);
        holder.patient = patient;
        holder.tvPatientName.setText(patient.getName());
        holder.tvHusbandName.setText(patient.getHusbandsName());
        holder.tvAge.setText(String.valueOf(patient.getAge()));
        holder.setListeners();

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final View mView;
        final TextView tvPatientName;
        final TextView tvHusbandName;
        final TextView tvAge;
        ImageView ivViewProfile, ivAddMedicalRecord;

        Patient patient;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvPatientName = (TextView) view.findViewById(R.id.tvPatientName);
            tvHusbandName = (TextView) view.findViewById(R.id.tvHusbandName);
            tvAge = (TextView) view.findViewById(R.id.tvAge);
            ivViewProfile = (ImageView) itemView.findViewById(R.id.iv_view_profile);
            ivAddMedicalRecord = (ImageView) itemView.findViewById(R.id.iv_add_medical_record);

        }

        public void setListeners() {
            ivViewProfile.setOnClickListener(ViewHolder.this);
            ivAddMedicalRecord.setOnClickListener(ViewHolder.this);
        }
        @Override
        public String toString() {
            return super.toString() + " '" + tvHusbandName.getText() + "'";
        }

        @Override
        public void onClick(View v) {
            Intent i = null;
            switch (v.getId()) {
                case R.id.iv_view_profile:
                    mListener.onListFragmentInteraction(this.patient, Constants.ActionType.PatientDetails);
                    break;

                case R.id.iv_add_medical_record:
                    mListener.onListFragmentInteraction(this.patient, Constants.ActionType.AddNewMedicalRecord);

                    break;
            }
        }
    }
}
