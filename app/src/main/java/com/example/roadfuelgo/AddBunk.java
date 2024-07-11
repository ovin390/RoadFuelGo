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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBunk extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference reference;
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
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bunk);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        bna=findViewById(R.id.editTextTextPersonName11);
        bar=findViewById(R.id.editTextTextPersonName12);
        bland=findViewById(R.id.editTextTextPersonName13);
        bcty=findViewById(R.id.editTextTextPersonName14);
        bpn=findViewById(R.id.editTextTextPersonName15);
        bph=findViewById(R.id.editTextTextPersonName16);
        bfavl=findViewById(R.id.editTextTextPersonName17);
        bpprc=findViewById(R.id.editTextTextPersonName18);
        bdprc=findViewById(R.id.editTextTextPersonName19);
        blprc=findViewById(R.id.editTextTextPersonName20);
        add=findViewById(R.id.button16);
        add.setOnClickListener(new View.OnClickListener() {
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
                if(!bname.isEmpty()&&!barea.isEmpty()&&!blandmark.isEmpty()&&!bcity.isEmpty()&&!bpin.isEmpty()&&!bphone.isEmpty()&&!bfavailable.isEmpty()&&!bpprice.isEmpty()&&!bdprice.isEmpty()&&!blprice.isEmpty()){
                    Addbu ab=new Addbu(bname,barea,blandmark,bcity,bpin,bphone,bfavailable,bpprice,bdprice,blprice);
                    db=FirebaseDatabase.getInstance();
                    reference=db.getReference("Bunk");
                    reference.child(barea).setValue(ab).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
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
                            Toast.makeText(AddBunk.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}