package com.example.demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyDatabase";
    private static final int DATABASE_VERSION = 1;

    // Define the table and column names
    public static final String TABLE_NAME = "UserTable";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_BABY_NAME = "baby_name";
    public static final String COLUMN_FATHER_NAME = "father_name";
    public static final String COLUMN_MOTHER_NAME = "mother_name";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_PASSWORD = "password";

    // Define the SQL statement to create the table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_BABY_NAME + " TEXT, " +
                    COLUMN_FATHER_NAME + " TEXT, " +
                    COLUMN_MOTHER_NAME + " TEXT, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PHONE_NUMBER + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT);";

    public DBHelper(Context context) {
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





