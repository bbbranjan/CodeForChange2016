package com.example.bobbyranjan.ybsandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.example.bobbyranjan.ybsandroid.models.Model;
import com.example.bobbyranjan.ybsandroid.service.PatientService;
import com.example.bobbyranjan.ybsandroid.service.PatientUserMappingService;
import com.example.bobbyranjan.ybsandroid.service.UserService;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction etents.
 * Use the {@link AddNewPatientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNewPatientFragment extends Fragment {
    private static final int REQUEST_IMAGE_CAPTURE = 111;
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
    @Bind(R.id.civPatientPhoto) CircleImageView civPatientPhoto;
    @Bind(R.id.ivTakePhoto) ImageView ivTakePhoto;
    private View view;
    private CalendarDatePickerDialogFragment.OnDateSetListener datePickerListener = new CalendarDatePickerDialogFragment.OnDateSetListener() {
        @Override
        public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
            LocalDate dob = new LocalDate(year, monthOfYear + 1, dayOfMonth);
            LocalDate now = LocalDate.now();
            Years age = Years.yearsBetween(dob, now);
            etAge.setText(String.valueOf(age.getYears()));
            etDateOfBirth.setText(String.format("%s-%02d-%02d", year, monthOfYear + 1, dayOfMonth));
        }
    };
    private String imageEncoded;

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
            return true;
        });
        etDateOfBirth.setOnFocusChangeListener((dobView, b) -> {
            if (dobView.isFocused()) {
                showDatePicker();
            }
        });
        return view;
    }

    @OnClick(R.id.etDateOfBirth)
    public void showDatePicker() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int monthOfYear = now.get(Calendar.MONTH);
        int dayOfMonth = now.get(Calendar.DAY_OF_MONTH);
        if (etDateOfBirth.getText() != null) {
            String dob = etDateOfBirth.getText().toString();
            String[] dateParts = dob.split("-");
            if (dateParts.length == 3) {
                try {
                    year = Integer.valueOf(dateParts[0]);
                    monthOfYear = Integer.valueOf(dateParts[1]);
                    dayOfMonth = Integer.valueOf(dateParts[2]);
                } catch (NumberFormatException e) {
                    if (BuildConfig.DEBUG) Log.d("AddNewPatientFragment", "e:" + e);
                }
            }
        }
        CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                .setOnDateSetListener(datePickerListener)
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setPreselectedDate(year, monthOfYear, dayOfMonth);
        cdp.show(getFragmentManager(), "Select Date Of Birth");
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
            PatientService.persistPatient(id, name, husband, village, age, dob, phone, imageEncoded, 1, false, false);
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

    @OnClick(R.id.ivTakePhoto)
    public void onTakePhotoClick() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            civPatientPhoto.setImageBitmap(imageBitmap);
            encodeBitmap(imageBitmap);
        }
    }

    public void encodeBitmap(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
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
