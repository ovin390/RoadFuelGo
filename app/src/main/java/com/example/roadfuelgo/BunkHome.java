package com.example.roadfuelgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class BunkHome extends AppCompatActivity {
    private Button addbunk;
    private Button updatebunk;

    private Button logout;
    private Button bookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunk_home);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        addbunk=findViewById(R.id.button15);
        logout=findViewById(R.id.button17);
        updatebunk=findViewById(R.id.button18);
        bookings=findViewById(R.id.button24);
        bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BunkHome.this,BunkBookings.class);
                startActivity(intent);
            }
        });
        addbunk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BunkHome.this,AddBunk.class);
                startActivity(intent);
            }
        });
        updatebunk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BunkHome.this,UpadteBunk.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(BunkHome.this, "logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BunkHome.this,MainActivity.class));
            }
        });
    }
}