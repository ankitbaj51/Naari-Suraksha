package com.example.kakaninavin.womensafety;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class second extends AppCompatActivity {
    Button btnMove2;
    Button btnMove3;
    EditText emob,epass;
    SQLiteDatabase nsqlite;
    DatabaseHelper myhelper;
    String mob1,pssw1,mob,pssw;

    public String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        emob =(EditText)findViewById(R.id.editmobile);
        epass =(EditText)findViewById(R.id.editpass);

        myhelper = new DatabaseHelper(second.this,"abzDB", null,1);
        nsqlite = myhelper.getWritableDatabase();

        btnMove2 = (Button) findViewById(R.id.btnMove2);
        btnMove2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                mob=emob.getText().toString();
                pssw=epass.getText().toString();

                Cursor c=nsqlite.query("abz",null,null,null,null,null,null);
                while(c.moveToNext())
                {
                    mob1=c.getString(c.getColumnIndex("mobile"));
                    pssw1=c.getString(c.getColumnIndex("password"));
                }
                if(mob.equals("") || pssw.equals(""))
                {
                    Toast.makeText(second.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if(mob1.equals(mob) && pssw1.equals(pssw))
                {
                    value="true";
                    ContentValues cv=new ContentValues();
                    cv.put("stat",value);
                    nsqlite.insert("abz1",null,cv);
                    Intent i=new Intent(second.this,third_login.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(second.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnMove3 =(Button) findViewById(R.id.btnMove3);
        btnMove3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(second.this,fourth_signup.class);
                startActivity(i);
            }
        });


    }
}
