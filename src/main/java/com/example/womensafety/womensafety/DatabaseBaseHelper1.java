package com.example.kakaninavin.womensafety;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseBaseHelper1 extends SQLiteOpenHelper{

    public DatabaseBaseHelper1(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context,name,factory,version);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table contacts (_id1 integer primary key,mobileno text)");

    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}


