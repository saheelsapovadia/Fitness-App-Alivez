package com.example.bottomnav;

import android.content.Context;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class MyAdapterTrainerProfile extends RecyclerView.Adapter<MyAdapterTrainerProfile.MyViewHolder> {

    List<TrainerProfileModel> mList;
    Context context;

    public MyAdapterTrainerProfile(List<TrainerProfileModel> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView1,textView2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.clientimg);
            textView1 = itemView.findViewById(R.id.clientname);
            textView2 = itemView.findViewById(R.id.satus);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView.setImageResource(mList.get(position).getClientimg());
        holder.textView1.setText(mList.get(position).getClientname());
        holder.textView2.setText(mList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



}
