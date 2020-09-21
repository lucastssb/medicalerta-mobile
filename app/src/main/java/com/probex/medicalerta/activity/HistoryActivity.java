package com.probex.medicalerta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.probex.medicalerta.R;
import com.probex.medicalerta.adapter.MedItemAdapter;

public class HistoryActivity extends AppCompatActivity {

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
        MedItemAdapter adapter = new MedItemAdapter();
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

}