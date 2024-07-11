package com.example.roadfuelgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserBookings extends AppCompatActivity {

    private Button show;
    private EditText bid;
    private TextView bname;
    private TextView ftype;
    private TextView qt;
    private TextView loc;
    private TextView bd;
    DatabaseReference reference;
    String id;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_bookings);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        bid=findViewById(R.id.editTextText19);
        bname=findViewById(R.id.textView57);
        ftype=findViewById(R.id.textView58);
        qt=findViewById(R.id.textView59);
        loc=findViewById(R.id.textView60);
        bd=findViewById(R.id.textView52);
        show=findViewById(R.id.button25);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=bid.getText().toString();
                if(!id.isEmpty()){
                    searchBookings(id);
                }else{
                    Toast.makeText(UserBookings.this, "Please Enter Id", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void searchBookings(String id) {
        reference= FirebaseDatabase.getInstance().getReference("Bookings");
        reference.child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        Toast.makeText(UserBookings.this, "Successfully Read", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot=task.getResult();
                        String name=String.valueOf(dataSnapshot.child("bunkname").getValue());
                        String fuelltype=String.valueOf(dataSnapshot.child("fueltype").getValue());
                        String qauantity=String.valueOf(dataSnapshot.child("quantity").getValue());
                        String locaation=String.valueOf(dataSnapshot.child("location").getValue());
                        String bdate=String.valueOf(dataSnapshot.child("bdate").getValue());
                        bname.setText(name);
                        ftype.setText(fuelltype);
                        qt.setText(qauantity);
                        loc.setText(locaation);
                        bd.setText(bdate);
                    }else{
                        Toast.makeText(UserBookings.this, "Booking Doesn't Exist", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(UserBookings.this, "Failed to read data", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}