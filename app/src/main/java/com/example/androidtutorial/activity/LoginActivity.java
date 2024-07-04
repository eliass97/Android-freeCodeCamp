package com.example.androidtutorial.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.androidtutorial.R;
import com.example.androidtutorial.model.User;
import com.example.androidtutorial.util.DataUtil;

public class LoginActivity extends AppCompatActivity {

    private DataUtil dataUtil;

    private TextView messageTextView;
    private EditText emailEditText, passwordEditText;
    private Button loginBtn;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dataUtil = DataUtil.getInstance(LoginActivity.this);
        init();
    }

    private void init() {
        setVariables();

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) supportActionBar.setDisplayHomeAsUpEnabled(true);

        loginBtn.setOnClickListener(view -> {
            clearWarnings();
            if (!validate()) return;

            Thread thread = new Thread(() -> {
                User user = buildUser();
                boolean loggedIn = dataUtil.login(user, progressBar);

                if (loggedIn) {
                    runOnUiThread(() -> {
                        Intent intent = new Intent(LoginActivity.this, BooksMenuActivity.class);
                        startActivity(intent);
                    });
                } else {
                    runOnUiThread(() -> messageTextView.setVisibility(View.VISIBLE));
                }
            });

            thread.start();
        });
    }

    private void setVariables() {
        messageTextView = findViewById(R.id.login_WarningTextView);

        emailEditText = findViewById(R.id.login_EmailTextView);
        passwordEditText = findViewById(R.id.login_PasswordTextView);

        loginBtn = findViewById(R.id.login_LoginBtn);

        progressBar = findViewById(R.id.login_ProgressBar);
    }

    private boolean validate() {
        if (emailEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()) {
            messageTextView.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }

    private User buildUser() {
        User user = new User();
        user.setEmail(emailEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());
        return user;
    }

    private void clearWarnings() {
        messageTextView.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}