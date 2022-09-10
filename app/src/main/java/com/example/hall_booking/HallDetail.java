package com.example.hall_booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class HallDetail extends AppCompatActivity {

    TextView name,loc,cap,cn,price;
    ImageView img;
    Button book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.bookName);
        loc = findViewById(R.id.bookLoc);
        img = findViewById(R.id.hallImage);
        book = findViewById(R.id.bookButton);
        cap = findViewById(R.id.textView14);
        price = findViewById(R.id.textView11);
        cn = findViewById(R.id.textView12);


        Intent intent = getIntent();
        String a = intent.getStringExtra("hallList");
        String b = intent.getStringExtra("loc");

        int c = intent.getIntExtra("hallImage",R.drawable.hall);
        String d = intent.getStringExtra("capCity");
        String e = intent.getStringExtra("Amount");
        String f = intent.getStringExtra("cnNo");
        name.setText(a);
        loc.setText(b);
        img.setImageResource(c);
        price.setText(e);
        cap.setText(d);
        cn.setText(f);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),BookHall.class);
                intent1.putExtra("hName",a);
                intent1.putExtra("hPrice",e);
                startActivity(intent1);
            }
        });


    }
}