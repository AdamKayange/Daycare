package com.example.demo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginCaregiver extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    CaregiverDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_caregiver);

        dbHelper = new CaregiverDBHelper(this);

        usernameEditText = findViewById(R.id.usnm); // Change the ID to match your XML layout
        passwordEditText = findViewById(R.id.password); // Change the ID to match your XML layout
        loginButton = findViewById(R.id.registerBtn); // Change the ID to match your XML layout

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the entered username and password
                String enteredUsername = usernameEditText.getText().toString().trim();
                String enteredPassword = passwordEditText.getText().toString().trim();

                // Check if the entered credentials are valid
                if (isValidCredentials(enteredUsername, enteredPassword)) {
                    // Credentials are valid, navigate to the next activity
                    Toast.makeText(LoginCaregiver.this,"Login is successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginCaregiver.this, CareSuccess.class); // Replace NextActivity with your target activity
                    startActivity(intent);
                    finish(); // Finish the current activity to prevent going back to the login screen
                } else {
                    // Invalid credentials, show an error message
                    Toast.makeText(LoginCaregiver.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidCredentials(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define the columns you want to retrieve
        String[] projection = {
                CaregiverDBHelper.COLUMN_USERNAME,
                CaregiverDBHelper.COLUMN_PASSWORD
        };

        // Define a selection criteria
        String selection = CaregiverDBHelper.COLUMN_USERNAME + " = ? AND " +
                CaregiverDBHelper.COLUMN_PASSWORD + " = ?";

        // Define the selection arguments
        String[] selectionArgs = {username, password};

        // Query the database to check if the username and password match
        Cursor cursor = db.query(
                CaregiverDBHelper.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        // Check if a row with matching credentials exists
        boolean isValid = cursor.moveToFirst();

        // Close the cursor and the database
        cursor.close();
        db.close();

        return isValid;
    }
}
