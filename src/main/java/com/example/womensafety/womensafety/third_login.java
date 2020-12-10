package com.example.kakaninavin.womensafety;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class third_login extends AppCompatActivity {

    Button b;
    EditText phone;
    String ph;
    SQLiteDatabase nsqlite1;
    DatabaseBaseHelper1 myhelper1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_login);
        b=(Button)findViewById(R.id.button);
        phone =(EditText)findViewById(R.id.addcon);
        myhelper1 = new DatabaseBaseHelper1(third_login.this,"contactsDB", null,1);
        nsqlite1 = myhelper1.getWritableDatabase();

    }

    public void addcontacts(View view) {

        ContentValues cv1=new ContentValues();
        ph=phone.getText().toString();
        if(ph.equals(""))
        {
            Toast.makeText(third_login.this, "Field is Empty",Toast.LENGTH_SHORT).show();
        }
        else {
            cv1.put("mobileno", ph);
            long id1 = nsqlite1.insert("contacts", null, cv1);
            Toast.makeText(third_login.this, "Contact Successfully Added", Toast.LENGTH_SHORT).show();
            phone.setText("");
        }
    }

    public void next(View view) {
        Intent i=new Intent(third_login.this,notify.class);
        startActivity(i);
    }
}
