package com.example.demo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FullfillBabyActivity extends AppCompatActivity {
    EditText ageEditText;
    RadioGroup genderRadioGroup;
    RadioButton maleRadioButton;
    RadioButton femaleRadioButton;
    Button registerButton;
    BabyDetailsDBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullfill_baby);

        dbHelper = new BabyDetailsDBHelper(this);

        ageEditText = findViewById(R.id.age);
        genderRadioGroup = findViewById(R.id.radioGroup);
        maleRadioButton = findViewById(R.id.maleButton);
        femaleRadioButton = findViewById(R.id.femaleButton);
        registerButton = findViewById(R.id.button1);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input
                String age = ageEditText.getText().toString().trim();
                String gender = getSelectedGender();

                // Check inputs
                if (age.isEmpty() || gender.isEmpty()) {
                    Toast.makeText(FullfillBabyActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Execute registration
                    new RegisterTask().execute(age, gender);
                }
            }
        });
    }

    private String getSelectedGender() {
        int selectedRadioButtonId = genderRadioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId == maleRadioButton.getId()) {
            return "Male";
        } else if (selectedRadioButtonId == femaleRadioButton.getId()) {
            return "Female";
        }
        return "";
    }

    private class RegisterTask extends AsyncTask<String, Void, Long> {
        @Override
        protected Long doInBackground(String... strings) {
            String age = strings[0];
            String gender = strings[1];

            // Insert baby details into the database
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(BabyDetailsDBHelper.COLUMN_AGE, age);
            values.put(BabyDetailsDBHelper.COLUMN_GENDER, gender);

            long newRowId = db.insert(BabyDetailsDBHelper.TABLE_NAME, null, values);
            db.close();

            return newRowId;
        }

        @Override
        protected void onPostExecute(Long newRowId) {
            if (newRowId != -1) {
                Toast.makeText(FullfillBabyActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), BaadaMtoto.class);
                startActivity(intent);
            } else {
                Toast.makeText(FullfillBabyActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
