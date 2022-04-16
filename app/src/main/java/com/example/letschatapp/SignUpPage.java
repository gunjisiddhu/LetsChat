package com.example.letschatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpPage extends AppCompatActivity {

    EditText name,email,password;
    Button submit;
    TextView signup;


    FirebaseAuth auth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        name = findViewById(R.id.signup_name);
        email = findViewById(R.id.signup_mail);
        password  = findViewById(R.id.signup_password);
        submit = findViewById(R.id.signup_submit);
        signup = findViewById(R.id.signup_signin);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_name = name.getText().toString().trim();
                String str_mail = email.getText().toString().trim();
                String str_pass = password.getText().toString();


                auth.createUserWithEmailAndPassword(str_mail,str_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUpPage.this, "Done!!"+auth.getUid(), Toast.LENGTH_SHORT).show();
                            DatabaseReference reference = database.getReference("Users").child(auth.getUid());
                            UserData userData = new UserData(str_name,str_mail,str_pass);
                            Toast.makeText(SignUpPage.this, userData.getEmail(), Toast.LENGTH_SHORT).show();
                            reference.setValue(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),"Created Successfully",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),LoginScreen.class));
                                        finish();
                                    }else{
                                        Toast.makeText(SignUpPage.this, "Not Created! Fuck you", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(SignUpPage.this, "Somethings wrong..! Try Again!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginScreen.class));
                finish();
            }
        });
    }
}