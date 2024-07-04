package com.example.androidtutorial.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.androidtutorial.R;
import com.example.androidtutorial.model.User;
import com.example.androidtutorial.util.DataUtil;

public class RegisterActivity extends AppCompatActivity {

    private DataUtil dataUtil;

    private TextView messageTextView, warningName, warningEmail, warningPassword, warningRepeatPassword, warningTerms;
    private EditText editTextName, editTextEmail, editTextPassword, editTextRepeatPassword;
    private RadioGroup rgGender;
    private Spinner spinnerCountry;
    private CheckBox checkboxTerms;
    private Button imageButton, registerButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dataUtil = DataUtil.getInstance(RegisterActivity.this);
        init();
    }

    private void init() {
        setVariables();

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) supportActionBar.setDisplayHomeAsUpEnabled(true);

        imageButton.setOnClickListener(view -> Toast.makeText(RegisterActivity.this, "Not implemented yet", Toast.LENGTH_SHORT).show());

        registerButton.setOnClickListener(view -> {
            clearWarnings();

            if (!validate()) return;

            Thread thread = new Thread(() -> {
                User user = buildUser();
                dataUtil.register(user, progressBar);

                runOnUiThread(() -> {
                    String name = editTextName.getText().toString();
                    String message = getString(R.string.application_submitted, name);
                    messageTextView.setText(message);
                });
            });

            thread.start();
        });
    }

    private void setVariables() {
        messageTextView = findViewById(R.id.register_MessageTextView);

        warningName = findViewById(R.id.register_WarningNameTextView);
        warningEmail = findViewById(R.id.register_WarningEmailTextView);
        warningPassword = findViewById(R.id.register_WarningPasswordTextView);
        warningRepeatPassword = findViewById(R.id.register_WarningRepeatPasswordTextView);
        warningTerms = findViewById(R.id.register_WarningTermsTextView);

        editTextName = findViewById(R.id.register_NameEditText);
        editTextEmail = findViewById(R.id.register_EmailEditText);
        editTextPassword = findViewById(R.id.register_PasswordEditText);
        editTextRepeatPassword = findViewById(R.id.register_RepeatPasswordEditText);

        rgGender = findViewById(R.id.register_GenderRadioGroup);

        spinnerCountry = findViewById(R.id.register_CountrySpinner);

        checkboxTerms = findViewById(R.id.register_TermsCheckbox);

        registerButton = findViewById(R.id.register_RegisterBtn);
        imageButton = findViewById(R.id.register_ImageBtn);

        progressBar = findViewById(R.id.register_Progress);
    }

    private void clearWarnings() {
        warningName.setVisibility(View.GONE);
        warningEmail.setVisibility(View.GONE);
        warningPassword.setVisibility(View.GONE);
        warningRepeatPassword.setVisibility(View.GONE);
        warningTerms.setVisibility(View.GONE);
    }

    private boolean validate() {
        boolean checksPassed = true;

        if (editTextName.getText().toString().isEmpty()) {
            warningName.setVisibility(View.VISIBLE);
            checksPassed = false;
        }

        if (editTextEmail.getText().toString().isEmpty()) {
            warningEmail.setVisibility(View.VISIBLE);
            checksPassed = false;
        }

        if (editTextPassword.getText().toString().isEmpty()) {
            warningPassword.setVisibility(View.VISIBLE);
            checksPassed = false;
        }

        if (editTextRepeatPassword.getText().toString().isEmpty()
                || !editTextPassword.getText().toString().equals(editTextRepeatPassword.getText().toString())) {
            warningRepeatPassword.setVisibility(View.VISIBLE);
            checksPassed = false;
        }

        if (!checkboxTerms.isChecked()) {
            warningTerms.setVisibility(View.VISIBLE);
            checksPassed = false;
        }

        return checksPassed;
    }

    private User buildUser() {
        RadioButton genderRadioButton = findViewById(rgGender.getCheckedRadioButtonId());
        String gender = genderRadioButton != null
                ? genderRadioButton.getText().toString()
                : "";

        return new User(
                editTextName.getText().toString(),
                editTextEmail.getText().toString(),
                editTextPassword.getText().toString(),
                gender,
                spinnerCountry.getSelectedItem().toString()
        );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}