package com.probex.medicalerta.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialTextInputPicker;
import com.probex.medicalerta.R;

public class CreateUserActivity extends AppCompatActivity {
    Button materialTextInputPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        //materialTextInputPicker = findViewById(R.id.datePickerCreateUser);

        //Material date picker
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Início");
        final MaterialDatePicker materialDatePicker = builder.build();


    }
}