package com.probex.medicalerta.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.probex.medicalerta.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    public void openAlarmListActivity(View view) {
        startActivity(new Intent(this, AlarmListActivity.class));
    }
    public void openHistoryListActivity(View view) {
        startActivity(new Intent(this, HistoryActivity.class));
    }
}