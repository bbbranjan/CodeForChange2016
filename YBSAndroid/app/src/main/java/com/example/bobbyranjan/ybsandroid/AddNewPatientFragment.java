package com.example.bobbyranjan.ybsandroid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.bobbyranjan.ybsandroid.models.Model;
import com.example.bobbyranjan.ybsandroid.service.PatientService;
import com.example.bobbyranjan.ybsandroid.service.PatientUserMappingService;
import com.example.bobbyranjan.ybsandroid.service.UserService;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction etents.
 * Use the {@link AddNewPatientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNewPatientFragment extends Fragment {
    @Bind(R.id.llHeader) LinearLayout llHeader;
    @Bind(R.id.etPatientName) EditText etPatientName;
    @Bind(R.id.ilPatientName) TextInputLayout ilPatientName;
    @Bind(R.id.etVillage) EditText etVillage;
    @Bind(R.id.ilVillage) TextInputLayout ilVillage;
    @Bind(R.id.etHusbandName) EditText etHusbandName;
    @Bind(R.id.ilHusbandName) TextInputLayout ilHusbandName;
    @Bind(R.id.etDateOfBirth) EditText etDateOfBirth;
    @Bind(R.id.ilDateOfBirth) TextInputLayout ilDateOfBirth;
    @Bind(R.id.etAge) EditText etAge;
    @Bind(R.id.ilAge) TextInputLayout ilAge;
    @Bind(R.id.etPhone) EditText etPhone;
    @Bind(R.id.ilPhone) TextInputLayout ilPhone;
    private View view;
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
            etDateOfBirth.setText(String.format("%s-%s-%s", year, monthOfYear, dayOfMonth));
        }
    };

    public AddNewPatientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddNewPatientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNewPatientFragment newInstance() {
        AddNewPatientFragment fragment = new AddNewPatientFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_new_patient, container, false);
        ButterKnife.bind(this, view);
        etDateOfBirth.setInputType(InputType.TYPE_NULL);
        etDateOfBirth.setRawInputType(InputType.TYPE_CLASS_TEXT);
        etDateOfBirth.setOnTouchListener((v, event) -> {
            v.onTouchEvent(event);   // handle the event first
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);  // hide the soft keyboard
            }
            Calendar cal = Calendar.getInstance(TimeZone.getDefault()); // Get current date
            // Create the DatePickerDialog instance
            DatePickerDialog datePicker = DatePickerDialog.newInstance(datePickerListener, cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH));
            datePicker.setCancelable(true);
            datePicker.setTitle("Select Date Of Birth");
            datePicker.show(this.getActivity().getFragmentManager(), "Select Date Of Birth");
            return true;
        });
        return view;
    }

    @OnClick(R.id.btnSave)
    void savePatient() {
        String name = etPatientName.getText().toString();
        String husband = etHusbandName.getText().toString();
        String village = etVillage.getText().toString();
        String etAgeText = etAge.getText().toString();
        String dob = etDateOfBirth.getText().toString();
        String phone = etPhone.getText().toString();
        if (validateData(name, etAgeText, dob)) {
            String id = PatientService.getKey(Model.PATIENT);
            int age = Integer.valueOf(etAgeText);
            PatientService.persistPatient(id, name, husband, village, age, dob, phone, 1, false, false);
            String user = UserService.getCurrentUserUUID();
            PatientUserMappingService.persistPatientUserMapping(user, id);
            startActivity(new Intent(view.getContext(), PatientListActivity.class));
        }
    }

    private boolean validateData(String name, String age, String dob) {
        boolean isValidationSuccess = true;
        if (TextUtils.isEmpty(name)) {
            ilPatientName.setError(getString(R.string.error_patient_name));
            isValidationSuccess = false;
        } else {
            ilPatientName.setErrorEnabled(false);
        }
        if (TextUtils.isEmpty(age)) {
            ilAge.setError(getString(R.string.error_age));
            isValidationSuccess = false;
        } else {
            ilAge.setErrorEnabled(false);
        }
        if (TextUtils.isEmpty(dob)) {
            ilDateOfBirth.setError(getString(R.string.error_dob));
            isValidationSuccess = false;
        } else {
            ilDateOfBirth.setErrorEnabled(false);
        }
        return isValidationSuccess;
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://deteloper.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
