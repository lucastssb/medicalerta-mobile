package com.probex.medicalerta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;
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
import java.util.TimeZone;

public class HistoryActivity extends AppCompatActivity {
    private BancoDadosMed bancoDadosMed;
    private List<Historico> historicos = new ArrayList<Historico>();
    private List<Historico> historicosSearch = new ArrayList<>();
    private List<Alarm> alarms = new ArrayList<>();
    private MedItemAdapter adapter;
    private long selectedStartDate = 0;
    private long selectedEndDate = 0;
    private String nameSearch;

    private RecyclerView recyclerMedList;
    private Button startDatePicker, endDatePicker;
    private TextInputLayout nameSearchTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerMedList = findViewById(R.id.recyclerMedList);
        startDatePicker = findViewById(R.id.startDatePicker);
        endDatePicker = findViewById(R.id.endDatePicker);
        nameSearchTextInput = findViewById(R.id.nameSearchTextInput);

        //Define layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerMedList.setLayoutManager( layoutManager );



        //Define adapter
        //this.setUpAlarms();

        bancoDadosMed = new BancoDadosMed(this);
        historicos = bancoDadosMed.listaTodosHistorico();
        bancoDadosMed.close();

        historicosSearch.addAll(historicos);

        adapter = new MedItemAdapter( historicosSearch, this );
        recyclerMedList.setAdapter( adapter );

        adapter.notifyDataSetChanged();

        nameSearchTextInput.getEditText().addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                nameSearch = s.toString();
                //get the String from CharSequence with s.toString() and process it to validation

            }
        });

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        //Material date picker start
        MaterialDatePicker.Builder builderStart = MaterialDatePicker.Builder.datePicker();
        builderStart.setTitleText("Início");
        final MaterialDatePicker materialDatePickerStart = builderStart.build();

        startDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePickerStart.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });

        materialDatePickerStart.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                startDatePicker.setText(materialDatePickerStart.getHeaderText());
                selectedStartDate = selection + 10801000;
            }
        });

        //Material date picker end
        MaterialDatePicker.Builder builderEnd = MaterialDatePicker.Builder.datePicker();
        builderStart.setTitleText("Término");
        final MaterialDatePicker materialDatePickerEnd = builderStart.build();

        endDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePickerEnd.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });

        materialDatePickerEnd.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                endDatePicker.setText(materialDatePickerEnd.getHeaderText());
                selectedEndDate = selection + 97199000;
            }
        });


    }

    public static List<Historico> setUpAlarmsSearch(List<Historico> hist, String medName, long start, long end) {
        List<Historico> alxHistoricoLista = new ArrayList<>();
        alxHistoricoLista.addAll(hist);

        for (Historico historico: hist
        ) {
            long finalDate = historico.getData_final() == 0 ? start + 1 : historico.getData_final();
            if (medName != null) {
                if(!historico.getProduto().contains(medName.toUpperCase())) {
                    alxHistoricoLista.remove(historico);
                }
            }
            if(start != 0 && end != 0) {
                if (!((start <= historico.getData_inicial() && historico.getData_inicial() <= end) || (start <= finalDate && finalDate <= end))) {
                    alxHistoricoLista.remove(historico);
                }
            }
        }

        System.out.println("Tamanho retorno: " +alxHistoricoLista.size());

        return alxHistoricoLista;
    }


    public void handlerSearch(View view){
        historicosSearch.clear();
        historicosSearch.addAll( setUpAlarmsSearch(historicos, nameSearch, selectedStartDate, selectedEndDate));
        adapter.notifyDataSetChanged();
    }

    public void voltar(View view) {
        finish();
        overridePendingTransition(0, R.anim.mover_direita);
    }

}