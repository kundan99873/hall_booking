package com.example.hall_booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;

public class Hall extends AppCompatActivity {
    String hallList[]  = {"Hotel Plaza","Gurukripa Banquets","Acres Banquet","The Royal Orchid","Club Emerald","Balvikas hall","Samaj Mandir Hall","Tamil Sangam Hall","Emperor hall","Sea Princess","Krishna Palate","Occassion Plus","J K Banquet","Malad De Grande","Gracen Hall","Sahara Star","VITS hall","Sumati Banquet Hall","Shanmukhananda Hall","Vitthal Rakhumai Mandir Hall"};
    String loc[] = {"Chembur, Mumbai","fort, Mumbai","Bandra, Mumbai","Andheri, Mumbai","Chembur, Mumbai","Andheri, Mumbai","Sion, Mumbai","King Circle, Mumbai","Kurla ,Mumbai","Gogegoan, Mumbai","Vashi, New Mumbai","Navi Mumbai","Saki Naka, Mumbai","Malad, Mumbai","Panvel","Vile Parle(E), Mumbai","Andheri, Mumbai","Kalyan, Mumbai","King Circle, Mumbai","Dahisar, Mumbai"};
    int hallImg[] = {R.drawable.hall, R.drawable.hall1, R.drawable.hall2, R.drawable.hall3,R.drawable.hall4, R.drawable.hall6,R.drawable.smhall,R.drawable.tyvgcygvtygd,R.drawable.sammlen,R.drawable.tyvgcygvtygd,R.drawable.images,R.drawable.occassionplus,R.drawable.jkbanquet,R.drawable.maladdegrande,R.drawable.grace,R.drawable.images3,R.drawable.images1,R.drawable.images4,R.drawable.shan,R.drawable.images5};

    String capacity[] = {"1,200","1,000","200","300","500","600","200","350","800","500","650","250","500","800","500","750","650","1,000","800","300"};
    String amount[] = {"\u20B9 25,000","\u20B9 50,000","\u20B9 42,000","\u20B9 34,000","\u20B9 62,000","\u20B9 1,00,000","\u20B9 80,000","\u20B9 18,000","\u20B9 35,000","\u20B9 56,000","\u20B9 25,000","\u20B9 46,000","\u20B9 20,000","\u20B9 35,000","\u20B9 78,000","\u20B9 1,10,000","\u20B9 2,05,000","\u20B9 26,000","\u20B9 2,00,000","\u20B9 23,000"};
    String cn[] = {"9876543210","9876543210","9876543210","9876543210","9876543210","9876543210","9876543210","9876543210","9876543210","9876543210","9876543210","9876543210","9876543210","9876543210","9876543210","9876543210","9876543210","9876543210","9876543210","9876543210"};

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv = findViewById(R.id.listView);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(),hallList,loc,hallImg);
        lv.setAdapter(customBaseAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext() , HallDetail.class);
                intent.putExtra("hallList",hallList[i]);
                intent.putExtra("loc",loc[i]);
                intent.putExtra("hallImage",hallImg[i]);
                intent.putExtra("capCity",capacity[i]);
                intent.putExtra("cnNo",cn[i]);
                intent.putExtra("Amount",amount[i]);
                startActivity(intent);
            }
        });
    }

}