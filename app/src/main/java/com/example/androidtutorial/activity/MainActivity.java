package com.example.androidtutorial.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.androidtutorial.R;
import com.example.androidtutorial.util.DataUtil;

public class MainActivity extends AppCompatActivity {

    private DataUtil dataUtil;

    private Button registerBtn, loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataUtil = DataUtil.getInstance(MainActivity.this);
        init();
    }

    private void init() {
        if (dataUtil.getCurrentUser() != null) {
            Intent intent = new Intent(MainActivity.this, BooksMenuActivity.class);
            startActivity(intent);
        }

        setVariables();

        registerBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        loginBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void setVariables() {
        registerBtn = findViewById(R.id.main_RegisterBtn);
        loginBtn = findViewById(R.id.main_LoginBtn);
    }

    @Override
    public void onBackPressed() {

    }
}