package com.example.bobbyranjan.ybsandroid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bobbyranjan.ybsandroid.PatientMedicalHistoryListFragment.OnListFragmentInteractionListener;
import com.example.bobbyranjan.ybsandroid.models.PatientMedicalHistory;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PatientMedicalHistory} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PatientMedicalHistoryListViewAdapter extends RecyclerView.Adapter<PatientMedicalHistoryListViewAdapter.ViewHolder> {

    private final List<PatientMedicalHistory> mValues;
    private final OnListFragmentInteractionListener mListener;

    public PatientMedicalHistoryListViewAdapter(List<PatientMedicalHistory> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_patient_medical_history_list_view_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        PatientMedicalHistory pmh = mValues.get(position);
        holder.patientMedicalHistory = pmh;
        holder.tvHPHT.setText(pmh.getHpht());
        holder.tvTP.setText(pmh.getTp());
        holder.tvTotalNumberOfVisits.setText(pmh.getVisit());
        holder.tvTD.setText(pmh.getTd());
        holder.tvLila.setText(pmh.getLila());
        holder.tvUK.setText(pmh.getUk());
        holder.tvBBTB.setText(pmh.getBb_tb());


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.patientMedicalHistory, Constants.ActionType.ViewMedicalHistory);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public PatientMedicalHistory patientMedicalHistory;

        @Bind(R.id.tvHPHT) TextView tvHPHT;
        @Bind(R.id.tvTP) TextView tvTP;
        @Bind(R.id.tvTotalNumberOfVisits) TextView tvTotalNumberOfVisits;
        @Bind(R.id.tvTD) TextView tvTD;
        @Bind(R.id.tvLila) TextView tvLila;
        @Bind(R.id.tvUK) TextView tvUK;
        @Bind(R.id.tvBBTB) TextView tvBBTB;

        ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }


    }
}
