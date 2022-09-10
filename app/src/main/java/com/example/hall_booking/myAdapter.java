package com.example.hall_booking;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.view.ContentInfoCompat;
import androidx.recyclerview.widget.RecyclerView;

//import com.google.firebase.firestore.auth.User;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.List;


//public class myAdapter {
//    class myviewholder extends RecyclerView.ViewHolder
//    {
//        TextView hallName,hallDate,hallAmount;
//        public myviewholder(@NonNull View itemView) {
//            super(itemView);
//            hallName = itemView.findViewById(R.id.HallListName);
//            hallDate = itemView.findViewById(R.id.dateList);
//            hallAmount = itemView.findViewById(R.id.nameList);
//        }
//    }
//}

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder>{


    ArrayList<User> list;
    TextView hName,hDate,hAmount;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;




    public myAdapter(ArrayList<User> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public myAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.hallName.setText(user.getHallName());
        holder.hallDate.setText(user.getDate());
        holder.hallAmount.setText(user.getAmount());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                        final Dialog d = new Dialog(holder.hallName.getContext());
                        d.setContentView(R.layout.confirm_dialog);
                        d.setCancelable(false);
                        Button yes = d.findViewById(R.id.btn_yes);
                        Button no = d.findViewById(R.id.btn_no);

                        yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                fAuth = FirebaseAuth.getInstance();
                                fStore = FirebaseFirestore.getInstance();
                                String userId = fAuth.getCurrentUser().getUid();
                                String id = user.getID();

                                FirebaseFirestore.getInstance().collection("user").document(userId).collection("Booking").document(id).delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(holder.hallName.getContext(), "Booking Cancelled Successfully", Toast.LENGTH_SHORT).show();
                                                notifyItemRemoved(position);
                                            }
                                        });

                                Intent i2 = new Intent(holder.hallName.getContext(),Booking.class);
                                holder.hallName.getContext().startActivity(i2);
                                d.dismiss();
                            }

                        });
                        no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//                                Intent in = new Intent(holder.hallName.getContext(),Booking.class);
//                                holder.hallName.getContext().startActivity(in);
                                d.dismiss();
                            }
                        });
                        d.show();
//                    }
//                });
//                dialog.show();
//
           }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView hallName,hallDate,hallAmount;
        CardView cv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hallName = itemView.findViewById(R.id.HallListName);
            hallDate = itemView.findViewById(R.id.dateList);
            hallAmount = itemView.findViewById(R.id.nameList);
            cv = itemView.findViewById(R.id.cardView);
        }
    }
}

//                                String id = fStore.collection("user").document(user.getUid()).collection("Booking").document().getId();
//                                FirebaseFirestore.getInstance().collection("user").document(fUser.getUid()).collection("Booking").whereEqualTo("HallName",user.getHallName()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                                    @Override
//                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                                        WriteBatch b = FirebaseFirestore.getInstance().batch();
//                                        List<DocumentSnapshot> s=queryDocumentSnapshots.getDocuments();
//                                        for (DocumentSnapshot snapshot:s){
//                                            b.delete(snapshot.getReference());
//                                        }
//                                        b.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
//                                            @Override
//                                            public void onSuccess(Void unused) {
//                                                Toast.makeText(holder.hallName.getContext(),"Booking Cancel",Toast.LENGTH_SHORT).show();
//                                                Intent i = new Intent(holder.hallName.getContext(), Booking.class);
//                                                holder.hallName.getContext().startActivity(i);
//                                                dialog.dismiss();
//                                            }
//                                        });
//                                    }
//                                });

//                final Dialog dialog = new Dialog(holder.hallName.getContext());
//                dialog.setContentView(R.layout.bookingdialog);
//                dialog.setCancelable(false);
//
//                hName = dialog.findViewById(R.id.txtHAllName);
//                hDate = dialog.findViewById(R.id.txtHallDate);
//                hAmount = dialog.findViewById(R.id.txtHallAmount);
//                hName.setText(user.getHallName());
//                hDate.setText(user.getDate());
//                hAmount.setText(user.getAmount());
//
//                Button btn = dialog.findViewById(R.id.btnOkay1);
//                btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent i = new Intent(holder.hallName.getContext(), Booking.class);
//                        holder.hallName.getContext().startActivity(i);
//                        dialog.dismiss();
//                    }
//                });
//                Button button = dialog.findViewById(R.id.btnCancel);
//                button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {

