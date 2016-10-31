package com.example.bobbyranjan.ybsandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bobbyranjan.ybsandroid.service.UserService;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity {
    @Bind(R.id.spRole) Spinner spRole;
    @Bind(R.id.ilName) TextInputLayout ilName;
    @Bind(R.id.etName) EditText etName;
    @Bind(R.id.ilEmail) TextInputLayout ilEmail;
    @Bind(R.id.etEmail) EditText etEmail;
    @Bind(R.id.ilPhone) TextInputLayout ilPhone;
    @Bind(R.id.etPhone) EditText etPhone;
    @Bind(R.id.ilPassword) TextInputLayout ilPassword;
    @Bind(R.id.etPassword) EditText etPassword;
    @Bind(R.id.ilConfirmPassword) TextInputLayout ilConfirmPassword;
    @Bind(R.id.etConfirmPassword) EditText etConfirmPassword;

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
        String name = etName.getText().toString();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String cfm_password = etConfirmPassword.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        //checking if email and passwords are empty
        if (!validateData(name, email, password, cfm_password)) return;
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
                        Toast.makeText(SignUpActivity.this, R.string.msg_register_success, Toast.LENGTH_LONG).show();
                        Object selectedRole = spRole.getSelectedItem();
                        String role = selectedRole.toString();
                        UserService.persistUser(id, name, email, phone, role);
                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    } else {
                        //display some message here
                        Toast.makeText(SignUpActivity.this, R.string.error_registration, Toast.LENGTH_LONG).show();
                    }
                    this.dismissProgressDialog();
                });
    }

    private boolean validateData(String name, String email, String password, String cfm_password) {
        boolean isValidationSuccess = true;
        View selectedView = spRole.getSelectedView();
        if (selectedView != null && selectedView instanceof TextView) {
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError(getString(R.string.error_select_role));
            isValidationSuccess = false;
        }
        if (TextUtils.isEmpty(name)) {
            ilName.setError(getString(R.string.error_blank_name));
            isValidationSuccess = false;
        } else {
            ilName.setErrorEnabled(false);
        }
        if (TextUtils.isEmpty(email)) {
            ilEmail.setError(getString(R.string.error_email_msg));
            isValidationSuccess = false;
        } else if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ilEmail.setError(getString(R.string.error_invalid_email));
            isValidationSuccess = false;
        } else {
            ilEmail.setErrorEnabled(false);
        }
        if (TextUtils.isEmpty(password)) {
            ilPassword.setError(getString(R.string.error_password_msg));
            isValidationSuccess = false;
        } else {
            ilPassword.setErrorEnabled(false);
        }
        if (!TextUtils.equals(password, cfm_password)) {
            ilConfirmPassword.setError(getString(R.string.error_passwords_mismatch));
            isValidationSuccess = false;
        } else {
            ilConfirmPassword.setErrorEnabled(false);
        }
        return isValidationSuccess;
    }
}
