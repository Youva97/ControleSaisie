package com.example.controlesaisie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    protected Button btnValid;
    protected ProgressBar progressBar;
    protected TextInputLayout login, email, password, password2;
    private String strLogin, strEmail, strPassword, strPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnValid = findViewById(R.id.btnValid);
        progressBar = findViewById(R.id.progressBar);
        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);

        btnValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validLogin();
                validEmail();
                validPassword();

                if (!validLogin() || !validEmail() || !validPassword()) {
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                btnValid.setVisibility(View.GONE);
            }
        });
    }

    private boolean validLogin() {
        strLogin = login.getEditText().getText().toString().trim().toLowerCase();

        if (strLogin.isEmpty()) {
            login.setError("Login doit être renseigné");
            login.requestFocus();
            return false;
        } else if (strLogin.length() > 10) {
            login.setError("Votre login est trop long");
            return false;
        } else {
            login.setError(null);
            return true;
        }
    }

    private boolean validEmail() {
        strEmail = email.getEditText().getText().toString().trim().toLowerCase();

        if (strEmail.isEmpty()) {
            email.setError("Login doit être renseigné");
            email.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {
            email.setError(strEmail + "n'est pas une adresse Email valide");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private boolean validPassword() {
        strPassword = password.getEditText().getText().toString().trim().toLowerCase();
        strPassword2 = password2.getEditText().getText().toString().trim().toLowerCase();

        if (strPassword.isEmpty() || !PASSWORD_PATTERN.matcher(strPassword2).matches()) {
            password.setError("Le mode de passe n'est pas valid");
            return false;
        } else if (!strPassword.equals(strPassword2)) {
            password.setError("Les mots de passe ne sont pas identiques");
            password2.setError("Les mots de passe ne sont pas identiques");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }
    private boolean validPassword2() {
        strPassword = password.getEditText().getText().toString().trim().toLowerCase();
        strPassword2 = password2.getEditText().getText().toString().trim().toLowerCase();

        if (strPassword2.isEmpty() || !PASSWORD_PATTERN.matcher(strPassword).matches()) {
            password.setError("Le mode de passe n'est pas valid");
            return false;
        } else if (!strPassword2.equals(strPassword)) {
            password.setError("Les mots de passe ne sont pas identiques");
            password2.setError("Les mots de passe ne sont pas identiques");
            return false;
        } else {
            password.setError(null);
            password2.setError(null);
            return true;
        }
    }

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +
                    "(?=.*[a-z])" +
                    "(?=.*[A-Z])" +
                    "(?=.*[@#!$%^&+=])" +
                    "{6,20}" +
                    "%" +
                    "?=\\" +
                    "S+$");


}