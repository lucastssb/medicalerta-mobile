package com.probex.medicalerta.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;


import com.probex.medicalerta.R;

import java.util.Date;

public class AlarmActivity extends AppCompatActivity {
    private TextView textName, textHour;

    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setShowWhenLocked(true);
        setTurnScreenOn(true);
        setContentView(R.layout.activity_alarm);

        textHour = findViewById(R.id.hour);
        textName = findViewById(R.id.nameMed);

        Bundle data = getIntent().getExtras();
        String medNome = data.getString("Nome");
        int idMed = data.getInt("Id");
        String hora = data.getString("Hora");

        textName.setText(medNome);
        textHour.setText(hora);
    }
}