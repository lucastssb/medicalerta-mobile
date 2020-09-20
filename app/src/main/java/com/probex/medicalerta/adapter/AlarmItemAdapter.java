package com.probex.medicalerta.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.probex.medicalerta.R;

public class AlarmItemAdapter extends RecyclerView.Adapter<AlarmItemAdapter.MyViewHolder> {

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alarm_item_details, parent, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText("Dipirona");
        holder.interval.setText("6");
        holder.sunRiseTime.setText("06:00");
        holder.dayTime.setText("12:00");
        holder.nightTime.setText("18:00");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView interval;
        private TextView sunRiseTime;
        private TextView dayTime;
        private TextView nightTime;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.medName);
            interval = itemView.findViewById(R.id.interval);
            sunRiseTime = itemView.findViewById(R.id.sunRiseTime);
            dayTime = itemView.findViewById(R.id.dayTime);
            nightTime = itemView.findViewById(R.id.nightTime);
        }
    }
}
