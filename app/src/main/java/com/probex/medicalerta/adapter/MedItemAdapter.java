package com.probex.medicalerta.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.probex.medicalerta.R;

public class MedItemAdapter extends RecyclerView.Adapter<MedItemAdapter.MyViewHolder> {

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.med_item, parent, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText("Dipirona");
        holder.dateStart.setText("12/18/2020");
        holder.dateEnd.setText("22/18/2020");
        holder.timeFirst.setText("06:00");
        holder.timeSecond.setText("12:00");
        holder.timeThird.setText("18:00");
        holder.timeFourth.setText("18:00");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView dateStart;
        private TextView dateEnd;
        private TextView timeFirst;
        private TextView timeSecond;
        private TextView timeThird;
        private TextView timeFourth;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            dateStart = itemView.findViewById(R.id.dateStart);
            dateEnd = itemView.findViewById(R.id.dateEnd);
            timeFirst = itemView.findViewById(R.id.timeFirst);
            timeSecond = itemView.findViewById(R.id.timeSecond);
            timeThird = itemView.findViewById(R.id.timeThird);
            timeFourth = itemView.findViewById(R.id.timeFourth);

        }
    }
}
