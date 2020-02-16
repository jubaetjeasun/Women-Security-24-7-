package com.example.project;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class help extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    TextView position ;
    String S;

    String tfirstContact,tsecondContact,tthirdContact,tfourthContact,tfifthContact;

    private FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        ActivityCompat.requestPermissions(help.this,new String[]{Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);

        S = getIntent().getExtras().getString("URL");

        position = findViewById(R.id.loc);
        position.setText(S);

        mAuth = FirebaseAuth.getInstance();
        loadInformation();
    }

    public void Sender(View view){

        try {
            String a = "I,m in Danger.\nPlease help me,my current location is : \n ";
            String message = a + S;
            String number1 = tfirstContact;
            String number2 = tsecondContact;
            String number3 = tthirdContact;
            String number4 = tfourthContact;
            String number5 = tfifthContact;


            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(number1, null, message, null, null);
            sms.sendTextMessage(number2, null, message, null, null);
            sms.sendTextMessage(number3, null, message, null, null);
            sms.sendTextMessage(number4, null, message, null, null);
            sms.sendTextMessage(number5, null, message, null, null);


            Toast.makeText(getApplicationContext(), "Text and location is Send", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(help.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
        }
    }

    public void Texter (View view){
        try {
            String a = "I'm in Danger,Contact me soon please \n: ";
            String message = a;
            member Member = new member();

            //String number = "01521431446";
            String number1 = tfirstContact;
            String number2 = tsecondContact;
            String number3 = tthirdContact;
            String number4 = tfourthContact;
            String number5 = tfifthContact;


            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(number1, null, message, null, null);
            sms.sendTextMessage(number2, null, message, null, null);
            sms.sendTextMessage(number3, null, message, null, null);
            sms.sendTextMessage(number4, null, message, null, null);
            sms.sendTextMessage(number5, null, message, null, null);


            Toast.makeText(getApplicationContext(), "Text is Send", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(help.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
        }

    }


    public void loadInformation(){
        FirebaseUser user = mAuth.getCurrentUser();

        if(user.getDisplayName() != null){
            String contacts = user.getDisplayName();

            tfirstContact = (contacts.substring(0,11)).toString();
            tsecondContact = (contacts.substring(11,22).toString());
            tthirdContact = (contacts.substring(22,33)).toString();
            tfourthContact = (contacts.substring(33,44).toString());
            tfifthContact = (contacts.substring(44,55)).toString();
        }


    }


}
