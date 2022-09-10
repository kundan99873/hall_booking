package com.example.hall_booking.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.hall_booking.About;
import com.example.hall_booking.Booking;
import com.example.hall_booking.Hall;
import com.example.hall_booking.MainActivity;
import com.example.hall_booking.R;
import com.example.hall_booking.databinding.FragmentHomeBinding;
import com.example.hall_booking.profile;
import com.example.hall_booking.ui.gallery.GalleryFragment;
import com.example.hall_booking.ui.slideshow.SlideshowFragment;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    ViewFlipper flipper;
    CardView cardView,cv,lg,book;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        int imgArray[] = {R.drawable.hall,R.drawable.hall2,R.drawable.hall3,R.drawable.hall4};
        flipper = root.findViewById(R.id.flipper);
        for(int i=0;i<imgArray.length;i++)
            showImg(imgArray[i]);

        cv = root.findViewById(R.id.Location);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Hall.class);
                startActivity(i);
            }
        });

        book = root.findViewById(R.id.booking);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(getActivity(), Booking.class);
                startActivity(i2);
            }
        });

        lg = root.findViewById(R.id.about);
        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), About.class);

                startActivity(i);
            }
        });

        cardView = root.findViewById(R.id.profile);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(getActivity(), profile.class);
                startActivity(i);


            }

        });


        return root;
    }
    public void showImg(int img){
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(img);
        flipper.addView(imageView);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);
        flipper.setInAnimation(getContext(), android.R.anim.slide_in_left);
        flipper.setOutAnimation(getContext(), android.R.anim.slide_out_right);
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}
//                GalleryFragment f = new GalleryFragment();
//                FragmentManager fm = getFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                ft.replace(R.id.myHome,f);
//                ft.commit();



//                Fragment gf = new GalleryFragment();
//                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.myHome , gf).commit();


//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);