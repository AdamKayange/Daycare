package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewAnnouncement extends AppCompatActivity {

    private TextView titleTextView;
    private TextView contentTextView;
    Button homepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_announcement);

        titleTextView = findViewById(R.id.titleTextView);
        contentTextView = findViewById(R.id.contentTextView);
        homepage = findViewById(R.id.backButton);

        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
            }
        });
        // Retrieve and display the announcement
        displayAnnouncement();
    }

    private void displayAnnouncement() {
        // Initialize the database helper
        AnnouncementDBHelper dbHelper = new AnnouncementDBHelper(this);

        // Open the database for reading
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define the columns you want to retrieve
        String[] columns = {
                AnnouncementDBHelper.COLUMN_TITLE,
                AnnouncementDBHelper.COLUMN_CONTENT
        };

        // Query the database to retrieve the latest announcement (you can modify this query as needed)
        Cursor cursor = db.query(
                AnnouncementDBHelper.TABLE_NAME,
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
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(AnnouncementDBHelper.COLUMN_TITLE));
            @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex(AnnouncementDBHelper.COLUMN_CONTENT));

            titleTextView.setText(title);
            contentTextView.setText(content);
        } else {
            // Handle the case where there are no announcements to display
            titleTextView.setText("No Announcement");
            contentTextView.setText("There are currently no announcements.");
        }

        // Close the cursor and database
        if (cursor != null) {
            cursor.close();
        }
        db.close();
    }
}
