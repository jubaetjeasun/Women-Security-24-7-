package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class siren extends AppCompatActivity implements View.OnClickListener {

    private Button buttonStart;
    private Button buttonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siren);

        buttonStart = (Button) findViewById(R.id.button1);
        buttonStop = (Button) findViewById(R.id.button2);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonStart) {
            //starting service
            startService(new Intent(this,MyService.class));
        }
        if (view == buttonStop) {
            //stopping service
            stopService(new Intent(this,MyService.class));
        }

    }
}
