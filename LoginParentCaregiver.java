package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginParentCaregiver extends AppCompatActivity {
    Button registerParent;
    Button registerCaregiver;
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_parent_caregiver);
        registerParent = findViewById(R.id.registerParent);
        registerCaregiver = findViewById(R.id.registerCaregiver);
        register = findViewById(R.id.CreateText);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ParentsCaregiverActivity.class);
                startActivity(intent);
            }
        });
        registerParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
        registerCaregiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginCaregiver.class);
                startActivity(intent);
            }
        });
    }
}