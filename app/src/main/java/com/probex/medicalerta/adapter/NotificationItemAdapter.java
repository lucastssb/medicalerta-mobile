package com.probex.medicalerta.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.probex.medicalerta.R;

public class NotificationItemAdapter extends RecyclerView.Adapter<NotificationItemAdapter.MyViewHolder> {
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_item, parent, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.notification_title.setText("Diazepam");
        holder.notification_description.setText("Você esqueceu de tomar esse remédio as 10:00");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView notification_title, notification_description;
        ImageView delete_button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            notification_title = itemView.findViewById(R.id.notification_title);
            notification_description = itemView.findViewById(R.id.notification_description);
            delete_button = itemView.findViewById(R.id.delete_notification_button);
        }
    }
}
