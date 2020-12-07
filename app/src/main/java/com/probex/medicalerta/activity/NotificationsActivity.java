package com.probex.medicalerta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.probex.medicalerta.R;
import com.probex.medicalerta.adapter.Notificacao;
import com.probex.medicalerta.adapter.NotificationItemAdapter;
import com.probex.medicalerta.database.BancoDadosMed;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {
    private List<Notificacao> notificacoes = new ArrayList<Notificacao>();
    private BancoDadosMed bancoDadosMed;

    RecyclerView recycler_notification_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        setUpNotifications();

        recycler_notification_list = findViewById(R.id.recycler_notification_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_notification_list.setLayoutManager(layoutManager);

        NotificationItemAdapter notificationItemAdapter = new NotificationItemAdapter(notificacoes, this);
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

}