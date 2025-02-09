package com.probex.medicalerta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.probex.medicalerta.R;
import com.probex.medicalerta.adapter.Alarme;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddMedication3 extends AppCompatActivity {

    private TextView tvTimer;
    private EditText diasTrata;
    private int t1Hour, t1Minute;
    private Button buttonContinuar;
    private Spinner vezesDias;
    private String value;
    private Alarme alarme;
    boolean timeAlterado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication3);

        value = getIntent().getStringExtra("key");

        diasTrata = (EditText) findViewById(R.id.edit_dias_trata);


        vezesDias = (Spinner) findViewById(R.id.spinner_vezes_dia);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.vezes_por_dia, android.R.layout.simple_spinner_dropdown_item);
        vezesDias.setAdapter(adapter2);


        tvTimer = findViewById(R.id.tv_timer1);
        tvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarTimePickerDialog();
            }
        });

        buttonContinuar = findViewById(R.id.continuar);
        buttonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonContinuar();
            }
        });

    }

    public void click(View v) {
        closeKeyboard();
    }

    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void buttonContinuar() {
        if(timeAlterado == false){
            AlertDialog alerta;
            AlertDialog.Builder builder = new AlertDialog.Builder(AddMedication3.this);
            //define o titulo
            builder.setTitle("AVISO");
            //define a mensagem
            builder.setMessage("É preciso escolher o horário de início");
            //define um botão como positivo
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    chamarTimePickerDialog();
                }
            });
            //cria o AlertDialog
            alerta = builder.create();
            //Exibe
            alerta.show();
        }else {
            long horaInicial, dataFinal, diaMilisegundos = 86400000;
            Date data;


            //Intervalo
            int posicao = vezesDias.getSelectedItemPosition();
            switch (posicao) {
                case 0:
                    posicao = 1;
                    break;
                case 1:
                    posicao = 4;
                    break;
                case 2:
                    posicao = 6;
                    break;
                case 3:
                    posicao = 8;
                    break;
                case 4:
                    posicao = 12;
                    break;

            }

            //Hora inicial
            Calendar cal = Calendar.getInstance(new Locale("BR"));

            cal.set(Calendar.HOUR_OF_DAY, t1Hour);
            cal.set(Calendar.MINUTE, t1Minute);
            cal.set(Calendar.SECOND, 0);

            data = cal.getTime();
            horaInicial = data.getTime();

            //Dias de Tratamento
            if (diasTrata.getText().toString().equals("")) { //tentar pegar erro nullpoint
                dataFinal = 0;
            } else {
                String d = diasTrata.getEditableText().toString();
                Long l = Long.valueOf(d).longValue();
                dataFinal = data.getTime() + diaMilisegundos * l;
            }
            //Criando Alarme
            alarme = new Alarme();
            alarme.setId_med(Integer.valueOf(value));
            alarme.setData_inicial(horaInicial);
            alarme.setData_final(dataFinal);
            alarme.setUltimo_alarme(0);
            alarme.setIntervalo(posicao);

            Intent intent = new Intent(AddMedication3.this, AddMedication4.class);
            intent.putExtra("alarme", alarme);
            intent.putExtra("medicamento", value);
            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.mover_esquerda, R.anim.fade_out);
            ActivityCompat.startActivity(AddMedication3.this, intent, activityOptionsCompat.toBundle());
        }
    }

    public void voltar(View view) {
        finish();
        overridePendingTransition(0, R.anim.mover_direita);
    }

    private void chamarTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        final int horaSystem = calendar.get(Calendar.HOUR_OF_DAY);
        final int minutoSystem = calendar.get(Calendar.MINUTE);


        TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hora, int minuto) {
                t1Hour = hora;
                t1Minute = minuto;


                if (t1Hour > horaSystem ||  t1Hour == horaSystem && t1Minute >= minutoSystem ) {
                    String time = t1Hour + ":" + t1Minute;
                    SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                    try {
                        Date date = f24Hours.parse(time);
                        SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                        tvTimer.setText(f24Hours.format(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Calendar calNow = Calendar.getInstance();
                    //Não é necessário, getInstance já retorna o calendário com a data e hora actual
                    calNow.set(Calendar.HOUR_OF_DAY, hora);
                    calNow.set(Calendar.MINUTE, minuto);
                    timeAlterado = true;

                } else {
                    AlertDialog alerta;
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddMedication3.this);
                    //define o titulo
                    builder.setTitle("AVISO");
                    //define a mensagem
                    builder.setMessage("O horário do próximo alarme deve ser maior que o horário atual");
                    //define um botão como positivo
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            chamarTimePickerDialog();
                        }
                    });
                    //cria o AlertDialog
                    alerta = builder.create();
                    //Exibe
                    alerta.show();
                }
            }
        };

        //Cria um calendário com a data e hora actual
        TimePickerDialog dialog = new TimePickerDialog(AddMedication3.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mTimeSetListener,
                horaSystem,
                minutoSystem,
                true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

}