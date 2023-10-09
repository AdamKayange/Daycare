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


public class StartActivity extends AppCompatActivity {
    EditText babyEdit;
    EditText motherEdit;
    EditText fatherEdit;
    EditText usernameEdit;
    EditText emailEdit;
    EditText passwordEdit;
    EditText phoneEdit;
    Button registerEdit;
    TextView rudiEdit;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        dbHelper = new DBHelper(this);

        babyEdit = findViewById(R.id.babyname);
        motherEdit = findViewById(R.id.mothername);
        fatherEdit = findViewById(R.id.fathername);
        usernameEdit = findViewById(R.id.username);
        emailEdit = findViewById(R.id.email);
        passwordEdit = findViewById(R.id.password);
        phoneEdit = findViewById(R.id.phonenumber);
        registerEdit = findViewById(R.id.RegisterBtn);
        rudiEdit = findViewById(R.id.createText);
        rudiEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ButtonActivity.class);
                startActivity(intent);
            }
        });

        registerEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
    }

    private void insertData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_BABY_NAME, babyEdit.getText().toString());
        values.put(DBHelper.COLUMN_FATHER_NAME, fatherEdit.getText().toString());
        values.put(DBHelper.COLUMN_MOTHER_NAME, motherEdit.getText().toString());
        values.put(DBHelper.COLUMN_USERNAME, usernameEdit.getText().toString());
        values.put(DBHelper.COLUMN_EMAIL, emailEdit.getText().toString());
        values.put(DBHelper.COLUMN_PHONE_NUMBER, phoneEdit.getText().toString());
        values.put(DBHelper.COLUMN_PASSWORD, passwordEdit.getText().toString());

        long newRowId = db.insert(DBHelper.TABLE_NAME, null, values);
        if (newRowId != -1) {
            // Insertion was successful
            // You can show a success message or navigate to another screen here
            Toast.makeText(StartActivity.this,"Registration is successfully",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),BabyAfterRegister.class);
            startActivity(intent);
        } else {
            // Insertion failed
            // You can show an error message here
            Toast.makeText(StartActivity.this,"Registration is failed",Toast.LENGTH_SHORT).show();
        }

        // Close the database connection
        db.close();
    }
}
