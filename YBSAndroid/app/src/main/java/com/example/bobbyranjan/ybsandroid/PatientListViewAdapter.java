package com.example.bobbyranjan.ybsandroid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bobbyranjan.ybsandroid.PatientListFragment.OnListFragmentInteractionListener;
import com.example.bobbyranjan.ybsandroid.models.Patient;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Patient} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
class PatientListViewAdapter extends RecyclerView.Adapter<PatientListViewAdapter.ViewHolder> {

    private final List<Patient> mValues;
    private final OnListFragmentInteractionListener mListener;

    PatientListViewAdapter(List<Patient> items, OnListFragmentInteractionListener listener) {
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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.profile_image) CircleImageView profileImage;
        @Bind(R.id.tvPatientName) TextView tvPatientName;
        @Bind(R.id.tvHusbandName) TextView tvHusbandName;
        @Bind(R.id.tvAge) TextView tvAge;
        @Bind(R.id.iv_view_profile) ImageView ivViewProfile;
        @Bind(R.id.iv_add_medical_record) ImageView ivAddMedicalRecord;

        Patient patient;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void setListeners() {
            ivViewProfile.setOnClickListener(ViewHolder.this);
            ivAddMedicalRecord.setOnClickListener(ViewHolder.this);
        }
        @Override
        public String toString() {
            return super.toString() + " '" + tvHusbandName.getText() + "'";
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_view_profile:
                    mListener.onListFragmentInteraction(this.patient, Constants.ActionType.PatientDetails);
                    break;

                case R.id.iv_add_medical_record:
                    mListener.onListFragmentInteraction(this.patient, Constants.ActionType.ViewMedicalHistoryList);

                    break;
            }
        }
    }
}
