package com.example.book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    TextInputLayout loginEmail, loginEnrollment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        loginEmail = findViewById(R.id.loginEmail);
        loginEnrollment = findViewById(R.id.loginEnrollment);
    }

    private Boolean validateEmail() {
        String emailId = loginEmail.getEditText().getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (emailId.isEmpty()) {
            loginEmail.setError("Field cannot be empty");
            return false;
        } else if (!emailId.matches(emailPattern)) {
            loginEnrollment.setError("Invalid Email Address!");
            return false;
        } else {
//            Removes the error
            loginEmail.setError(null);
//            removes the space of the error
            loginEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateNumber() {
        String number = loginEnrollment.getEditText().getText().toString().trim();
        String noWhiteSpace = "(?=\\s+$)";

        if (number.isEmpty()) {
            loginEnrollment.setError("Field cannot be empty");
            return false;
        } else if (number.length() > 6) {
            loginEnrollment.setError("Enrollment number should only be 6-digit long");
            return false;
        } else if (number.length() < 6) {
            loginEnrollment.setError("Enrollment number should only be 6-digit long");
            return false;
        } else if (!number.matches(noWhiteSpace)) {
            loginEnrollment.setError("Whitespaces are not allowed");
            return false;
        } else {
            loginEnrollment.setError(null);
            loginEnrollment.setErrorEnabled(false);
            return true;
        }
    }

    public void permitUser(View view) {
        if (!validateEmail() | !validateNumber()) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }

}