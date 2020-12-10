package com.example.kakaninavin.womensafety;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class first extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1000;
    SQLiteDatabase nsqlite;
    DatabaseHelper myhelper;
    String val,status="false";
    boolean firstStart;

    void databaseSetup()
    {
        ContentValues cv = new ContentValues();
        cv.put("stat", status);
        nsqlite.insert("abz1", null, cv);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        myhelper = new DatabaseHelper(first.this, "abzDB", null, 1);
        nsqlite = myhelper.getWritableDatabase();

        SharedPreferences setting = getSharedPreferences("PREFS",0);
        firstStart=setting.getBoolean("first_time",true);
        if(firstStart){
            SharedPreferences.Editor editor=setting.edit();
            editor.putBoolean("first_time",false);
            editor.commit();
            databaseSetup();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Cursor c = nsqlite.query("abz1", null, null, null, null, null, null);
                while (c.moveToNext())
                {
                    val = c.getString(c.getColumnIndex("stat"));
                }
                if (val.equals("true")) {
                    Intent i = new Intent(first.this, notify.class);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(first.this, second.class);
                    startActivity(i);
                    finish();
                }

            }
        }, SPLASH_TIME_OUT);

    }

}
