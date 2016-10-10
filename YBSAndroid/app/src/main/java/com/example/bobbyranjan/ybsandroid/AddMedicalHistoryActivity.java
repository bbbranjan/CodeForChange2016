package com.example.bobbyranjan.ybsandroid;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;

public class AddMedicalHistoryActivity extends AppCompatActivity implements AddMedicalHistoryFragment.OnFragmentInteractionListener{

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Constants.ActionType actionType;
    private ActionBar supportActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_medical_history);
        actionType = (Constants.ActionType) getIntent().getSerializableExtra(Constants.ACTION_TYPE);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpAnimation();

        setupToolbar();
        addFragments(actionType);

    }

    private void addFragments(Constants.ActionType actionType) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        String patientId = (String) getIntent().getSerializableExtra("patientId");
        int titleId = 0;
        titleId = R.string.add_new_medical_history;
        AddMedicalHistoryFragment fragment = new AddMedicalHistoryFragment();
        Bundle args = new Bundle();
        args.putString("patientId", patientId);
        fragment.setArguments(args);
        fragmentTransaction.add(R.id.rlAddMedicalHistory, fragment, "Add New Medical History");
        supportActionBar.setSubtitle(getString(titleId));

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
