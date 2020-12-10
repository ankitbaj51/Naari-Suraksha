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

public class fourth_signup extends AppCompatActivity {

    Button btnMove5;
    EditText name ,mobile_no,address,email,pass,conpass;
    SQLiteDatabase nsqlite;
    DatabaseHelper myhelper;
    String nme,mob,ad,eml,pss,conp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_signup);

        name =(EditText)findViewById(R.id.TEname);
         mobile_no =(EditText)findViewById(R.id.TEmob);
         address =(EditText)findViewById(R.id.TEAddress);
         email =(EditText)findViewById(R.id.TEmail);
         pass =(EditText)findViewById(R.id.TEpass);
         conpass =(EditText)findViewById(R.id.TEconpass);
         myhelper = new DatabaseHelper(fourth_signup.this,"abzDB", null,1);
         nsqlite = myhelper.getWritableDatabase();

         btnMove5 = (Button) findViewById(R.id.btnMove5);
         btnMove5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(fourth_signup.this,second.class);
                startActivity(i);
            }
        });
    }

    public void save(View view) {
        ContentValues cv=new ContentValues();
        nme=name.getText().toString();
        mob=mobile_no.getText().toString();
        ad=address.getText().toString();
        eml=email.getText().toString();
        pss=pass.getText().toString();
        conp=conpass.getText().toString();



        if(nme.equals("") || mob.equals("")|| ad.equals("") || eml.equals("") || pss.equals("") || conp.equals("") )
        {
            Toast.makeText(fourth_signup.this, "Compulsory fields cannot be empty", Toast.LENGTH_SHORT).show();
        }
         else if(!pss.equals(conp))
        {
            Toast.makeText(fourth_signup.this, "Password and Confirm Password doesn't match", Toast.LENGTH_SHORT).show();
        }
        else
        {
            cv.put("name",nme);
            cv.put("mobile",mob);
            cv.put("address",ad);
            cv.put("email",eml);
            cv.put("password",pss);
            cv.put("conpassword",conp);

            long id=  nsqlite.insert("abz",null,cv);
            Toast.makeText(fourth_signup.this, "Account Created Succesfully", Toast.LENGTH_SHORT).show();
            name.setText("");
            mobile_no.setText("");
            address.setText("");
            email.setText("");
            pass.setText("");
            conpass.setText("");

        }
    }
}
