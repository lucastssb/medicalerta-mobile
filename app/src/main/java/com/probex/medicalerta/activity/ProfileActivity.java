package com.probex.medicalerta.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.probex.medicalerta.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void openHistoryListActivity(View view) {
        startActivity(new Intent(this, HistoryActivity.class));
    }

    public void openNotificationActivity(View view) {
        startActivity(new Intent(this, NotificationsActivity.class));
    }

    public void openCreateUserActivity(View view) {
        startActivity(new Intent(this, CreateUserActivity.class));
    }

    public void voltar(View view) {
        finish();
        overridePendingTransition(0, R.anim.mover_direita);
    }
}