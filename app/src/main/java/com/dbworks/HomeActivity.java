package com.dbworks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView tvUser;
    StudentEntity studentEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvUser=findViewById(R.id.tv_user);
        String username=getIntent().getStringExtra("name");
        tvUser.setText("Hi, "+username);
    }
}