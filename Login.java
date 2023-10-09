package com.example.demo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    TextView createAccountTextView;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(this);
        usernameEditText = findViewById(R.id.usnm);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.registerBtn);
        createAccountTextView = findViewById(R.id.CreateText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered username and password
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Check if the username and password match in the database
                if (isValidUser(username, password)) {
                    // Login successful, navigate to the next activity
                    Toast.makeText(Login.this, "Login is successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Home.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Invalid login credentials, show an error message
                    Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        createAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the registration activity
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValidUser(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {DBHelper.COLUMN_USERNAME, DBHelper.COLUMN_PASSWORD};
        String selection = DBHelper.COLUMN_USERNAME + " = ? AND " + DBHelper.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(
                DBHelper.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        db.close();

        return isValid;
    }
}
