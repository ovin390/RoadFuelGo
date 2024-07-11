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

public class BunkSignUp extends AppCompatActivity {

    private Button sbtn;
    private EditText semail,spassword;
    private EditText cpassword;
    private FirebaseAuth auth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunk_sign_up);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        semail=findViewById(R.id.editTextTextPersonName3);
        spassword=findViewById(R.id.editTextTextPassword4);
        sbtn=findViewById(R.id.button9);
        cpassword=findViewById(R.id.editTextTextPassword6);
        auth=FirebaseAuth.getInstance();
        sbtn.setOnClickListener(new View.OnClickListener() {
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
                    //Toast.makeText(BunkSignUp.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                }else
                if(!lowercase.matcher(pass).matches()){
                    spassword.setError("Password must contain a lowercase character");
                    //Toast.makeText(BunkSignUp.this, "Password must contain a lowercase character", Toast.LENGTH_SHORT).show();

                }else
                if(pass.isEmpty()){
                    spassword.setError("Password cannot be empty");
                    //Toast.makeText(BunkSignUp.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                }else
                if (!uppercase.matcher(pass).matches()) {
                    spassword.setError("Password must contain a uppercase character");
                    //Toast.makeText(BunkSignUp.this, "Password must contain a uppercase character", Toast.LENGTH_SHORT).show();
                }else
                if (!number.matcher(pass).matches()) {
                    spassword.setError("Password must contain a number");
                    //Toast.makeText(BunkSignUp.this, "Password must contain a numbe", Toast.LENGTH_SHORT).show();
                }else
                if (!specialCharacter.matcher(pass).matches()) {
                    spassword.setError("Password must contain a special character");
                    //Toast.makeText(BunkSignUp.this, "Password must contain a special character", Toast.LENGTH_SHORT).show();
                }else
                if(pass.matches(cpass)){
                    auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(BunkSignUp.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(BunkSignUp.this,BunkLogin.class));
                            }else{
                                Toast.makeText(BunkSignUp.this, "SignUp Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText(BunkSignUp.this, "Password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}