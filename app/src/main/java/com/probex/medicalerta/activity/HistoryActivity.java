package com.probex.medicalerta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.probex.medicalerta.R;
import com.probex.medicalerta.adapter.Alarme;
import com.probex.medicalerta.adapter.Historico;
import com.probex.medicalerta.adapter.MedItemAdapter;
import com.probex.medicalerta.adapter.Medicamento;
import com.probex.medicalerta.database.BancoDadosMed;
import com.probex.medicalerta.model.Alarm;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private BancoDadosMed bancoDadosMed;
    private List<Historico> historicos = new ArrayList<Historico>();
    private List<Alarm> alarms = new ArrayList<>();
    private Medicamento medicamento;

    private RecyclerView recyclerMedList;
    private Button mDatePickerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerMedList = findViewById(R.id.recyclerMedList);

        mDatePickerBtn = findViewById(R.id.startDatePicker);

        //Define layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerMedList.setLayoutManager( layoutManager );

        //Define adapter
        //this.setUpAlarms();

        bancoDadosMed = new BancoDadosMed(this);
        historicos = bancoDadosMed.listaTodosHistorico();
        bancoDadosMed.close();

        MedItemAdapter adapter = new MedItemAdapter( historicos );
        recyclerMedList.setAdapter( adapter );

        //Material date picker
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Início");
        final MaterialDatePicker materialDatePicker = builder.build();

        mDatePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });
    }

    public void setUpAlarms() {
        bancoDadosMed = new BancoDadosMed(this);
        historicos = bancoDadosMed.listaTodosHistorico();

        for (Historico historico: historicos
        ) {
            System.out.println("Indicação: " + historico.getIndicacao());
            //long nextAlarm = historico.get() + 24 / alarme.getIntervalo() * 3600000;

        }
    }

    public void voltar(View view) {
        finish();
        overridePendingTransition(0, R.anim.mover_direita);
    }

}