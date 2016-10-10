package com.example.bobbyranjan.ybsandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;

import com.example.bobbyranjan.ybsandroid.models.PatientMedicalHistory;

public class PatientMedicalHistoryActivity extends AppCompatActivity implements PatientMedicalHistoryListFragment.OnListFragmentInteractionListener{

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Constants.ActionType actionType;
    private ActionBar supportActionBar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_patient_medical_history);
        actionType = (Constants.ActionType) getIntent().getSerializableExtra(Constants.ACTION_TYPE);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PatientMedicalHistoryActivity.this,AddMedicalHistoryActivity.class));

            }
        });
        setUpAnimation();

        setupToolbar();
        addFragments(actionType);

    }

    private void addFragments(Constants.ActionType actionType) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        String patientId = (String) getIntent().getSerializableExtra(Constants.PATIENT_ID);
        String subtitle = getString(R.string.patient_medical_history);
        fragmentTransaction.add(R.id.rlPatientMedicalHistory, new PatientMedicalHistoryListFragment(), subtitle);
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
    public void onListFragmentInteraction(PatientMedicalHistory item) {

    }
}
