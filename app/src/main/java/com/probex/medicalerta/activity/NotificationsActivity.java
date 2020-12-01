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
import com.probex.medicalerta.adapter.NotificationItemAdapter;

public class NotificationsActivity extends AppCompatActivity {

    RecyclerView recycler_notification_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        recycler_notification_list = findViewById(R.id.recycler_notification_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_notification_list.setLayoutManager(layoutManager);

        NotificationItemAdapter notificationItemAdapter = new NotificationItemAdapter();
        recycler_notification_list.setAdapter(notificationItemAdapter);
    }

    public void voltar(View view) {
        finish();
        overridePendingTransition(0, R.anim.mover_direita);
    }
}