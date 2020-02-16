package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggle_siren;
    ToggleButton toggle_help;
    ToggleButton toggle_police;
    ToggleButton toggle_emer;
    ToggleButton toggle_extr;
    ToggleButton toggle_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView) findViewById(R.id.textview);
        textView.setText("This section is for informing about your current situation as dangarous.");
        textView.setVisibility(View.GONE);

        toggle_siren = (ToggleButton) findViewById(R.id.toggle_b1);
        toggle_siren.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (((ToggleButton)v).isChecked()){
                    textView.setVisibility(View.VISIBLE);
                }
                else{
                    textView.setVisibility(View.GONE);

                }
            }

        });


        final TextView textView1 = (TextView) findViewById(R.id.textview1);
        textView1.setText("In Help section you can send your location and a text to your desire guardian cell number.");
        textView1.setVisibility(View.GONE);

        toggle_help = (ToggleButton) findViewById(R.id.toggle_help);
        toggle_help.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (((ToggleButton)v).isChecked()){
                    textView1.setVisibility(View.VISIBLE);


                }
                else{
                    textView1.setVisibility(View.GONE);

                }
            }

        });

        final TextView textView2 = (TextView) findViewById(R.id.textview2);
        textView2.setText("In police station section you can find your nearest police station directory");
        textView2.setVisibility(View.GONE);

        toggle_police = (ToggleButton) findViewById(R.id.toggle_police);
        toggle_police.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (((ToggleButton)v).isChecked()){
                    textView2.setVisibility(View.VISIBLE);
                }
                else{
                    textView2.setVisibility(View.GONE);

                }
            }

        });


        final TextView textView3 = (TextView) findViewById(R.id.textview3);
        textView3.setText("This section will connect you with '999' help index immediately.");
        textView3.setVisibility(View.GONE);

        toggle_emer = (ToggleButton) findViewById(R.id.toggle_emer);
        toggle_emer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (((ToggleButton)v).isChecked()){
                    textView3.setVisibility(View.VISIBLE);
                }
                else{
                    textView3.setVisibility(View.GONE);

                }
            }

        });

        final TextView textView4 = (TextView) findViewById(R.id.textview4);
        textView4.setText("If you use extream section then you can send your current location at every 5 minutes.");
        textView4.setVisibility(View.GONE);

        toggle_extr = (ToggleButton) findViewById(R.id.toggle_extr);
        toggle_extr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (((ToggleButton)v).isChecked()){
                    textView4.setVisibility(View.VISIBLE);
                }
                else{
                    textView4.setVisibility(View.GONE);

                }
            }

        });


        final TextView textView5 = (TextView) findViewById(R.id.textview5);
        textView5.setText("In setting section you can set your desire guardian cell number and 5 contact.");
        textView5.setVisibility(View.GONE);

        toggle_setting = (ToggleButton) findViewById(R.id.toggle_setting);
        toggle_setting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (((ToggleButton)v).isChecked()){
                    textView5.setVisibility(View.VISIBLE);
                }
                else{
                    textView5.setVisibility(View.GONE);

                }
            }

        });
    }
}
