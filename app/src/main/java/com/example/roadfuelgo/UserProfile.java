package com.example.roadfuelgo;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.example.roadfuelgo.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UserProfile extends AppCompatActivity {
    private Button submit;
    int year, month,day;

    private EditText na;
    private EditText gend;
    private EditText dobb;
    private EditText ph;
    private EditText cty;
    //ActivityMainBinding binding;
    String name,gender,dob,number,city;
    FirebaseDatabase db;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        //binding=ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView((binding.getRoot()));
        submit=findViewById(R.id.button14);
        dobb=findViewById(R.id.editTextTextPersonName7);
        gend=findViewById(R.id.editTextTextPersonName5);
        na=findViewById(R.id.editTextTextPersonName4);
        ph=findViewById(R.id.editTextTextPersonName8);
        cty=findViewById(R.id.editTextTextPersonName9);
        Calendar calendar=Calendar.getInstance();
        dobb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(UserProfile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        dobb.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                    }
                },year,month,day);
                //datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis()-1000);
                datePickerDialog.show();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=na.getText().toString();
                gender=gend.getText().toString();
                dob=dobb.getText().toString();
                number=ph.getText().toString();
                city=cty.getText().toString();
                if(!name.isEmpty())
                {
                    db=FirebaseDatabase.getInstance();
                    reference=db.getReference("Users");
                    reference.child(name).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                UserP user = snapshot.getValue(UserP.class);
                                if (user != null) {
                                    na.setText(user.getName());
                                    gend.setText(user.getGender());
                                    dobb.setText(user.getDob());
                                    ph.setText(user.getPhone());
                                    cty.setText(user.getCity());
                                }
                                Toast.makeText(UserProfile.this, "Already registered", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(UserProfile.this, "Database error", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                if(!name.isEmpty()&&!gender.isEmpty()&&!dob.isEmpty()&&!number.isEmpty()&&!city.isEmpty()){
                    //UserP us=new UserP(name,gender,dob,number,city);
                    db=FirebaseDatabase.getInstance();
                    reference=db.getReference("Users");
                    reference.child(name).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                UserP user = snapshot.getValue(UserP.class);
                                if (user != null) {
                                    na.setText(user.getName());
                                    gend.setText(user.getGender());
                                    dobb.setText(user.getDob());
                                    ph.setText(user.getPhone());
                                    cty.setText(user.getCity());
                                }
                                Toast.makeText(UserProfile.this, "Already registered", Toast.LENGTH_SHORT).show();

                            }else{
                                UserP us=new UserP(name,gender,dob,number,city);
                                reference.child(name).setValue(us).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        na.setText("");
                                        gend.setText("");
                                        dobb.setText("");
                                        ph.setText("");
                                        cty.setText("");
                                        Toast.makeText(UserProfile.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(UserProfile.this, "Database error", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });
//        reference.child(name).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    UserP user = snapshot.getValue(UserP.class);
//                    if (user != null) {
//                        na.setText(user.getName());
//                        gend.setText(user.getGender());
//                        dobb.setText(user.getDob());
//                        ph.setText(user.getPhone());
//                        cty.setText(user.getCity());
//                    }
//                    Toast.makeText(UserProfile.this, "Already registered", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // Handle database error
//                Toast.makeText(UserProfile.this, "Database error", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}