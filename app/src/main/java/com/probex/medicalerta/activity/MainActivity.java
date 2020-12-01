package com.probex.medicalerta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

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
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.mover_esquerda ,R.anim.fade_out );
        ActivityCompat.startActivity(MainActivity.this, new Intent(MainActivity.this, AddMedication1.class), activityOptionsCompat.toBundle());
    }
    public void openListaMedicamento2(View view) {
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.mover_esquerda ,R.anim.fade_out );
        ActivityCompat.startActivity(MainActivity.this, new Intent(MainActivity.this, ListaMedicamento1.class), activityOptionsCompat.toBundle());
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