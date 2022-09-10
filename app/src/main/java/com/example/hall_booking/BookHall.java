package com.example.hall_booking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hall_booking.ui.slideshow.SlideshowFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BookHall extends AppCompatActivity {



    TextView tvDate,hallName;
    EditText name,phone,amount,function,etDate;
    Button book,btn;
    private int aYear,aMonth,aDate;
    FirebaseFirestore fStore;
    String userId,Email;
    FirebaseAuth fAuth;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_hall);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //spinner = findViewById(R.id.spinner);
        hallName = findViewById(R.id.HallName);
        amount = findViewById(R.id.editTextTextPersonName2);


        Intent i = getIntent();
        String h = i.getStringExtra("hName");
        hallName.setText(h);
        String p = i.getStringExtra("hPrice");
        amount.setText(p);


        etDate = findViewById(R.id.editTextTextPersonName5);
        progressBar = findViewById(R.id.progressBar4);


        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar = Calendar.getInstance();
                aYear = calendar.get(Calendar.YEAR);
                aMonth = calendar.get(Calendar.MONTH);
                aDate = calendar.get(Calendar.DATE);
                DatePickerDialog dialog = new DatePickerDialog(BookHall.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
//                        date.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                        etDate.setText(date+"/"+(month+1)+"/"+year);
                    }
                },aYear,aMonth,aDate);
                dialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
//                dialog.getDatePicker().setMaxDate(System.currentTimeMillis()+(1000*60*24*60*60));
//                dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis()+(1000*90*24*60*60));
                dialog.show();
            }
        });


        name = findViewById(R.id.editTextTextPersonName);
        phone = findViewById(R.id.editTextTextPersonName4);

        function = findViewById(R.id.editTextTextPersonName3);
//        dates = findViewById(R.id.date);
        book = findViewById(R.id.button2);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();



        DocumentReference dr = fStore.collection("user").document(userId);
        dr.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                name.setText(value.getString("Name"));
                Email = value.getString("Email");
                phone.setText(value.getString("Contact_No"));


            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseUser user = fAuth.getCurrentUser();
                String uid = user.getUid();
                String id = fStore.collection("user").document(user.getUid()).collection("Booking").document().getId();
                DocumentReference df = fStore.collection("user").document(user.getUid()).collection("Booking").document(id);

                Map<String, Object> m = new HashMap<String, Object>();
                m.put("Name",name.getText().toString());
                m.put("ContactNo",phone.getText().toString());
                m.put("Amount",amount.getText().toString());
                m.put("Function",function.getText().toString());
                m.put("Date",etDate.getText().toString());
                m.put("HallName",hallName.getText().toString());
                m.put("Email",Email);
                m.put("ID",id);

                df.set(m).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        String n = name.getText().toString();
                        if(TextUtils.isEmpty(n)){
                            name.setError("Name is required");
                            return;
                        }
                        String date = etDate.getText().toString();
                        if(TextUtils.isEmpty(date)){
                            etDate.setError("Date  is required");
                            return;
                        }
                        String p = phone.getText().toString();
                        if(TextUtils.isEmpty(p)){
                            phone.setText("Contact Number is required");
                            return;
                        }
                        if(p.length() != 10){
                            phone.setError("Enter Proper Contact number");
                            return;
                        }


                        Toast.makeText(BookHall.this, "Booking Successfully done !!!", Toast.LENGTH_SHORT).show();

                        final Dialog dialogBox = new Dialog(BookHall.this);
                        dialogBox.setContentView(R.layout.dialog);
                        dialogBox.setCancelable(false);

                        Button btn = dialogBox.findViewById(R.id.btnOkay);
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i = new Intent(BookHall.this, Booking.class);
                                startActivity(i);
                                dialogBox.dismiss();
                            }
                        });
                        dialogBox.show();
                        progressBar.setVisibility(View.VISIBLE);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });





//
//                FirebaseDatabase.getInstance().getReference().child("USERS").push() .child("subUsers").push().setValue(m);
//                Toast.makeText(BookHall.this, "Booking Successfully Done!!!", Toast.LENGTH_SHORT).show();
//                HashMap<String, Object> m = new HashMap<String, Object>();
//                m.put("Name", name.getText().toString());
//                m.put("ContactNo", phone.getText().toString());
//                m.put("Amount", amount.getText().toString());
//                m.put("Function", function.getText().toString());
//                m.put("Date", etDate.getText().toString());
//                m.put("Hall Name", amount.getText().toString());
//                m.put("Email", Email);
//                FirebaseDatabase.getInstance().getReference().child("USERS").push().setValue(m);
//                Toast.makeText(BookHall.this, "Booking Successfully Done!!!", Toast.LENGTH_SHORT).show();

//
//                DocumentReference documentReference = fStore.collection("user").document(userId).collection("Book").document();
//                HashMap<String , Object> m = new HashMap<String, Object>();
//                m.put("Name",name.getText().toString());
//                m.put("ContactNo",phone.getText().toString());
//                m.put("Amount",amount.getText().toString());
//                m.put("Function",function.getText().toString());
//                m.put("Date",etDate.getText().toString());
//                m.put("Hall Name",amount.getText().toString());
//                m.put("Email",Email);
//
//                dr.set(m).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                    }
//                });
            }
        });

    }
}

//    public void onClick(View view) {
//                HashMap<String , Object> m = new HashMap<String, Object>();
//                m.put("Name",name.getText().toString());
//                m.put("ContactNo",phone.getText().toString());
//                m.put("Amount",amount.getText().toString());
//                m.put("Function",function.getText().toString());
//                m.put("Date",etDate.getText().toString());
//                m.put("Hall Name",amount.getText().toString());
//                m.put("Email",Email);
//
//                FirebaseDatabase.getInstance().getReference().child("USERS").push() .child("subUsers").push().setValue(m);
//                Toast.makeText(BookHall.this, "Booking Successfully Done!!!", Toast.LENGTH_SHORT).show();
//            }




//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(BookHall.this, android.R.layout.simple_spinner_item,price);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);




//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String str = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(BookHall.this, str, Toast.LENGTH_SHORT).show();
//            }
//
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
