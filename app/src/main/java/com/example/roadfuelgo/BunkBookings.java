//package com.example.roadfuelgo;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class BunkBookings extends AppCompatActivity {
//    private Button show;
//    private EditText bid;
//    private TextView cname;
//    private TextView ftype;
//    private TextView qt;
//    private TextView loc;
//    private TextView bd;
//    private TextView mobile;
//    DatabaseReference reference;
//    String id;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bunk_bookings);
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
//        show=findViewById(R.id.button26);
//        bid=findViewById(R.id.editTextText21);
//        cname=findViewById(R.id.textView69);
//        mobile=findViewById(R.id.textView70);
//        ftype=findViewById(R.id.textView71);
//        qt=findViewById(R.id.textView72);
//        bd=findViewById(R.id.textView73);
//        loc=findViewById(R.id.textView74);
//        show.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                id=bid.getText().toString();
//                if(!id.isEmpty()){
//                    searchBookings(id);
//                }else{
//                    Toast.makeText(BunkBookings.this, "Please Enter Id", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//    private void searchBookings(String id){
//        reference= FirebaseDatabase.getInstance().getReference("Bookings");
//        reference.child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if(task.isSuccessful()){
//                    if(task.getResult().exists()){
//                        Toast.makeText(BunkBookings.this, "Successfully Read", Toast.LENGTH_SHORT).show();
//                        DataSnapshot dataSnapshot=task.getResult();
//                        String name=String.valueOf(dataSnapshot.child("custname").getValue());
//                        String fuelltype=String.valueOf(dataSnapshot.child("fueltype").getValue());
//                        String qauantity=String.valueOf(dataSnapshot.child("quantity").getValue());
//                        String locaation=String.valueOf(dataSnapshot.child("location").getValue());
//                        String bdate=String.valueOf(dataSnapshot.child("bdate").getValue());
//                        String pphone=String.valueOf(dataSnapshot.child("phone").getValue());
//                        cname.setText(name);
//                        ftype.setText(fuelltype);
//                        qt.setText(qauantity);
//                        loc.setText(locaation);
//                        bd.setText(bdate);
//                        mobile.setText(pphone);
//                    }else{
//                        Toast.makeText(BunkBookings.this, "Booking Doesn't Exist", Toast.LENGTH_SHORT).show();
//                    }
//                }else{
//                    Toast.makeText(BunkBookings.this, "Failed to read data", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//}
package com.example.roadfuelgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BunkBookings extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BookingAdapter bookingAdapter;
    private List<bookb> bookingList;
    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunk_bookings);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));

        recyclerView = findViewById(R.id.hi);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the booking list
        bookingList = new ArrayList<>();

        // Create an instance of the BookingAdapter
        bookingAdapter = new BookingAdapter(bookingList);
        recyclerView.setAdapter(bookingAdapter);

        // Get a reference to the "Bookings" node in your Firebase database
        reference = FirebaseDatabase.getInstance().getReference("Bookings");

        // Fetch the booking data from Firebase
        fetchBookings();
    }

    private void fetchBookings() {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot bookingSnapshot : dataSnapshot.getChildren()) {
                    bookb booking = bookingSnapshot.getValue(bookb.class);
                    bookingList.add(booking);
                }

                // Notify the adapter that the data has changed
                bookingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BunkBookings.this, "Failed to fetch bookings", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
