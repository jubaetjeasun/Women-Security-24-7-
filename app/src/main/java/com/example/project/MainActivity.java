package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.Manifest.permission;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;
import  android.widget.Toolbar;


import com.google.firebase.auth.FirebaseAuth;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {


    private CardView sirenPage,policeStationPage,helpPage,callingPage,extremePage,overviewPage ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS},
                        PackageManager.PERMISSION_GRANTED);


        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        sirenPage = findViewById(R.id.sirenCard);
        sirenPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,siren.class);
                startActivity(intent);
            }
        });

        policeStationPage = findViewById(R.id.policeCard);
        policeStationPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browseIntent  = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/police+station/"));
                startActivity(browseIntent);
            }
        });

        helpPage = findViewById(R.id.helpCard);
        helpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,location.class);
                startActivity(intent);
            }
        });

        callingPage = findViewById(R.id.callCard);
        callingPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Calling.class);
                startActivity(intent);
            }
        });

        extremePage = findViewById(R.id.extremeCard);
        extremePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent intent = new Intent(MainActivity.this,Extreme.class);
                startActivity(intent);
            }
        });

        overviewPage = findViewById(R.id.viewerCard);
        overviewPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(MainActivity.this,pagerOverview.class);
                startActivity(intent);
            }
        });

    }

    public void logout (View view){
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(MainActivity.this,login.class));
    }


    /*
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        //return super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){

        switch (item.getItemId()){
            case R.id.logout :
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(MainActivity.this,login.class));
                break;
        }


        return true;
    }*/
}
