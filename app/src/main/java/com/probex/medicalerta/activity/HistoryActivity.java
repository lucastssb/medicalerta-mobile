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
    private List<Alarme> listaAlarmes = new ArrayList<Alarme>();
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
        this.setUpAlarms();

        MedItemAdapter adapter = new MedItemAdapter( alarms );
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
        listaAlarmes = bancoDadosMed.listaTodosAlarmes();

        for (Alarme alarme:listaAlarmes
        ) {
            medicamento = bancoDadosMed.selecionarMedicamento(alarme.getId_med());

            Calendar nextAlarm = convertMillisIntoCalendar(alarme.getUltimo_alarme() + 24 / alarme.getIntervalo() * 3600000);
            Date nAlarmDate = nextAlarm.getTime();
            SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm");
            String nexAlarm = sdf3.format(nAlarmDate);

            Calendar initialDate = convertMillisIntoCalendar(alarme.getData_inicial());
            Date iDate = initialDate.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            String inDate = sdf.format(iDate);

            Calendar finalDate = convertMillisIntoCalendar(alarme.getData_final());
            Date finDate = finalDate.getTime();
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yyyy");
            String fDate = sdf2.format(finDate);


            alarms.add(new Alarm(medicamento.getProduto(), nexAlarm, alarme.getIntervalo(), inDate, fDate));
        }
    }

    public Calendar convertMillisIntoCalendar(long timeInMillis) {
        Timestamp timestamp = new Timestamp(timeInMillis);
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        return calendar;
    }

    public void voltar(View view) {
        finish();
        overridePendingTransition(0, R.anim.mover_direita);
    }

}