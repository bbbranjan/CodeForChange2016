package com.example.bobbyranjan.ybsandroid;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

public class AddMedicalHistoryActivity extends BaseActivity implements AddMedicalHistoryFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_medical_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpAnimation();

        setupToolbar(toolbar);
        addFragments();

    }

    private void addFragments() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        String patientId = (String) getIntent().getSerializableExtra("patientId");
        int titleId;
        titleId = R.string.add_new_medical_history;
        AddMedicalHistoryFragment fragment = new AddMedicalHistoryFragment();
        Bundle args = new Bundle();
        args.putString("patientId", patientId);
        fragment.setArguments(args);
        fragmentTransaction.add(R.id.rlAddMedicalHistory, fragment, "Add New Medical History");
        supportActionBar.setSubtitle(getString(titleId));

        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
