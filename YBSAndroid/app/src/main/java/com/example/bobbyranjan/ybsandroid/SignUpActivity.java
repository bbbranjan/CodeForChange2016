package com.example.bobbyranjan.ybsandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SignUpActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mEmail;
    private EditText mPhone;
    private EditText mPassword;
    private EditText mCfmPassword;
    private Spinner mSpinner;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mName = (EditText) findViewById(R.id.name);
        mEmail = (EditText) findViewById(R.id.email);
        mPhone = (EditText) findViewById(R.id.phone);
        mPassword = (EditText) findViewById(R.id.password);
        mCfmPassword = (EditText) findViewById(R.id.cfm_password);
        mSpinner = (Spinner) findViewById(R.id.role);
        String role = mSpinner.getSelectedItem().toString();
        
        mButton = (Button) findViewById(R.id.register);
        
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
    }
}
