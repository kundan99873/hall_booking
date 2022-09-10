package com.example.hall_booking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hall_booking.R;

public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    String hallList[];
    String loc[];
    int hallImg[];
    LayoutInflater inflater;

    public CustomBaseAdapter(Context ctx,String[] hallList,String[] loc, int[] listImg){
        this.context = ctx;
        this.hallImg = listImg;
        this.hallList = hallList;
        this.loc = loc;
        inflater = LayoutInflater.from(ctx);

    }
    @Override
    public int getCount() {
        return hallList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_item, null);
        TextView txtView = (TextView) view.findViewById(R.id.locationName);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageHall);
        TextView textView = view.findViewById(R.id.message);
        txtView.setText(hallList[i]);
        imageView.setImageResource(hallImg[i]);
        textView.setText(loc[i]);
        return view;
    }
}
