package com.example.bobbyranjan.ybsandroid;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

public class AddPatientActivity extends BaseActivity implements AddNewPatientFragment.OnFragmentInteractionListener

{

    private Toolbar toolbar;
    private ActionBar supportActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_patient);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpAnimation();

        setupToolbar();
        addFragments();

    }

    private void addFragments() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        int titleId = R.string.add_new_pregnant_woman;
        String subtitle = getString(titleId);
        fragmentTransaction.add(R.id.rlAddNewPatient, new AddNewPatientFragment(), subtitle);
        supportActionBar.setSubtitle(subtitle);

        fragmentTransaction.commit();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
