package com.example.classschedule;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,"database.db",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table courses(" +
                "id integer primary key autoincrement," +
                "course_id text," +
                "course_name text," +
                "teacher text," +
                "week text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
