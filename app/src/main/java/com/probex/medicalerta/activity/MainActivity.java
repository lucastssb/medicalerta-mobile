package com.probex.medicalerta.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.probex.medicalerta.R;
import com.probex.medicalerta.services.AlarmService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(getBaseContext(), AlarmService.class));
    }

    public void openAddMedication1(View view) {
        startActivity(new Intent(this, AddMedication1.class));
    }

    public void openAlarmListActivity(View view) {
        startActivity(new Intent(this, AlarmListActivity.class));
    }
    public void openHistoryListActivity(View view) {
        startActivity(new Intent(this, HistoryActivity.class));
    }
    public void openProfileActivity(View view) {
        startActivity(new Intent(this, ProfileActivity.class));
    }
}