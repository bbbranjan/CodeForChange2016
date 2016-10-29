package com.example.bobbyranjan.ybsandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bobbyranjan.ybsandroid.models.User;
import com.example.bobbyranjan.ybsandroid.service.UserService;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {


    // UI references.
    @Bind(R.id.tlEmail) TextInputLayout tlEmail;
    @Bind(R.id.evEmail) EditText evEmail;
    @Bind(R.id.tlPassword) TextInputLayout tlPassword;
    @Bind(R.id.evPassword) EditText evPassword;
    @Bind(R.id.newUser) TextView newUser;
    @Bind(R.id.forgotPassword) TextView forgotPassword;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        //if getCurrentUser does not returns null
        if (UserService.auth.getCurrentUser() != null) {
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
        }

        newUser.setOnClickListener(v -> showSignUp());

        forgotPassword.setOnClickListener(v -> sendResetEmail());

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @OnClick(R.id.email_sign_in_button)
    void onEmailSignInButtonClick() {
        attemptLogin();
    }

    private void sendResetEmail() {
        String email = evEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_LONG).show();
        } else {
            UserService.passwordReset(email);
            Toast.makeText(this, "Password reset email sent", Toast.LENGTH_LONG).show();
        }
    }

    private void showSignUp() {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    private void attemptLogin() {
        String email = evEmail.getText().toString().trim();
        String password = evPassword.getText().toString().trim();


        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        this.showProgressDialog(null, "logging in...", false);

        //logging in the user
        UserService.login(email, password)
                .addOnCompleteListener(this, task -> {
                    this.dismissProgressDialog();
                    //if the task is successful
                    if (task.isSuccessful()) {
                        UserService.getUser(UserService.getCurrentUserUUID(), this::processResult);
                        //start the profile activity
                    } else {
                        Toast.makeText(this, R.string.error_logon_failed, Toast.LENGTH_LONG).show();
                    }


                });
    }

    public void processResult(User result) {
        Toast.makeText(LoginActivity.this, "Hello " + result.getName() + "!", Toast.LENGTH_LONG).show();
        finish();
        Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
        intent.putExtra("name", result.getName());
        startActivity(intent);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Login Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}

