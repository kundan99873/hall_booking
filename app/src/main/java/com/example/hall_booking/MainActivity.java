package com.example.hall_booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    TextView User;
    EditText aEmail,aPassword;
    Button aButton;
    TextView forget;
    ProgressBar pBar;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        isSignin();
        User = findViewById(R.id.textView);

        User.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                pBar.onVisibilityAggregated(true);
                startActivity(new Intent(MainActivity.this,Register.class));
            }
        });

        aEmail = findViewById(R.id.username);
        aPassword = findViewById(R.id.pswd);
        aButton = findViewById(R.id.button);
        pBar = findViewById(R.id.progressBar2);


        aButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = aEmail.getText().toString().trim();
                String password = aPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    aEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    aEmail.setError("Password is required");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String username = aEmail.getText().toString();
                        Intent intent = new Intent(MainActivity.this,Dashboard.class);
                        intent.putExtra("keyEmail",username);
//                      startActivity(intent);
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "User Login Successful", Toast.LENGTH_SHORT).show();
//
                            startActivity(intent);
                            pBar.setVisibility(View.VISIBLE);
                        }

                        else{
                            Toast.makeText(getApplicationContext(), "Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });

        forget = findViewById(R.id.forgot);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, forgot.class));
            }
        });

    }

    private void isSignin() {
        if(fAuth.getCurrentUser()!=null){
            Intent intent2 = new Intent(MainActivity.this,Dashboard.class);
            startActivity(intent2);
        }
    }

}