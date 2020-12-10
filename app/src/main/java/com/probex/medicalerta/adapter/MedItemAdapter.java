package com.probex.medicalerta.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.probex.medicalerta.R;
import com.probex.medicalerta.model.AppUtils;

import java.util.List;

public class MedItemAdapter extends RecyclerView.Adapter<MedItemAdapter.MyViewHolder> {
    private List<Historico> historicos ;
    private Context context;


    public MedItemAdapter(List<Historico> historicos, Context context) {
        this.historicos = historicos;
        this.context = context;
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
        String draw = "ic_pic" + historico.getIndicacao();

        holder.name.setText(historico.getProduto());
        holder.dateStart.setText(AppUtils.getDateFromCalendar(AppUtils.convertMillisToCalendar(historico.getData_inicial())));
        holder.iconMedHistory.setImageDrawable(getDrawable(context,draw));
        if (historico.getData_final() == 0) {
            holder.dateEnd.setText("Término: Indeterminado");
        } else {
            holder.dateEnd.setText(AppUtils.getDateFromCalendar(AppUtils.convertMillisToCalendar(historico.getData_final())));
        }

    }

    @Override
    public int getItemCount() {
        int count = historicos.size();
        return count;
    }

    public static Drawable getDrawable(Context c, String ImageName) {
        return c.getResources().getDrawable(c.getResources().getIdentifier(ImageName, "drawable", c.getPackageName()));
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
