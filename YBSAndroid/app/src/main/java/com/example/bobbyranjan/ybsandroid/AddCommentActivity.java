package com.example.bobbyranjan.ybsandroid;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;

public class AddCommentActivity extends AppCompatActivity implements AddDoctorCommentFragment.OnFragmentInteractionListener{

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Constants.ActionType actionType;
    private ActionBar supportActionBar;

    String patientId;
    String historyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_comment);
        actionType = (Constants.ActionType) getIntent().getSerializableExtra(Constants.ACTION_TYPE);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpAnimation();

        setupToolbar();
        addFragments(actionType);

    }

    private void addFragments(Constants.ActionType actionType) {

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

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setUpAnimation() {

        Explode enterTransition = new Explode();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration));
        getWindow().setEnterTransition(enterTransition);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
