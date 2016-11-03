package com.example.bobbyranjan.ybsandroid;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bobbyranjan.ybsandroid.models.Patient;
import com.example.bobbyranjan.ybsandroid.models.User;
import com.example.bobbyranjan.ybsandroid.service.PresenceListener;
import com.example.bobbyranjan.ybsandroid.service.UserService;

public class PatientListActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, PatientListFragment.OnListFragmentInteractionListener, PresenceListener {

    TextView navEmail;
    TextView navUser;
    boolean connected = false;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindControls();
        setupWindowAnimations();
        setupToolbar();
        setupDrawer();
        hookEvents();
        addFragments();
        UserService.listenPresence(this);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setSubtitle(R.string.list_of_pregnant_women);
    }


    private void bindControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    private void setupWindowAnimations() {
        setUpAnimation();

    }


    private void addFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.rlContent, new PatientListFragment(), "Patient List Fragment");
        fragmentTransaction.commit();
    }

    private void setupDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.navEmailId);
        navUser = (TextView) navigationView.getHeaderView(0).findViewById(R.id.navUserName);
        navEmail.setText(UserService.getCurrentUserEmail());
        UserService.getUser(UserService.getCurrentUserUUID(), this::processResult);
    }

    private void hookEvents() {
        fab.setOnClickListener(view -> onListFragmentInteraction(null, Constants.ActionType.AddNewPatient));
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onListFragmentInteraction(Patient patient, Constants.ActionType fragmentType) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PatientListActivity.this);
        Intent i = null;

        switch (fragmentType) {

            case AddNewPatient:
                i = new Intent(PatientListActivity.this, AddPatientActivity.class);
                i.putExtra(Constants.ACTION_TYPE, Constants.ActionType.AddNewPatient);
                break;
            case PatientDetails:
                i = new Intent(PatientListActivity.this, ViewPatientActivity.class);
                i.putExtra(Constants.ACTION_TYPE, Constants.ActionType.PatientDetails);
                i.putExtra(Constants.PATIENT_ID, patient.getId());
                break;
            case ViewMedicalHistoryList:
                i = new Intent(PatientListActivity.this, PatientMedicalHistoryActivity.class);
                i.putExtra(Constants.ACTION_TYPE, Constants.ActionType.ViewMedicalHistoryList);
                i.putExtra(Constants.PATIENT_ID, patient.getId());
                break;
        }
        startActivity(i, options.toBundle());

    }

    @Override
    public void connected() {
        connected = true;
    }

    @Override
    public void disconnected() {
        connected = false;
        //Toast.makeText(getApplicationContext(), "Disconnected from internet!", Toast.LENGTH_LONG).show();
    }

    public void processResult(User result) {
        if (result != null) {
            navUser.setText(result.getName());
        }

    }


}
