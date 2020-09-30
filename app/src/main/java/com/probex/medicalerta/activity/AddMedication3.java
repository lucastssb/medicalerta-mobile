package com.probex.medicalerta.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.probex.medicalerta.R;

public class AddMedication3 extends AppCompatActivity {

    private TextView resposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication3);


        resposta = findViewById(R.id.resposta);

        String value = getIntent().getStringExtra("key");

        resposta.setText(value);
    }
}