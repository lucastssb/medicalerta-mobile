package com.probex.medicalerta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.probex.medicalerta.R;
import com.probex.medicalerta.activity.NotificationsActivity;
import com.probex.medicalerta.database.BancoDadosMed;

import java.util.List;
import java.util.concurrent.Callable;

public class NotificationItemAdapter extends RecyclerView.Adapter<NotificationItemAdapter.MyViewHolder> {
    private List<Notificacao> notificacoes;
    private BancoDadosMed bancoDadosMed;
    private Context context;
    Callable<Void> callable;

    public NotificationItemAdapter(List<Notificacao> notificacoes, Context context, Callable<Void> callable) {
        this.notificacoes = notificacoes;
        this.context = context;
        this.callable = callable;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_item, parent, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Notificacao notificacao = notificacoes.get(position);
        holder.notification_title.setText(notificacao.getNome());
        holder.notification_description.setText(notificacao.getDescricao());
        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bancoDadosMed = new BancoDadosMed(context);
                bancoDadosMed.apagarNotificacao(notificacao);
                bancoDadosMed.close();
                try {
                    callable.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        int amount = notificacoes.size();
        return amount;
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
