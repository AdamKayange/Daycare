package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ButtonActivity extends AppCompatActivity {

    Button sendAnnounc;
    Button viewAnnounc;
    Button sendReport;
    Button ViewReport;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        sendAnnounc = (Button) findViewById(R.id.button1);
        viewAnnounc= (Button) findViewById(R.id.button2);
        sendReport = (Button) findViewById(R.id.button3);
        ViewReport = (Button) findViewById(R.id.button4);

        sendAnnounc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(),Announcement.class);
            startActivity(intent);
            }
        });
        viewAnnounc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent = new Intent(getApplicationContext(), ViewAnnouncement.class);
             startActivity(intent);
            }
        });
        sendReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           Intent intent = new Intent(getApplicationContext(),CildreportActivity.class);
           startActivity(intent);
            }
        });
        ViewReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(getApplicationContext(),BabyRport.class);
              startActivity(intent);
            }
        });

    }
}