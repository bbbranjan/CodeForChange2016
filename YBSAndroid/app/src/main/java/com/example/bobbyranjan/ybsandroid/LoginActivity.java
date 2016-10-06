package com.example.bobbyranjan.ybsandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bobbyranjan.ybsandroid.models.User;
import com.example.bobbyranjan.ybsandroid.service.AsyncResultListener;
import com.example.bobbyranjan.ybsandroid.service.AsyncResultTask;
import com.example.bobbyranjan.ybsandroid.service.UserService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements AsyncResultListener {


    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private TextView mNewUser;
    private TextView mForgotPassword;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //if getCurrentUser does not returns null
        if (UserService.auth.getCurrentUser() != null) {
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
        }

        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mNewUser = (TextView) findViewById(R.id.newUser);
        mNewUser.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignUp();
            }
        });

        mForgotPassword = (TextView) findViewById(R.id.forgotPassword);
        mForgotPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResetEmail();
            }
        });

        progressDialog = new ProgressDialog(this);
    }

    private void sendResetEmail() {
        String email = mEmailView.getText().toString();
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
        String email = mEmailView.getText().toString().trim();
        String password = mPasswordView.getText().toString().trim();


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

        progressDialog.setMessage("logging in...");
        progressDialog.show();

        final AsyncResultTask retrieve_task = new AsyncResultTask(this);

        //logging in the user
        UserService.login(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if (task.isSuccessful()) {
                            UserService.getUser(UserService.getCurrentUserUUID(),retrieve_task);
                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
                        }
                    }
                });
    }

    @Override
    public void processResult(Object result) {
        User me = (User)result;
        Toast.makeText(LoginActivity.this, "Hello "+me.getName()+"!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void processResults(Object... results) {
    }
}

