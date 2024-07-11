package com.example.roadfuelgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class SearchBunk extends AppCompatActivity {
    DatabaseReference reference;
    private Button search;
    private Button map;
    private Button book;
    private EditText userenteredarea;
    String barea;
    private TextView na;
    private TextView cty;
    private TextView ph;
    private TextView fav;
    private TextView ppr;
    private TextView dpr;
    private TextView lpr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bunk);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        search=findViewById(R.id.button20);
        book=findViewById(R.id.button21);
        map=findViewById(R.id.button22);
        userenteredarea=findViewById(R.id.editTextText11);
        na=findViewById(R.id.textView33);
        cty=findViewById(R.id.textView34);
        ph=findViewById(R.id.textView35);
        fav=findViewById(R.id.textView36);
        ppr=findViewById(R.id.textView37);
        dpr=findViewById(R.id.textView38);
        lpr=findViewById(R.id.textView39);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String bbname=na.getText().toString();
                Intent intent=new Intent(SearchBunk.this, BookBunk.class);
                intent.putExtra("bbname",bbname);
                startActivity(intent);


            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SearchBunk.this, Mapactivity.class);
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barea=userenteredarea.getText().toString();
                if(!barea.isEmpty()){
                    searchBunk(barea);
                }else{
                    Toast.makeText(SearchBunk.this, "Please Enter Area", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void searchBunk(String barea) {
        reference= FirebaseDatabase.getInstance().getReference("Bunk");
        reference.child(barea).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        Toast.makeText(SearchBunk.this, "Successfully Read", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot=task.getResult();
                        String name=String.valueOf(dataSnapshot.child("bname").getValue());
                        String city=String.valueOf(dataSnapshot.child("bcity").getValue());
                        String phone=String.valueOf(dataSnapshot.child("bphone").getValue());
                        String fuelavailable=String.valueOf(dataSnapshot.child("bfavailable").getValue());
                        String petrolprice=String.valueOf(dataSnapshot.child("bpprice").getValue());
                        String dieselprice=String.valueOf(dataSnapshot.child("bdprice").getValue());
                        String lpgprice=String.valueOf(dataSnapshot.child("blprice").getValue());
                        na.setText(name);
                        cty.setText(city);
                        ph.setText(phone);
                        fav.setText(fuelavailable);
                        ppr.setText(petrolprice);
                        dpr.setText(dieselprice);
                        lpr.setText(lpgprice);
                    }else{
                        Toast.makeText(SearchBunk.this, "Bunk Doesn't Exist", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SearchBunk.this, "Failed to read data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}