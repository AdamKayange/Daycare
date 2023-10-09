package com.example.demo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CaregiverActivity extends AppCompatActivity {
    EditText fullnameEditText;
    EditText usernameEditText;
    EditText passwordEditText;
    Button registerButton;
    TextView loginTextView;
    CaregiverDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver);

        dbHelper = new CaregiverDBHelper(this);
        fullnameEditText = findViewById(R.id.Fullname);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.carepassword);
        registerButton = findViewById(R.id.button);
        loginTextView = findViewById(R.id.createText);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered caregiver information
                String fullname = fullnameEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Insert caregiver data into the database
                long result = insertCaregiver(fullname, username, password);

                if (result != -1) {
                    // Registration successful, show a success message
                    Toast.makeText(CaregiverActivity.this, "Caregiver registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginCaregiver.class);
                    startActivity(intent);
                } else {
                    // Registration failed, show an error message
                    Toast.makeText(CaregiverActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the login activity
                finish(); // Finish the current activity
            }
        });
    }

    private long insertCaregiver(String fullname, String username, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CaregiverDBHelper.COLUMN_FULLNAME, fullname);
        values.put(CaregiverDBHelper.COLUMN_USERNAME, username);
        values.put(CaregiverDBHelper.COLUMN_PASSWORD, password);

        long newRowId = db.insert(CaregiverDBHelper.TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }
}
