package com.example.hall_booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class forgot extends AppCompatActivity {

    EditText eMail;
    Button btn;
    ProgressBar pBar;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        eMail = findViewById(R.id.txtEmail);
        btn = findViewById(R.id.button3);
        pBar = findViewById(R.id.progressBar5);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = eMail.getText().toString();
                fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(forgot.this, "Email sent", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(forgot.this,MainActivity.class));
                        pBar.setVisibility(View.VISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(forgot.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                startActivity(new Intent(forgot.this,MainActivity.class));

            }
        });
    }
}