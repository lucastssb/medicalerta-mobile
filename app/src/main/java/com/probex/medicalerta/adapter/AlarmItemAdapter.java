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

public class AlarmItemAdapter extends RecyclerView.Adapter<AlarmItemAdapter.MyViewHolder> {
    public List<Alarm> alarms;

    public AlarmItemAdapter(List<Alarm> alarms) {
        this.alarms = alarms;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alarm_item_details, parent, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Alarm alarm = alarms.get(position);
        holder.name.setText(alarm.getMedName());
        holder.interval.setText(Integer.toString(alarm.getInterval()) + " vezes ao dia");
        holder.nextAlarm.setText("Próximo alarme: " + alarm.getNextAlarmTime());
        holder.finDate.setText("Término: " + alarm.getFinalDate());
        holder.initDate.setText("Início: " + alarm.getInitialDate());

    }


    @Override
    public int getItemCount() {
        int amount = alarms.size();
        return amount;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView interval;
        private TextView initDate;
        private TextView finDate;
        private TextView nextAlarm;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.medName);
            interval = itemView.findViewById(R.id.interval);
            initDate = itemView.findViewById(R.id.initDate);
            finDate = itemView.findViewById(R.id.finDate);
            nextAlarm = itemView.findViewById(R.id.nextAlarm);
        }
    }
}
