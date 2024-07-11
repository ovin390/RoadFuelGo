package com.example.roadfuelgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class BunkLogin extends AppCompatActivity {
    private Button signup;
    private Button login;
    private EditText email;
    private EditText password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunk_login);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        signup=findViewById(R.id.button8);
        email=findViewById(R.id.editTextTextPersonName2);
        password=findViewById(R.id.editTextTextPassword3);
        login=findViewById(R.id.button6);
        auth=FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BunkLogin.this,BunkSignUp.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email=email.getText().toString();
                String txt_password=password.getText().toString();
                loginBunk(txt_email,txt_password);
            }
        });
    }
    private void loginBunk(String email, String password) {
        if (email.isEmpty()) {
            Toast.makeText(BunkLogin.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
        }else
        if(password.isEmpty()){
            Toast.makeText(BunkLogin.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
        }

        else {
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(BunkLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BunkLogin.this, BunkHome.class));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(BunkLogin.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}