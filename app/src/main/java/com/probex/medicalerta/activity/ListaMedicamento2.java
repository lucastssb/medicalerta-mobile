package com.probex.medicalerta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.probex.medicalerta.R;
import com.probex.medicalerta.adapter.Alarme;
import com.probex.medicalerta.adapter.Medicamento;
import com.probex.medicalerta.database.BancoDadosMed;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ListaMedicamento2 extends AppCompatActivity {

    BancoDadosMed db = new BancoDadosMed(this);
    private Spinner vezesDias;
    private EditText diasTrata;
    private TextView tvTimer, produto;
    private static final int SUCCESS = 1, ERROR = -1;
    private int t1Hour, t1Minute;
    private long horaInicial, dataFinal, diaMilisegundos = 86400000;
    private boolean timeAlterado = false;
    private Medicamento medicamento;
    private Date data;
    private Alarme alarme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicamento2);

        //Pegando dados Activity anterior
        String value = getIntent().getStringExtra("id");
        int drawableId = getIntent().getIntExtra("drawable", 0);

        //Banco de dados
        alarme = db.selecionarAlarme(Integer.valueOf(value));
        medicamento = db.selecionarMedicamento(Integer.valueOf(value));


        produto = (TextView) findViewById(R.id.produto);
        produto.setText(medicamento.getProduto());

        //Set FOTO
        String nomeFoto = "ic_pic" + String.valueOf(medicamento.getIndicacao());
        int drawableIds = getResources().getIdentifier(nomeFoto, "drawable", getPackageName());
        Drawable merDrawable = getResources().getDrawable(drawableIds);

        ImageView pictograma = (ImageView) findViewById(R.id.ic_medicamento);
        pictograma.setImageDrawable(merDrawable);


        //Setando Dias de tratamento
        diasTrata = (EditText) findViewById(R.id.edit_dias_trata);
        if (alarme.getData_final() == 0) {
        } else {
            long ini = alarme.getData_inicial();
            long fin = alarme.getData_final();
            long res = (fin - ini) / 86400000;
            String resultadoDias = String.valueOf(res);
            diasTrata.setText(resultadoDias);
        }


        //Set spinner Intervalo
        vezesDias = (Spinner) findViewById(R.id.spinner_vezes_dia);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.vezes_por_dia, android.R.layout.simple_spinner_dropdown_item);
        vezesDias.setAdapter(adapter2);
        int posicao = alarme.getIntervalo();
        switch (posicao) {
            case 1:
                posicao = 0;
                break;
            case 4:
                posicao = 1;
                break;
            case 6:
                posicao = 2;
                break;
            case 8:
                posicao = 3;
                break;
            case 12:
                posicao = 4;
                break;
        }
        vezesDias.setSelection(posicao);

        //Set get hora
        tvTimer = findViewById(R.id.tv_timer1);
        tvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarTimePickerDialog();
            }
        });


//
//        Date date = new Date();
//
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//
//            System.out.println("++++++++++++++++++++++++++++++\nData Inicial: " + sdf.format(alarme.getData_inicial()));
//
//        date.setTime(alarme.getData_final());
//        System.out.println("Data Final: " + sdf.format(date));
//        date.setTime(alarme.getUltimo_alarme());
//        System.out.println("Último Alarme: " + sdf.format(date));
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

    public void voltar(View view) {

        finish();
        overridePendingTransition(0, R.anim.mover_direita);
    }

    public void confirmar(final View view) {
        final Alarme novoAlarme = new Alarme();

        AlertDialog alerta;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("ATUALIZAR");
        //define a mensagem
        builder.setMessage("Você deseja atualizar este medicamento?");
        //define um botão como positivo
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
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


                Calendar cal = Calendar.getInstance(new Locale("BR"));
                cal.set(Calendar.HOUR_OF_DAY, t1Hour);
                cal.set(Calendar.MINUTE, t1Minute);


                novoAlarme.setId_med(alarme.getId_med());
                novoAlarme.setId_alarme(alarme.getId_alarme());


                if (timeAlterado == true) {
                    cal.set(Calendar.HOUR_OF_DAY, t1Hour);
                    cal.set(Calendar.MINUTE, t1Minute);
                    data = cal.getTime();
                    horaInicial = data.getTime();

                    if (diasTrata.getText().toString().equals("")) { //tentar pegar erro nullpoint
                        dataFinal = 0;
                    } else {
                        String d = diasTrata.getEditableText().toString();
                        Long l = Long.valueOf(d).longValue();
                        dataFinal = horaInicial + diaMilisegundos * l;
                    }
                    novoAlarme.setUltimo_alarme(horaInicial);
                    novoAlarme.setData_inicial(horaInicial);
                } else {
                    if (diasTrata.getText().toString().equals("")) { //tentar pegar erro nullpoint
                        dataFinal = 0;
                    } else {
                        String d = diasTrata.getEditableText().toString();
                        Long l = Long.valueOf(d).longValue();
                        dataFinal = alarme.getUltimo_alarme() + diaMilisegundos * l;
                    }
                    novoAlarme.setUltimo_alarme(alarme.getUltimo_alarme());
                    novoAlarme.setData_inicial(alarme.getData_inicial());
                }

                novoAlarme.setData_final(dataFinal);
                novoAlarme.setIntervalo(posicao);

                db.atualizaAlarme(novoAlarme);

                voltar(view);
                showToast(SUCCESS, "Medicamento Atualizado");
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

    public void excluir(final View view) {
        AlertDialog alerta;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Excluir");
        //define a mensagem
        builder.setMessage("Você deseja excluir este medicamento?");
        //define um botão como positivo
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                db.apagarMedicamento(medicamento);
                db.apagarAlarme(alarme);
                voltar(view);
                showToast(SUCCESS, "Medicamento Excluido");
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //Toast.makeText(ListaMedicamento2.this, "negativo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

    private void showToast(int type, String message) {
        ViewGroup view = findViewById(R.id.container_toast);
        View v = getLayoutInflater().inflate(R.layout.custom_toast, view);

        switch (type) {
            case SUCCESS:
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_sucess));
                break;
            case ERROR:
                v.setBackground(ContextCompat.getDrawable(this, R.drawable.toast_error));
                break;

        }

        TextView txtMessage = v.findViewById(R.id.txt_message);
        txtMessage.setText(message);

        Toast toast = new Toast(this);
        toast.setView(v);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(ListaMedicamento2.this);
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
        TimePickerDialog dialog = new TimePickerDialog(ListaMedicamento2.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mTimeSetListener,
                horaSystem,
                minutoSystem,
                true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}