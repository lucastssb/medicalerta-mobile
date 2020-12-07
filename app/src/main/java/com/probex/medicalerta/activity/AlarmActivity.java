package com.probex.medicalerta.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.probex.medicalerta.R;
import com.probex.medicalerta.adapter.Alarme;
import com.probex.medicalerta.adapter.Historico;
import com.probex.medicalerta.adapter.Medicamento;
import com.probex.medicalerta.adapter.Notificacao;
import com.probex.medicalerta.adapter.Usuario;
import com.probex.medicalerta.database.BancoDadosMed;
import com.probex.medicalerta.helper.ImageSaver;
import com.probex.medicalerta.model.Alarm;
import com.probex.medicalerta.model.AppUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AlarmActivity extends AppCompatActivity {
    private TextView textName, textHour, userNameAlarm;
    private ImageView userPicAlarm;

    private Alarm alarm;
    private String hour;
    private BancoDadosMed bancoDadosMed;
    private List<Alarme> alarmes;
    private Alarme alm;
    private Medicamento medicamento;
    private List<Usuario> usuarios;

    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setShowWhenLocked(true);
        setTurnScreenOn(true);
        setContentView(R.layout.activity_alarm);

        textHour = findViewById(R.id.hour);
        textName = findViewById(R.id.nameMed);
        userPicAlarm = findViewById(R.id.userPicAlarm);
        userNameAlarm = findViewById(R.id.userNameAlarm);

        bancoDadosMed = new BancoDadosMed(this);

        Bundle data = getIntent().getExtras();
        int idAlarm = data.getInt("idAlarm");

        usuarios = bancoDadosMed.listaTodosUsuarios();

        if(usuarios.size() != 0) {
            Usuario usuario =  usuarios.get(0);
            int ft;

            try {
                //For some reason it does not work out if i use a string so i had to convert it to int
                ft = Integer.parseInt(usuario.getFoto());
            }catch (Exception e) {
                ft = 1;
            }

            if(ft != 0) {
                Bitmap bitmap = new ImageSaver(this).
                        setFileName(usuario.getFoto()).
                        setDirectoryName("images").
                        load();
                userPicAlarm.setImageBitmap(bitmap);
            }

            String[] name = usuario.getNome().split(" ");
            userNameAlarm.setText(name[0]);
        }

        alarmes = bancoDadosMed.listaTodosAlarmes();

        for (Alarme alarme:alarmes
             ) {
            if(alarme.getId_alarme() == idAlarm) {
                alm = alarme;
            }
        }
        medicamento = bancoDadosMed.selecionarMedicamento(alm.getId_med());

        hour = AppUtils.getHourFromCalendar(AppUtils.convertMillisToCalendar(alm.getUltimo_alarme()));
        textName.setText(medicamento.getProduto());
        textHour.setText(hour);
    }

    public void tookIt(View view) {
        bancoDadosMed.addHistorico(new Historico(medicamento.getId_med(),
                medicamento.getIndicacao(),
                medicamento.getSubstancia(),
                medicamento.getProduto(),
                medicamento.getConcentracao(),
                medicamento.getForma_farmaceutica(),
                medicamento.getQuantidade(),
                medicamento.getSubclasse(),
                alm.getData_inicial(),
                alm.getData_final(),
                alm.getIntervalo()
        ));
        bancoDadosMed.close();
        finish();
    }

    public void didntTakeIt(View view) {
        String description = "Você esqueceu de tomar o medicamento às " + hour;
        bancoDadosMed.addNotificação(new Notificacao(medicamento.getProduto(), description));
        bancoDadosMed.close();
        finish();
    }
}