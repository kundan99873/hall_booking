package com.example.hall_booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText aName, aPswd, aEmail, aContact;
    Button button;

    FirebaseFirestore fStore;
    String userID;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aName = findViewById(R.id.name);
        aPswd = findViewById(R.id.name4);
        aEmail = findViewById(R.id.name3);
        aContact = findViewById(R.id.name2);

        button = findViewById(R.id.button);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

//        if(fAuth.getCurrentUser() !=null){
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//            finish();
//        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = aEmail.getText().toString().trim();
                String pswd = aPswd.getText().toString().trim();
                final String name = aName.getText().toString();
                final String contact = aContact.getText().toString();

                if(TextUtils.isEmpty(name)){
                    aName.setError("Name is required");
                    return;
                }
                if(TextUtils.isEmpty(pswd)){
                    aPswd.setError("Password is required");
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    aEmail.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(contact)){
                    aContact.setError("Contact Number is required");
                    return;
                }


                if(pswd.length() < 6){
                    aPswd.setError("Password must have minimum 6 character");
                    return;
                }

                if(contact.length() != 10){
                    aContact.setError("Enter Proper Contact number");
                    return;
                }




                fAuth.createUserWithEmailAndPassword(email,pswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser fUser = fAuth.getCurrentUser();
                            fUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(), "Register Successful", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"OnFailure : Email Not Sent"+e.getMessage());
                                }
                            });

                            Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference dr = fStore.collection("user").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Name",name);
                            user.put("Email",email);
                            user.put("Contact_No",contact);

                            dr.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"onSuccess : User profile is created for "+userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"onFailure "+e.toString());
                                }
                            });

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{
                            Toast.makeText(Register.this, "Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}