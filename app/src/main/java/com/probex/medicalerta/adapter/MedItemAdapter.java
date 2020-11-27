package com.probex.medicalerta.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.probex.medicalerta.R;
import com.probex.medicalerta.model.Alarm;

import java.util.List;

public class MedItemAdapter extends RecyclerView.Adapter<MedItemAdapter.MyViewHolder> {
    private List<Alarm> alarms;

    public MedItemAdapter(List<Alarm> alarms) {
        this.alarms = alarms;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.med_item, parent, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Alarm alarm = alarms.get(position);

        holder.name.setText(alarm.getMedName());
        holder.dateStart.setText(alarm.getInitialDate());
        holder.dateEnd.setText(alarm.getFinalDate());

    }

    @Override
    public int getItemCount() {
        int count = alarms.size();
        return count;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView dateStart;
        private TextView dateEnd;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.medNameHistory);
            dateStart = itemView.findViewById(R.id.dateStartHistory);
            dateEnd = itemView.findViewById(R.id.dateEndHistory);

        }
    }
}
