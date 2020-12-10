package com.probex.medicalerta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.probex.medicalerta.R;
import com.probex.medicalerta.adapter.Notificacao;
import com.probex.medicalerta.adapter.NotificationItemAdapter;
import com.probex.medicalerta.database.BancoDadosMed;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class NotificationsActivity extends AppCompatActivity {
    private List<Notificacao> notificacoes = new ArrayList<Notificacao>();
    private BancoDadosMed bancoDadosMed;
    private  NotificationItemAdapter notificationItemAdapter;

    RecyclerView recycler_notification_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        setUpNotifications();

        recycler_notification_list = findViewById(R.id.recycler_notification_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_notification_list.setLayoutManager(layoutManager);


        notificationItemAdapter = new NotificationItemAdapter(notificacoes, this, new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                return updateNotifications();
            }
        });
        recycler_notification_list.setAdapter(notificationItemAdapter);
    }

    private void setUpNotifications() {
        bancoDadosMed = new BancoDadosMed(this);
        notificacoes = bancoDadosMed.listaTodasNotificacao();
        bancoDadosMed.close();
    }


    public void voltar(View view) {
        finish();
        overridePendingTransition(0, R.anim.mover_direita);
    }

    public Void updateNotifications() {
        bancoDadosMed = new BancoDadosMed(this);
        notificacoes.clear();
        notificacoes.addAll(bancoDadosMed.listaTodasNotificacao());
        notificationItemAdapter.notifyDataSetChanged();

        return null;
    }
    
}