package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Announcement extends AppCompatActivity {
    private EditText titleEditText;
    private EditText contentEditText;
    private Button sendButton;
    private Button backButton;
    private AnnouncementDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        dbHelper = new AnnouncementDBHelper(this);

        titleEditText = findViewById(R.id.titleEditText);
        contentEditText = findViewById(R.id.contentEditText);
        sendButton = findViewById(R.id.sendButton);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HomeCareActivity.class);
                startActivity(intent);
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAnnouncement();
            }
        });
    }

    private void sendAnnouncement() {
        String title = titleEditText.getText().toString().trim();
        String content = contentEditText.getText().toString().trim();

        if (!title.isEmpty() && !content.isEmpty()) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(AnnouncementDBHelper.COLUMN_TITLE, title);
            values.put(AnnouncementDBHelper.COLUMN_CONTENT, content);

            long newRowId = db.insert(AnnouncementDBHelper.TABLE_NAME, null, values);

            if (newRowId != -1) {
                Toast.makeText(this, "Announcement sent successfully", Toast.LENGTH_SHORT).show();
                titleEditText.setText("");
                contentEditText.setText("");
            } else {
                Toast.makeText(this, "Error sending announcement", Toast.LENGTH_SHORT).show();
            }

            db.close();
        } else {
            Toast.makeText(this, "Please fill in both title and content", Toast.LENGTH_SHORT).show();
        }
    }
}
