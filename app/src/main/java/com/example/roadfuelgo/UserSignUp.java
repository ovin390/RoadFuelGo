package com.example.roadfuelgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class UserSignUp extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText semail,spassword;
    private Button sbutton;
    private EditText cpassword;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        auth=FirebaseAuth.getInstance();
        semail=findViewById(R.id.editTextTextPersonName6);
        spassword=findViewById(R.id.editTextTextPassword);
        sbutton=findViewById(R.id.button7);
        cpassword=findViewById(R.id.editTextTextPassword5);

        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pattern lowercase = Pattern.compile("^.*[a-z].*$");
                Pattern uppercase = Pattern.compile("^.*[A-Z].*$");
                Pattern number = Pattern.compile("^.*[0-9].*$");
                Pattern specialCharacter = Pattern.compile("^.*[^a-zA-Z0-9].*$");
                String email=semail.getText().toString();
                String pass=spassword.getText().toString();
                String cpass=cpassword.getText().toString();
                if(email.isEmpty()){
                    semail.setError("Email cannot be empty");
                }else
                if(!lowercase.matcher(pass).matches()){
                    spassword.setError("Password must contain a lowercase character");
                }else
                if(pass.isEmpty()){
                    spassword.setError("Password cannot be empty");
                }else
                if (!uppercase.matcher(pass).matches()) {
                    spassword.setError("Password must contain a uppercase character");
                }else
                if (!number.matcher(pass).matches()) {
                    spassword.setError("Password must contain a number");
                }else
                if (!specialCharacter.matcher(pass).matches()) {
                    spassword.setError("Password must contain a special character");
                }else
                if(pass.matches(cpass)){
                    auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(UserSignUp.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(UserSignUp.this,UserLogin.class));
                            }else{
                                Toast.makeText(UserSignUp.this, "SignUp Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(UserSignUp.this, "Password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}