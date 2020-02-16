package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Calling extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    private Button call_999,call_10921;
    public String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final int REQUEST_CALL = 1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);

        call_999 = findViewById(R.id.call999);
        call_999.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = "999";
                makePhoneCall();

            }
        });

        call_10921 = findViewById(R.id.call109);
        call_10921.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = "10921";
                makePhoneCall();
            }
        });
    }

    private void makePhoneCall() {
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(Calling.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Calling.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(Calling.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
