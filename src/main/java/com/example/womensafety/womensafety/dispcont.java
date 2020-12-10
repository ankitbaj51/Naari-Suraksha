package com.example.kakaninavin.womensafety;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dispcont extends AppCompatActivity {
    SQLiteDatabase nsqlite1;
    DatabaseBaseHelper1 myhelper1;
String cont[]=new String[50];
int i=1,j=1;
ListView l1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispcont);
        l1=(ListView)findViewById(R.id.list);
        myhelper1 = new DatabaseBaseHelper1(this,"contactsDB", null,1);
        nsqlite1 = myhelper1.getWritableDatabase();

        Cursor c=nsqlite1.query("contacts",null,null,null,null,null,null);

        while(c.moveToNext())
        {
            cont[i]=c.getString(c.getColumnIndex("mobileno"));
            i++;
        }
        j=i-1;
        List<String> co=new ArrayList<String>(Arrays.asList(cont));
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,co);
       l1.setAdapter(arrayAdapter);
    }
}
