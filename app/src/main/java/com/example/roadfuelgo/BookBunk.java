package com.example.roadfuelgo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class BookBunk extends AppCompatActivity {
    String[] fu={"Petrol","Diesel","LPG"};
    Spinner spinner;
    private Button submit;
    FirebaseDatabase db;
    DatabaseReference reference;
    String bunkname,id,custname,phone,fueltype,quantity,location,bdate;
    private EditText bna;
    private EditText bid;
    private EditText na;
    private EditText ph;
   // private EditText ftype;
    private EditText qt;
    private EditText loc;
    private EditText bd;
    int year, month,day;
    String value;

    @Override
    protected void onStart() {
        super.onStart();
        Random random=new Random();
        int r=random.nextInt(1000);
        bid.setText(Integer.toString(r));

    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_bunk);
//        Random random=new Random();
//        int r=random.nextInt(1000);
//        bid.setText(Integer.toString(r));
        spinner=findViewById(R.id.spinner);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        Calendar calendar=Calendar.getInstance();
        submit=findViewById(R.id.button23);
        bna=findViewById(R.id.editTextText12);
        bid=findViewById(R.id.editTextText13);
        na=findViewById(R.id.editTextText14);
        ph=findViewById(R.id.editTextText15);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(BookBunk.this, android.R.layout.simple_spinner_item,fu);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 value=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(BookBunk.this,value,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //ftype=findViewById(R.id.editTextText16);
        qt=findViewById(R.id.editTextText17);
        loc=findViewById(R.id.editTextText18);
        bd=findViewById(R.id.editTextText20);
        bd.setOnClickListener(new View.OnClickListener() {
            //@RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(BookBunk.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        bd.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                    }
                },year,month,day);
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis()-1000);
                datePickerDialog.show();
            }
        });


        String bnname=getIntent().getStringExtra("bbname");
        bna.setText(bnname);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bunkname=bna.getText().toString();
                id=bid.getText().toString();
                custname=na.getText().toString();
                phone=ph.getText().toString();
                fueltype=value;
                quantity=qt.getText().toString();
                location=loc.getText().toString();
                bdate=bd.getText().toString();
                if (!bunkname.isEmpty()&&!id.isEmpty()&&!custname.isEmpty()&&!phone.isEmpty()&&!fueltype.isEmpty()&&!quantity.isEmpty()&&!location.isEmpty()&&!bdate.isEmpty()){
                    bookb bb=new bookb(bunkname,id,custname,phone,fueltype,quantity,location,bdate);
                    db=FirebaseDatabase.getInstance();
                    reference=db.getReference("Bookings");
                    reference.child(id).setValue(bb).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            bna.setText("");
                            bid.setText("");
                            na.setText("");
                            ph.setText("");
                            //ftype.setText("");
                            qt.setText("");
                            loc.setText("");
                            bd.setText("");
                            Toast.makeText(BookBunk.this, "Successfully booked", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });


    }
}