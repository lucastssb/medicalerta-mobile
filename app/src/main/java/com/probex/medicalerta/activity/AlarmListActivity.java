package com.probex.medicalerta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.probex.medicalerta.R;
import com.probex.medicalerta.adapter.AlarmItemAdapter;

public class AlarmListActivity extends AppCompatActivity {

    private RecyclerView recyclerAlarmList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);

        recyclerAlarmList = findViewById(R.id.recyclerAlarmList);

        //Define layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerAlarmList.setLayoutManager( layoutManager );

        //Define adapter
        AlarmItemAdapter adapter = new AlarmItemAdapter();
        recyclerAlarmList.setAdapter( adapter );
    }

    public void onBackPressed(){ //Botão BACK padrão do android
        startActivity(new Intent(this, MainActivity.class)); //O efeito ao ser pressionado do botão (no caso abre a activity)
        finishAffinity(); //Método para matar a activity e não deixa-lá indexada na pilhagem
        return;
    }
}