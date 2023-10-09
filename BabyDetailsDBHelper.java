package com.example.demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BabyDetailsDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BabyDetailsDatabase";
    private static final int DATABASE_VERSION = 1;

    // Define the table and column names for baby details
    public static final String TABLE_NAME = "BabyDetailsTable";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_GENDER = "gender";

    // Define the SQL statement to create the baby details table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_AGE + " TEXT, " +
                    COLUMN_GENDER + " TEXT)";

    public BabyDetailsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
