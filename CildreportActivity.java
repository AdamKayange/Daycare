package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class CildreportActivity extends AppCompatActivity {
    private EditText arrivalTimeEditText;
    private EditText leavingTimeEditText;
    private EditText dailyActivitiesEditText;
    private EditText sleepingTimeEditText;
    private Button submitButton;
    private ChildReportDBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cildreport);

        dbHelper = new ChildReportDBHelper(this);

        arrivalTimeEditText = (EditText) findViewById(R.id.arrivalTime);
        leavingTimeEditText = (EditText) findViewById(R.id.leavingTime);
        dailyActivitiesEditText = (EditText) findViewById(R.id.dailyActivities);
        sleepingTimeEditText = (EditText) findViewById(R.id.sleepingTime);
        submitButton = (Button) findViewById(R.id.Button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertChildReport();
            }
        });
    }

    private void insertChildReport() {
        String artime = arrivalTimeEditText.getText().toString().trim();
        String leavtime = leavingTimeEditText.getText().toString().trim();
        String dailyactvty = dailyActivitiesEditText.getText().toString().trim();
        String sleeptime = sleepingTimeEditText.getText().toString().trim();

        if (!artime.isEmpty() && !leavtime.isEmpty() && !dailyactvty.isEmpty() && !sleeptime.isEmpty()) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(ChildReportDBHelper.COLUMN_ARTIME, artime);
            values.put(ChildReportDBHelper.COLUMN_LEAVTIME, leavtime);
            values.put(ChildReportDBHelper.COLUMN_DAILYACTVTY, dailyactvty);
            values.put(ChildReportDBHelper.COLUMN_SLEEPTIME, sleeptime);

            long newRowId = db.insert(ChildReportDBHelper.TABLE_NAME, null, values);
            db.close();

            if (newRowId != -1) {
                // Report sent successfully
                Toast.makeText(CildreportActivity.this, "Report sent successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), HomeCareActivity.class);
                startActivity(intent);
            } else {
                // Error sending report
                Toast.makeText(CildreportActivity.this, "Report failed to send", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Fields are empty
            Toast.makeText(CildreportActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }
}
