package com.example.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class BabyRport extends Activity {

    private ChildReportDBHelper dbHelper;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_rport); // Use the correct layout file name

        // Initialize the database helper
        dbHelper = new ChildReportDBHelper(this);

        // Get a reference to the TextView elements
        TextView arrivalTimeTextViewValue = findViewById(R.id.arrivalTimeTextViewValue);
        TextView leavingTimeTextViewValue = findViewById(R.id.leavingTimeTextViewValue);
        TextView dailyActivitiesTextViewValue = findViewById(R.id.dailyActivitiesTextViewValue);
        TextView sleepingTimeTextViewValue = findViewById(R.id.sleepingTimeTextViewValue);

        // Open the database for reading
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define the columns you want to retrieve
        String[] columns = {
                ChildReportDBHelper.COLUMN_ARTIME,
                ChildReportDBHelper.COLUMN_LEAVTIME,
                ChildReportDBHelper.COLUMN_DAILYACTVTY,
                ChildReportDBHelper.COLUMN_SLEEPTIME
        };

        // Query the database to retrieve the data
        Cursor cursor = db.query(
                ChildReportDBHelper.TABLE_NAME,
                columns,
                null,  // Selection (where clause)
                null,  // Selection arguments
                null,  // Group by
                null,  // Having
                null   // Order by
        );

        // Check if there's data in the cursor
        if (cursor != null && cursor.moveToFirst()) {
            // Retrieve data from the cursor and set it in TextViews
            arrivalTimeTextViewValue.setText(cursor.getString(cursor.getColumnIndex(ChildReportDBHelper.COLUMN_ARTIME)));
            leavingTimeTextViewValue.setText(cursor.getString(cursor.getColumnIndex(ChildReportDBHelper.COLUMN_LEAVTIME)));
            dailyActivitiesTextViewValue.setText(cursor.getString(cursor.getColumnIndex(ChildReportDBHelper.COLUMN_DAILYACTVTY)));
            sleepingTimeTextViewValue.setText(cursor.getString(cursor.getColumnIndex(ChildReportDBHelper.COLUMN_SLEEPTIME)));
        }

        // Close the cursor and database
        if (cursor != null) {
            cursor.close();
        }
        db.close();
    }
}
