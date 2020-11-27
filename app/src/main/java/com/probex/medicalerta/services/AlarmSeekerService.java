package com.probex.medicalerta.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;


import androidx.annotation.RequiresApi;

import com.probex.medicalerta.adapter.Alarme;
import com.probex.medicalerta.adapter.Medicamento;
import com.probex.medicalerta.database.BancoDadosMed;
import com.probex.medicalerta.model.Alarm;
import com.probex.medicalerta.receiver.AlarmIntentReceiver;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class AlarmSeekerService extends Service {
    //private List<Alarm> alarms = new ArrayList();
    private BancoDadosMed bancoDadosMed;
    private List<Alarme> listaAlarmes = new ArrayList<Alarme>();
    private long timeInMillis;
    private Medicamento medicamento;


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bancoDadosMed = new BancoDadosMed(this);
        listaAlarmes = bancoDadosMed.listaTodosAlarmes();

        Calendar calendarTime = Calendar.getInstance();
        timeInMillis = calendarTime.getTimeInMillis();

        //System.out.println(listaAlarmes.get(0).getData_inicial());

        //Calendar calendar = Calendar.getInstance();
        //calendar.set(Calendar.HOUR_OF_DAY, 22);
        //calendar.set(Calendar.MINUTE, 32);
        //calendar.set(Calendar.SECOND, 0);

        for (Alarme alarme: listaAlarmes
             ) {
            if(alarme.getData_inicial() - timeInMillis <= 900000 && alarme.getData_inicial() >= timeInMillis){
                Timestamp timestamp = new Timestamp(alarme.getData_inicial());
                Calendar calendar = GregorianCalendar.getInstance();
                calendar.setTimeInMillis(timestamp.getTime());

                Date AlarmDate = calendar.getTime();
                SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm");
                String alarmHour = sdf3.format(AlarmDate);

                System.out.println("Alarm set" + alarme.getId_med());

                medicamento = bancoDadosMed.selecionarMedicamento(alarme.getId_med());
                String medNome = medicamento.getProduto();
                int idMed = alarme.getId_med();

                startAlarm(calendar, medNome, idMed, alarmHour);
            }
        }



        return START_STICKY;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startAlarm(Calendar c, String medNome, int idMed, String hora) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intentAlm = new Intent(this, AlarmIntentReceiver.class);
        intentAlm.putExtra("Nome", medNome);
        intentAlm.putExtra("Id", idMed);
        intentAlm.putExtra("Hora", hora);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intentAlm, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }
}