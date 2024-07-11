package com.example.roadfuelgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
public class UpadteBunk extends AppCompatActivity {
    DatabaseReference databaseReference;
    String bname,barea,blandmark,bcity,bpin,bphone,bfavailable,bpprice,bdprice,blprice;
    private EditText bna;
    private EditText bar;
    private EditText bland;
    private EditText bcty;
    private EditText bpn;
    private EditText bph;
    private EditText bfavl;
    private EditText bpprc;
    private EditText bdprc;
    private EditText blprc;
    private Button update;
    private Button showData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upadte_bunk);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        bna=findViewById(R.id.editTextText);
        bar=findViewById(R.id.editTextText2);
        bland=findViewById(R.id.editTextText3);
        bcty=findViewById(R.id.editTextText4);
        bpn=findViewById(R.id.editTextText5);
        bph=findViewById(R.id.editTextText6);
        showData=findViewById(R.id.button3);
        bfavl=findViewById(R.id.editTextText7);
        bpprc=findViewById(R.id.editTextText8);
        bdprc=findViewById(R.id.editTextText10);
        blprc=findViewById(R.id.editTextText9);
        update=findViewById(R.id.button19);
        showData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String barea = bar.getText().toString();
                showBunkDetails(barea);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bname=bna.getText().toString();
                barea=bar.getText().toString();
                blandmark=bland.getText().toString();
                bcity=bcty.getText().toString();
                bpin=bpn.getText().toString();
                bphone=bph.getText().toString();
                bfavailable=bfavl.getText().toString();
                bpprice=bpprc.getText().toString();
                bdprice=bdprc.getText().toString();
                blprice=blprc.getText().toString();

                upadteBunk(bname,barea,blandmark,bcity,bpin,bphone,bfavailable,bpprice,bdprice,blprice);
            }
        });
    }

    private void showBunkDetails(String barea) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Bunk");
        databaseReference.child(barea).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Addbu bunk = snapshot.getValue(Addbu.class);
                    if (bunk != null) {
                        bna.setText(bunk.getBname());
                        bland.setText(bunk.getBlandmark());
                        bcty.setText(bunk.getBcity());
                        bpn.setText(bunk.getBpin());
                        bph.setText(bunk.getBphone());
                        bfavl.setText(bunk.getBfavailable());
                        bpprc.setText(bunk.getBpprice());
                        bdprc.setText(bunk.getBdprice());
                        blprc.setText(bunk.getBlprice());
                    }
                }else{
                    Toast.makeText(UpadteBunk.this, "Bunk not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpadteBunk.this, "Database error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void upadteBunk(String bname, String barea, String blandmark, String bcity, String bpin, String bphone, String bfavailable, String bpprice, String bdprice, String blprice){
        HashMap bunk=new HashMap();
        bunk.put("bname",bname);
        bunk.put("blandmark",blandmark);
        bunk.put("bcity",bcity);
        bunk.put("bpin",bpin);
        bunk.put("bphone",bphone);
        bunk.put("bfavailable",bfavailable);
        bunk.put("bpprice",bpprice);
        bunk.put("bdprice",bdprice);
        bunk.put("blprice",blprice);
        databaseReference= FirebaseDatabase.getInstance().getReference("Bunk");
        databaseReference.child(barea).updateChildren(bunk).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    bna.setText("");
                    bar.setText("");
                    bland.setText("");
                    bcty.setText("");
                    bpn.setText("");
                    bph.setText("");
                    bfavl.setText("");
                    bpprc.setText("");
                    bdprc.setText("");
                    blprc.setText("");
                    Toast.makeText(UpadteBunk.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(UpadteBunk.this, "Failed to Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}