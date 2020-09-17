package com.probex.medicalerta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
}