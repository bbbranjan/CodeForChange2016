package com.example.bobbyranjan.ybsandroid;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

public class AddCommentActivity extends BaseActivity implements AddDoctorCommentFragment.OnFragmentInteractionListener {

    String patientId;
    String historyId;

    public AddCommentActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_comment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpAnimation();

        setupToolbar(toolbar);
        addFragments();

    }

    private void addFragments() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        patientId = (String) getIntent().getSerializableExtra(Constants.PATIENT_ID);
        historyId = (String) getIntent().getSerializableExtra(Constants.MEDICAL_HISTORY_ID);
        int titleId = R.string.add_new_doctor_comment;
        String subtitle = getString(titleId);
        AddDoctorCommentFragment fragment = AddDoctorCommentFragment.newInstance(patientId,historyId);
        fragmentTransaction.add(R.id.rlAddNewComment, fragment, subtitle);
        supportActionBar.setSubtitle(subtitle);

        fragmentTransaction.commit();
    }





    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
