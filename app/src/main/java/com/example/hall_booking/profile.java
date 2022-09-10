package com.example.hall_booking;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class profile extends AppCompatActivity {

    Button edit, delete;
    TextView first,email,name,contact,hEmail;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edit = findViewById(R.id.editButton);
        first = findViewById(R.id.textView2);
        hEmail =findViewById(R.id.textView3);
        name = findViewById(R.id.textView4);
        email = findViewById(R.id.textView5);
        contact = findViewById(R.id.textView8);



        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        DocumentReference dr = fStore.collection("user").document(userId);
        dr.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                name.setText(value.getString("Name"));
                email.setText(value.getString("Email"));
                contact.setText(value.getString("Contact_No"));
                hEmail.setText(value.getString("Email"));
                first.setText(value.getString("Name"));
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getApplicationContext(), editProfile.class);
                startActivity(i1);
            }
        });

    }
}