package com.example.kakaninavin.womensafety;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.widget.TextView;

public class notify extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String con[]=new String[50];
    Button sendMessage,locate;
    Double lat, longit;
    String latitude, longitude;

    SQLiteDatabase nsqlite;
    DatabaseHelper myhelper;

    int i=1,j=1;
    LocationManager locationManager;
    LocationListener locationListener;
    SQLiteDatabase nsqlite1;
    DatabaseBaseHelper1 myhelper1;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sendMessage = (Button) findViewById(R.id.b2);
        locate = (Button) findViewById(R.id.b1);

        myhelper = new DatabaseHelper(notify.this,"abzDB", null,1);
        nsqlite = myhelper.getWritableDatabase();

        myhelper1 = new DatabaseBaseHelper1(notify.this,"contactsDB", null,1);
        nsqlite1 = myhelper1.getWritableDatabase();

        Cursor c=nsqlite1.query("contacts",null,null,null,null,null,null);

        while(c.moveToNext())
        {
            con[i]=c.getString(c.getColumnIndex("mobileno"));
            i++;
        }
        j=i-1;

        Cursor cv=nsqlite.query("abz",null,null,null,null,null,null);
        while(cv.moveToNext())
        {
            name=cv.getString(c.getColumnIndex("mobileno"));
        }


        int permission_ALL=1;
        String[] Permissions={Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.SEND_SMS};
        if(!hasPermissions(this,Permissions))
        {
            ActivityCompat.requestPermissions(this,Permissions,permission_ALL);
        }



        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                lat = location.getLatitude();
                latitude = lat.toString();
                longit = location.getLongitude();
                longitude = longit.toString();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public static boolean hasPermissions(Context context,String... permissions )
    {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M && context!=null && permissions!=null)
        {
            for(String permission: permissions)
            {
                if(ActivityCompat.checkSelfPermission(context,permission)!=PackageManager.PERMISSION_GRANTED)
                {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.notify, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_addcon) {
            Intent i=new Intent(this,third_login.class);
            startActivity(i);
        } else if (id == R.id.nav_disp) {
            Intent i=new Intent(this,dispcont.class);
            startActivity(i);
        } else if (id == R.id.nav_del) {

        } else if (id == R.id.nav_lout) {
            String st="false";
            ContentValues cv = new ContentValues();
            cv.put("stat",st);
            nsqlite.insert("abz1",null,cv);
            Intent i=new Intent(this,second.class);
            startActivity(i);
            Toast.makeText(this,"Logged Off Successfully",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loc(View view) {
        locationManager.requestLocationUpdates("gps", 2000, 0, locationListener);
    }

    public void snd(View view) {
        SmsManager sms=SmsManager.getDefault();
        String mess="Hi This is "+name+" I am in Danger my Location is https://www.google.com/maps/?q="+latitude+","+longitude;
        for (int i=1;i<=j;i++)
        {
            sms.sendTextMessage(con[i],null,mess,null,null);
        }
        Toast.makeText(this,"Message Send Successfully",Toast.LENGTH_SHORT).show();
    }
}
