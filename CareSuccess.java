package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CareSuccess extends AppCompatActivity {
    Button loginCare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_success);
        loginCare = (Button) findViewById(R.id.buttonEnterHomepage);

        loginCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HomeCareActivity.class);
                startActivity(intent);
            }
        });

    }
}