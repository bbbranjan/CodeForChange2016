package com.example.bobbyranjan.ybsandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_patient_medical_history);
        actionType = (Constants.ActionType) getIntent().getSerializableExtra(Constants.ACTION_TYPE);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        final String patientId = (String) getIntent().getSerializableExtra(Constants.PATIENT_ID);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientMedicalHistoryActivity.this, AddMedicalHistoryActivity.class);
                intent.putExtra("patientId", patientId);
                startActivity(intent);

            }
        });
        setUpAnimation();
        fragmentManager = getSupportFragmentManager();
        setupToolbar();
        manageFragments(actionType, patientId,null);

    }

    private void manageFragments(Constants.ActionType actionType, final String patientId, final String pmhId) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        final String pmhListTag = getString(R.string.patient_medical_history);
        final String vmhTag = getString(R.string.view_medical_history);

        switch (actionType) {

            case AddNewPatient:
                break;
            case PatientDetails:
                break;
            case ViewMedicalHistoryList:
                removeFragment(fragmentTransaction, vmhTag);
                PatientMedicalHistoryListFragment medicalHistoryListFragment = PatientMedicalHistoryListFragment.newInstance(patientId);
                fragmentTransaction.add(R.id.rlPatientMedicalHistory, medicalHistoryListFragment, pmhListTag);
                supportActionBar.setSubtitle(pmhListTag);
                break;
            case ViewMedicalHistory:
                if(pmhId==null){
                    break;
                }
                removeFragment(fragmentTransaction, pmhListTag);
                ViewMedicalHistoryFragment viewMedicalHistoryFragment = ViewMedicalHistoryFragment.newInstance(patientId, pmhId);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(PatientMedicalHistoryActivity.this, AddCommentActivity.class);
                        intent.putExtra("patientId", patientId);
                        intent.putExtra("historyId", pmhId);
                        startActivity(intent);

                    }
                });
                fragmentTransaction.add(R.id.rlPatientMedicalHistory, viewMedicalHistoryFragment, vmhTag);
                supportActionBar.setSubtitle(vmhTag);
                break;
            case AddNewMedicalRecord:
                break;
        }


        fragmentTransaction.commit();
    }

    private void removeFragment(FragmentTransaction fragmentTransaction, String tag) {
        Fragment fragment = fragmentManager.findFragmentByTag(tag);

        if (fragment != null) {
            fragmentTransaction.remove(fragment);
            fragmentTransaction.addToBackStack(tag);

        }
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
    public void onListFragmentInteraction(PatientMedicalHistory item, Constants.ActionType actionType) {

        manageFragments(actionType, item.getPatientId(), item.getId());
    }

    @Override
    public void onBackPressed() {

        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
