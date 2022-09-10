package com.example.hall_booking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class editProfile extends AppCompatActivity {

    EditText etName,etPassword,etContact,etMail;
    Button button;
    FirebaseFirestore fStore;
    String userId;
    FirebaseAuth fAuth;
    String name,pass,mob,chEmail;
    ProgressBar progressBar;
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//    ProfileUpdate profileUpdate =  getIntent().getSerializableExtra("profileUpdate");
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        etName = findViewById(R.id.updateName);
//        etPassword = findViewById(R.id.updatePass);
        etContact = findViewById(R.id.updateContact);
        etMail = findViewById(R.id.updateMail);

        DocumentReference dr = fStore.collection("user").document(userId);
        dr.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                etName.setText(value.getString("Name"));
                etMail.setText(value.getString("Email"));
                etContact.setText(value.getString("Contact_No"));
//                etPassword.setText(value.getString("Password"));

            }
        });



        button = findViewById(R.id.buttonUpdate);
        progressBar = findViewById(R.id.progressBar3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
//                pass = etPassword.getText().toString();
                mob = etContact.getText().toString();
                chEmail = etMail.getText().toString();

                if(TextUtils.isEmpty(name)){
                    etName.setError("Name is required");
                    return;

                }else if(TextUtils.isEmpty(mob)){
                    etContact.setError("Contact Number is required");
                    return;
                }
                if(mob.length() != 10){
                    etContact.setError("Enter Proper Contact number");
                    return;
                }
                else{
                    UpdateData(name,mob,chEmail);
                }
                Intent intent = new Intent(editProfile.this,profile.class);
                startActivity(intent);
                progressBar.setVisibility(View.VISIBLE);
            }

            private void UpdateData(String name, String mob, String chEmail) {
                Map<String,Object> user = new HashMap<>();
                user.put("Name",name);
                user.put("Email",chEmail);
                user.put("Contact_No",mob);

                dr.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG,"onSuccess : User profile is created for "+userId);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG,"onFailure "+e.toString());
                    }
                });
            }
        });


    }
}