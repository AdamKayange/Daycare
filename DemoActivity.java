package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DemoActivity extends AppCompatActivity {
    TextView Ingia;
    Button parent;
    Button caregiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        setContentView(R.layout.activity_main);
        Ingia = findViewById(R.id.CreateText);
        parent = findViewById(R.id.registerParent);
        caregiver = findViewById(R.id.registerCaregiver);
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
        caregiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginCaregiver.class);
                startActivity(intent);
            }
        });
    }
}