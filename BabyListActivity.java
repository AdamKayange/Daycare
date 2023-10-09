package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;



import java.util.ArrayList;

public class BabyListActivity extends AppCompatActivity {
    DBHelper dbHelper;
    ListView babyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_list);

        dbHelper = new DBHelper(this);
        babyListView = findViewById(R.id.babyListView);

        // Retrieve the list of babies from the database
        ArrayList<String> babyNames = getBabyNames();

        // Create an ArrayAdapter to populate the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, babyNames);

        // Set the adapter for the ListView
        babyListView.setAdapter(adapter);
    }

    private ArrayList<String> getBabyNames() {
        ArrayList<String> babyNames = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {DBHelper.COLUMN_BABY_NAME};

        Cursor cursor = db.query(
                DBHelper.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            String babyName = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_BABY_NAME));
            babyNames.add(babyName);
        }

        cursor.close();
        db.close();

        return babyNames;
    }
}
