package com.probex.medicalerta.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.probex.medicalerta.R;
import com.probex.medicalerta.model.Alarm;
import com.probex.medicalerta.model.AppUtils;

import java.util.List;

public class MedItemAdapter extends RecyclerView.Adapter<MedItemAdapter.MyViewHolder> {
    private List<Historico> historicos ;


    public MedItemAdapter(List<Historico> historicos) {
        this.historicos = historicos;
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
        Historico historico = historicos.get(position);
        holder.name.setText(historico.getProduto());
        holder.dateStart.setText(AppUtils.getDateFromCalendar(AppUtils.convertMillisToCalendar(historico.getData_inicial())));
        holder.dateEnd.setText(AppUtils.getDateFromCalendar(AppUtils.convertMillisToCalendar(historico.getData_final())));

    }

    @Override
    public int getItemCount() {
        int count = historicos.size();
        return count;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView iconMedHistory;
        private TextView dateStart;
        private TextView dateEnd;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.medNameHistory);
            dateStart = itemView.findViewById(R.id.dateStartHistory);
            dateEnd = itemView.findViewById(R.id.dateEndHistory);
            iconMedHistory = itemView.findViewById(R.id.iconMedHistory);

        }
    }
}
