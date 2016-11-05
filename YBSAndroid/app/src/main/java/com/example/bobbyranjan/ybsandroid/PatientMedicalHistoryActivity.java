package com.example.bobbyranjan.ybsandroid;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bobbyranjan.ybsandroid.models.PatientMedicalHistory;

public class PatientMedicalHistoryActivity extends BaseActivity implements PatientMedicalHistoryListFragment.OnListFragmentInteractionListener, ViewMedicalHistoryFragment.OnFragmentInteractionListener, SearchView.OnQueryTextListener {
    SearchView searchView;
    MenuItem searchMenuItem;
    private Toolbar toolbar;
    private ActionBar supportActionBar;
    private FloatingActionButton fab;
    private FragmentManager fragmentManager;
    private ViewMedicalHistoryFragment viewMedicalHistoryFragment;
    private PatientMedicalHistoryListFragment medicalHistoryListFragment;
    private String patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_medical_history);
        Constants.ActionType actionType = (Constants.ActionType) getIntent().getSerializableExtra(Constants.ACTION_TYPE);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        patientId = (String) getIntent().getSerializableExtra(Constants.PATIENT_ID);
        final String pmhId = (String) getIntent().getSerializableExtra(Constants.MEDICAL_HISTORY_ID);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        setUpAnimation();
        fragmentManager = getSupportFragmentManager();
        setupToolbar();
        manageFragments(actionType == null ? Constants.ActionType.ViewMedicalHistoryList : actionType, patientId, pmhId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        searchMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
        return true;
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
                fab.setOnClickListener(view -> {
                    Intent intent = new Intent(PatientMedicalHistoryActivity.this, AddMedicalHistoryActivity.class);
                    intent.putExtra("patientId", patientId);
                    startActivity(intent);
                });
                medicalHistoryListFragment = PatientMedicalHistoryListFragment.newInstance(patientId);
                fragmentTransaction.replace(R.id.rlPatientMedicalHistory, medicalHistoryListFragment, pmhListTag);
                fragmentTransaction.addToBackStack(pmhListTag);
                supportActionBar.setSubtitle(pmhListTag);
                break;
            case ViewMedicalHistory:
                if (pmhId == null) {
                    break;
                }
                removeFragment(fragmentTransaction, pmhListTag);
                viewMedicalHistoryFragment = ViewMedicalHistoryFragment.newInstance(patientId, pmhId);
                fab.setOnClickListener(view -> {
                    Intent intent = new Intent(PatientMedicalHistoryActivity.this, AddCommentActivity.class);
                    intent.putExtra(Constants.PATIENT_ID, patientId);
                    intent.putExtra(Constants.MEDICAL_HISTORY_ID, pmhId);
                    startActivity(intent);
                });
                fragmentTransaction.replace(R.id.rlPatientMedicalHistory, viewMedicalHistoryFragment, vmhTag);
                fragmentTransaction.addToBackStack(vmhTag);
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
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
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

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String searchPattern) {
        medicalHistoryListFragment.search(patientId, searchPattern);
        return true;
    }
}
