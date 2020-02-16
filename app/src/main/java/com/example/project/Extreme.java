package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;

public class Extreme extends AppCompatActivity {

    //Button extrmeEmergency ;
    //Thread t;
    //String S;

    EditText firstContact,secondContact,thirdContact,fourthContact,fifthContact;
    TextView tfirstContact,tsecondContact,tthirdContact,tfourthContact,tfifthContact;
    Button addContact;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extreme);


        firstContact = findViewById(R.id.one);
        secondContact = findViewById(R.id.two);
        thirdContact = findViewById(R.id.three);
        fourthContact = findViewById(R.id.four);
        fifthContact = findViewById(R.id.five);

        tfirstContact = findViewById(R.id.tone);
        tsecondContact = findViewById(R.id.ttwo);
        tthirdContact = findViewById(R.id.tthree);
        tfourthContact = findViewById(R.id.tfour);
        tfifthContact = findViewById(R.id.tfive);

        addContact = findViewById(R.id.add);

        mAuth = FirebaseAuth.getInstance();

        loadInformation();

        mDatabase = FirebaseDatabase.getInstance().getReference();


        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                saveInformation();
            }
        });

        //ActivityCompat.requestPermissions(Extreme.this,new String[]{Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);

        //extrmeEmergency = findViewById(R.id.emg);
        //S = getIntent().getExtras().getString("URL");

        /*t=new Thread(){
            @Override
            public void run(){

                while(!isInterrupted()){

                    try {
                        Thread.sleep(3000);  //1000ms = 1 sec // 5min
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String message = S;
                                String number =  "01521431446";

                                SmsManager sms = SmsManager.getDefault();
                                sms.sendTextMessage(number,null,message , null,null);
                            }
                        });

                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        extrmeEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                t.start();
            }
        });
        */


    }


    protected void onStart (){
        super.onStart();
        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(Extreme.this,login.class));
        }

    }


    public void saveInformation(){

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
        String userId = mDatabase.push().getKey();



        member Member = new member();

        String guardianOne = firstContact.getText().toString().trim();
        Member.setFirstID(guardianOne);

        String guardianTwo = secondContact.getText().toString().trim();
        Member.setSecondID(guardianTwo);

        String guardianThree = thirdContact.getText().toString().trim();
        Member.setThirdID(guardianThree);

        String guardianFour = fourthContact.getText().toString().trim();
        Member.setFourthID(guardianFour);

        String guardianFive = fifthContact.getText().toString().trim();
        Member.setFifthID(guardianFive);




        if(guardianOne.length()<11){
            firstContact.setError("Error Character input");
            firstContact.requestFocus();
            return;
        }
        if(guardianTwo.length()<11){
            secondContact.setError("Error Character input");
            secondContact.requestFocus();
            return;
        }
        if(guardianThree.length()<11){
            thirdContact.setError("Error Character input");
            thirdContact.requestFocus();
            return;
        }
        if(guardianFour.length()<11){
            fourthContact.setError("Error Character input");
            fourthContact.requestFocus();
            return;
        }
        if(guardianFive.length()<11){
            fifthContact.setError("Error Character input");
            fifthContact.requestFocus();
            return;
        }

        if(guardianOne.isEmpty()){
            firstContact.setError("Add Contact");
            firstContact.requestFocus();
            return;
        }
        if(guardianTwo.isEmpty()){
            secondContact.setError("Add Contact");
            secondContact.requestFocus();
            return;
        }
        if(guardianThree.isEmpty()){
            thirdContact.setError("Add Contact");
            thirdContact.requestFocus();
            return;
        }
        if(guardianFour.isEmpty()){
            fourthContact.setError("Add Contact");
            fourthContact.requestFocus();
            return;
        }
        if(guardianFive.isEmpty()){
            fifthContact.setError("Add Contact");
            fifthContact.requestFocus();
            return;
        }




        FirebaseUser user = mAuth.getCurrentUser();
        String ID = mAuth.getCurrentUser().getUid();
        String Display = guardianOne + guardianTwo + guardianThree + guardianFour + guardianFive;
        if(user != null){

            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()


                    .setDisplayName(Display)
                    //.setDisplayName(guardianTwo)
                    //.setDisplayName(guardianThree)
                    //.setDisplayName(guardianFour)
                    //.setDisplayName(guardianFive)
                    .build();

            user.updateProfile(profile)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete (@NonNull Task<Void> task){
                            if(task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_LONG).show();
                                loadInformation();
                            }

                        }
                    });
            //mDatabase.child(ID).setValue(Member);

        }

    }

    public void loadInformation(){
        FirebaseUser user = mAuth.getCurrentUser();

        if(user.getDisplayName() != null){
            String contacts = user.getDisplayName();

            tfirstContact.setText(contacts.substring(0,11));
            tsecondContact.setText(contacts.substring(11,22));
            tthirdContact.setText(contacts.substring(22,33));
            tfourthContact.setText(contacts.substring(33,44));
            tfifthContact.setText(contacts.substring(44,55));

            member Member = new member();

            Member.setFirstID(tfirstContact.toString());
            Member.setSecondID(tsecondContact.toString());
            Member.setThirdID(tthirdContact.toString());
            Member.setFourthID(tfourthContact.toString());
            Member.setFifthID(tfifthContact.toString());


            //FirebaseUser user = mAuth.getCurrentUser();
            /*String ID = mAuth.getCurrentUser().getUid();

            mDatabase.child(ID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange (@NonNull DataSnapshot dataSnapshot){
                    if(dataSnapshot.getValue(String.class) != null)
                    {
                        String key = dataSnapshot.getKey();
                        if(key.equals("firstID"))
                           tfirstContact.setText(dataSnapshot.getValue(String.class).toString());

                        if(key.equals("secondID"))
                        tsecondContact.setText(dataSnapshot.getValue(String.class).toString());

                        if(key.equals("thirdID"))
                            thirdContact.setText(dataSnapshot.getValue(String.class).toString());


                    }

                }

                @Override
                public void onCancelled (@NonNull DatabaseError databaseError){

                }
            });*/

        }


    }
}
