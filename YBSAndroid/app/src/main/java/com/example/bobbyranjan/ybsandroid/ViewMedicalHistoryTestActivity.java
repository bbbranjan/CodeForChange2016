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

public class ViewMedicalHistoryTestActivity extends AppCompatActivity implements AddDoctorCommentFragment.OnFragmentInteractionListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

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
        fragmentTransaction.add(R.id.rlAddNewPatient, new ViewMedicalHistoryFragment(), "View Medical History Fragment");
        fragmentTransaction.commit();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setLogo(R.drawable.ic_pregnant_woman);
        supportActionBar.setTitle(R.string.app_name);
        supportActionBar.setSubtitle(getString(R.string.add_new_doctor_comment));
        supportActionBar.setElevation(9);
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
