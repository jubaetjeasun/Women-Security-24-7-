package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class login extends AppCompatActivity {


    //login
    EditText username, password;
    Button login, signUp;
    TextInputLayout txtInLayoutUsername, txtInLayoutPassword;
    CheckBox rememberMe;

    //reg
    EditText reg_username, reg_password, reg_firstName, reg_lastName, reg_email, reg_confirmemail;
    Button  reg_register;
    TextInputLayout txtInLayoutRegPassword;

    //database
    private FirebaseAuth mAuth;

    //
    boolean checkName,checkPass ;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signUp = findViewById(R.id.signUp);
        txtInLayoutUsername = findViewById(R.id.txtInLayoutUsername);
        txtInLayoutPassword = findViewById(R.id.txtInLayoutPassword);


        mAuth = FirebaseAuth.getInstance();



        ClickLogin();


        //SignUp's Button for showing registration page
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickSignUp();
            }
        });
    }

    @Override
    protected void onStart (){
        super.onStart();
        if(mAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(login.this,MainActivity.class));
        }

    }

    private void ClickLogin() {

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (username.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, "Please fill out these fields",Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    snackbar.show();
                    txtInLayoutUsername.setError("Username should not be empty");
                }

                if (password.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, "Please fill out these fields",Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    snackbar.show();
                    txtInLayoutPassword.setError("Password should not be empty");
                }

                if (email.isEmpty()){
                    username.setError("Please fill out this field");
                    username.requestFocus();
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    username.setError("Please enter valid email");
                    username.requestFocus();
                }

                if (pass.isEmpty()) {
                    txtInLayoutRegPassword.setPasswordVisibilityToggleEnabled(false);
                    password.setError("Please fill out this field");
                    password.requestFocus();
                }

                if(pass.length()<6){
                    password.setError("Password must be more than six letters");
                    password.requestFocus();
                }

                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete (@NonNull Task<AuthResult> task){
                        if(task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(login.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Error : "+task.getException().getMessage(),Toast.LENGTH_LONG).show();

                    }
                });


            }
        });

    }


    private void ClickSignUp() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_registration, null);
        dialog.setView(dialogView);

        reg_username = dialogView.findViewById(R.id.reg_username);
        reg_password = dialogView.findViewById(R.id.reg_password);
        reg_firstName = dialogView.findViewById(R.id.reg_firstName);
        reg_lastName = dialogView.findViewById(R.id.reg_lastName);
        reg_email = dialogView.findViewById(R.id.reg_email);
        reg_confirmemail = dialogView.findViewById(R.id.reg_confirmemail);
        reg_register = dialogView.findViewById(R.id.reg_register);
        txtInLayoutRegPassword = dialogView.findViewById(R.id.txtInLayoutRegPassword);


        reg_register.setOnClickListener(new View.OnClickListener() {



            public void onClick(View view) {

                String email = reg_email.getText().toString().trim();
                String pass = reg_password.getText().toString().trim();

                if (reg_username.getText().toString().trim().isEmpty()) {
                    reg_username.setError("Please fill out this field");
                }

                if (email.isEmpty()){
                    reg_email.setError("Please fill out this field");
                    reg_email.requestFocus();
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    reg_email.setError("Please enter valid email");
                    reg_email.requestFocus();
                }

                if (pass.isEmpty()) {
                    txtInLayoutRegPassword.setPasswordVisibilityToggleEnabled(false);
                    reg_password.setError("Please fill out this field");
                    reg_password.requestFocus();
                }

                if(pass.length()<6){
                    reg_password.setError("Password must be more than six letters");
                    reg_password.requestFocus();
                }


                if (reg_firstName.getText().toString().trim().isEmpty()) {

                    reg_firstName.setError("Please fill out this field");
                }

                if (reg_lastName.getText().toString().trim().isEmpty()) {

                    reg_lastName.setError("Please fill out this field");
                }


                if (reg_confirmemail.getText().toString().trim().isEmpty()) {
                    reg_confirmemail.setError("Please fill out this field");
                }

               mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete (@NonNull Task<AuthResult> task){
                        if(task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(login.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            Toast.makeText(login.this, "you are registered succesfully", Toast.LENGTH_LONG).show();
                        }

                        else {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException)
                                Toast.makeText(login.this,"Already registered",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(getApplicationContext(),"Error : "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });


        dialog.show();
    }
}
