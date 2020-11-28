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

public class manageClientsAdapter extends RecyclerView.Adapter<manageClientsAdapter.MyViewHolder> {

    List<manageClientsModel> mList;
    Context context;
    private SelectedUser selectedUser;

    public manageClientsAdapter(List<manageClientsModel> mList, Context context,SelectedUser selectedUser) {
        this.mList=mList;
        this.context = context;
        this.selectedUser = selectedUser;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.clientImage);
            textView1 = itemView.findViewById(R.id.clientName);

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
        View view = layoutInflater.inflate(R.layout.manage_clients,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView.setImageResource(mList.get(position).getClientImg());
        holder.textView1.setText(mList.get(position).getClientName());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface SelectedUser{
        void selectedUser(manageClientsModel manageClientsModel);
    }

}
