package com.example.bobbyranjan.ybsandroid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bobbyranjan.ybsandroid.DoctorRecordFragment.OnListFragmentInteractionListener;
import com.example.bobbyranjan.ybsandroid.models.DoctorComments;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DoctorComments} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
class DoctorRecordViewAdapter extends RecyclerView.Adapter<DoctorRecordViewAdapter.ViewHolder> {

    private final List<DoctorComments> mValues;
    private final OnListFragmentInteractionListener mListener;

    DoctorRecordViewAdapter(List<DoctorComments> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_doctor_record_list_view_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        DoctorComments mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
        }

    }
}
