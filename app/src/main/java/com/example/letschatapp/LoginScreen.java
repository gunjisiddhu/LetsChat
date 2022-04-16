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

public class LoginScreen extends AppCompatActivity {

    EditText email,password;
    Button submit;
    TextView signup;
    //Firebase
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        email = findViewById(R.id.signin_email);
        password  = findViewById(R.id.signin_password);
        submit = findViewById(R.id.signin_submit);
        signup = findViewById(R.id.signin_signup);

        auth = FirebaseAuth.getInstance();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String str_mail = email.getText().toString().trim();
                String str_pass = password.getText().toString();


                auth.signInWithEmailAndPassword(str_mail,str_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(LoginScreen.this, "No User Found!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignUpPage.class));
                finish();
            }
        });

    }
}