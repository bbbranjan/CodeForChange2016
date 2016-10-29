package com.example.bobbyranjan.ybsandroid;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bobbyranjan.ybsandroid.service.UserService;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity {

    @Bind(R.id.spRole) Spinner spRole;
    @Bind(R.id.evName) EditText evName;
    @Bind(R.id.evEmail) EditText evEmail;
    @Bind(R.id.evPhone) EditText evPhone;
    @Bind(R.id.evPassword) EditText evPassword;
    @Bind(R.id.evConfirmPassword) EditText evConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.roles_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spRole.setAdapter(adapter);

    }


    @OnClick(R.id.btnRegister)
    void onRegisterClick() {
        register();
    }
    private void register() {
        //getting email and password from edit texts
        String email = evEmail.getText().toString().trim();
        String password = evPassword.getText().toString().trim();
        String cfm_password = evConfirmPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        if (!TextUtils.equals(password, cfm_password)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        this.showProgressDialog(null, "Registering please wait...", false);

        //creating a new user
        UserService.registerUser(email, password)
                .addOnCompleteListener(this, task -> {
                    //checking if success
                    if (task.isSuccessful()) {
                        String id = task.getResult().getUser().getUid();
                        //display some message here
                        Toast.makeText(SignUpActivity.this, "Successfully registered", Toast.LENGTH_LONG).show();
                        String name = evName.getText().toString();
                        String email1 = evEmail.getText().toString();
                        String phone = evPhone.getText().toString();
                        String role = spRole.getSelectedItem().toString();
                        UserService.persistUser(id, name, email1, phone, role);
                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    } else {
                        //display some message here
                        Toast.makeText(SignUpActivity.this, "Registration Error", Toast.LENGTH_LONG).show();
                    }
                    this.dismissProgressDialog();
                });
    }
}
