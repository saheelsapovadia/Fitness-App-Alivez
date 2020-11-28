package com.example.bottomnav;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrainerWorkoutAdapter extends RecyclerView.Adapter<TrainerWorkoutAdapter.MyViewHolder> {

    List<TrainerWorkoutModel> mList;
    Context context;
    private SelectedUser selectedUser;

    public TrainerWorkoutAdapter(List<TrainerWorkoutModel> mList, Context context, SelectedUser selectedUser) {
        this.mList = mList;
        this.context = context;
        this.selectedUser = selectedUser;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView1,textView2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.clientImg);
            textView1 = itemView.findViewById(R.id.clientName);
            textView2 = itemView.findViewById(R.id.routine_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedUser.selectedUser(mList.get(getAdapterPosition()));
                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_users,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView.setImageResource(mList.get(position).getClientImg());
        holder.textView1.setText(mList.get(position).getClientName());
        holder.textView2.setText(mList.get(position).getRoutine());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface SelectedUser{
        void selectedUser(TrainerWorkoutModel trainerWorkoutModel);
    }

}
