package com.example.roadfuelgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class UserHome extends AppCompatActivity {
    private Button logout;
    private Button myprofile;
    private Button searchbunk;
    private Button bookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        logout=findViewById(R.id.button13);
        myprofile=findViewById(R.id.button10);
        searchbunk=findViewById(R.id.button11);
        bookings=findViewById(R.id.button12);
        bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserHome.this,UserBookings.class);
                startActivity(intent);
            }
        });
       myprofile.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(UserHome.this,UserProfile.class);
               startActivity(intent);
           }
       });
       searchbunk.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(UserHome.this,SearchBunk.class);
               startActivity(intent);
           }
       });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(UserHome.this, "logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserHome.this,MainActivity.class));
            }
        });

    }
}