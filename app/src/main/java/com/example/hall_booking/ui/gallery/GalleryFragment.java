package com.example.hall_booking.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hall_booking.Hall;
import com.example.hall_booking.R;
import com.example.hall_booking.databinding.FragmentGalleryBinding;
import com.example.hall_booking.editProfile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class GalleryFragment extends Fragment {
    private Button edit;
    TextView first,email,name,contact,hEmail;
    private FragmentGalleryBinding binding;

//    TextView tv;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        toast = root.findViewById(R.id.toast);
//        toast.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
//            }
//        });

        edit = root.findViewById(R.id.edit);
        first = root.findViewById(R.id.textView2);
        hEmail = root.findViewById(R.id.textView3);
        name = root.findViewById(R.id.textView4);
        email = root.findViewById(R.id.textView5);
        contact = root.findViewById(R.id.textView8);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        DocumentReference dr = fStore.collection("user").document(userId);
        dr.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                name.setText(value.getString("Name"));
                email.setText(value.getString("Email"));
                contact.setText(value.getString("Contact_No"));
                hEmail.setText(value.getString("Email"));
                first.setText(value.getString("Name"));
            }
        });


        edit = root.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), editProfile.class);
                startActivity(i);
            }
        });




//        final TextView textView = binding.;
//        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
//    public void fetData(){
//        DocumentReference dr = FirebaseFirestore.getInstance().collection("user").document();
//        dr.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                if(documentSnapshot.exists()){
//
//                }
//                else{
//                    Log.d(TAG,"Failed to fetch data");
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d(TAG,"Failed to fetch data");
//            }
//        })
//    }
}