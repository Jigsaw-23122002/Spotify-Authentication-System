package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private String Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        Username = intent.getStringExtra(LoginActivity.User_Key);

        TextView User = (TextView)findViewById(R.id.User);
        User.setText("Hi " + Username);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
}