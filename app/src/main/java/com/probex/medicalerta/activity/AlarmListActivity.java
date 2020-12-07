package com.probex.medicalerta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.probex.medicalerta.R;
import com.probex.medicalerta.adapter.AlarmItemAdapter;
import com.probex.medicalerta.adapter.Alarme;
import com.probex.medicalerta.adapter.Medicamento;
import com.probex.medicalerta.database.BancoDadosMed;
import com.probex.medicalerta.model.Alarm;
import com.probex.medicalerta.model.AppUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class AlarmListActivity extends AppCompatActivity {
    private BancoDadosMed bancoDadosMed;
    private List<Alarme> listaAlarmes = new ArrayList<Alarme>();
    private List<Alarm> alarms = new ArrayList<Alarm>();
    private Medicamento medicamento;

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
        this.setUpAlarms();

        AlarmItemAdapter adapter = new AlarmItemAdapter( alarms );
        recyclerAlarmList.setAdapter( adapter );
    }

    public void setUpAlarms() {
        bancoDadosMed = new BancoDadosMed(this);
        listaAlarmes = bancoDadosMed.listaTodosAlarmes();
        bancoDadosMed.close();

        for (Alarme alarme:listaAlarmes
             ) {
            long nextAlarmInMillis;
            medicamento = bancoDadosMed.selecionarMedicamento(alarme.getId_med());

            if(alarme.getUltimo_alarme() == 0) {
                nextAlarmInMillis = alarme.getData_inicial();
            }else {
                nextAlarmInMillis = alarme.getUltimo_alarme();
            }

            long nextAlarm = nextAlarmInMillis + 24 / alarme.getIntervalo() * 3600000;


            alarms.add(new Alarm(medicamento.getProduto(), medicamento.getIndicacao(), nextAlarm, alarme.getIntervalo(), alarme.getData_inicial(), alarme.getData_final()));
        }
    }


    public void onBackPressed(){ //Botão BACK padrão do android
        startActivity(new Intent(this, MainActivity.class)); //O efeito ao ser pressionado do botão (no caso abre a activity)
        finishAffinity(); //Método para matar a activity e não deixa-lá indexada na pilhagem
        return;
    }

    public void voltar(View view) {
        finish();
        overridePendingTransition(0, R.anim.mover_direita);
    }
}