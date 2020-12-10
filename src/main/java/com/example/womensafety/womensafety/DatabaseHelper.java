package com.example.kakaninavin.womensafety;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version)
    {
        super(context,name,factory,version);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table abz (_id integer primary key,name text,mobile text,address text,email text,password text,conpassword text)");
        db.execSQL("create table abz1 (stat text)");

    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
