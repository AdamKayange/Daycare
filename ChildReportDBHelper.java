package com.example.demo;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class ChildReportDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ChildReportDatabase";
    private static final int DATABASE_VERSION = 1;

    // Define the table and column names for ChildReport registration
    public static final String TABLE_NAME = "ChildReportTable";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ARTIME = "artime";
    public static final String COLUMN_LEAVTIME = "leavtime";
    public static final String COLUMN_DAILYACTVTY = "dailyactvty";
    public static  final String COLUMN_SLEEPTIME = "sleeptime";

    // Define the SQL statement to create the childreport table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ARTIME + " TEXT, " +
                    COLUMN_LEAVTIME + " TEXT, " +
                    COLUMN_DAILYACTVTY + " TEXT, " +  // Added a space before "TEXT"
                    COLUMN_SLEEPTIME + " TEXT)";  // Added a space before "TEXT"


    public ChildReportDBHelper(Context context) {
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
