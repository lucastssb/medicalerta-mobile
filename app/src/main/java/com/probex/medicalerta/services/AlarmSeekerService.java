package com.probex.medicalerta.services;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.probex.medicalerta.adapter.Alarme;
import com.probex.medicalerta.adapter.Medicamento;
import com.probex.medicalerta.database.BancoDadosMed;
import com.probex.medicalerta.model.Alarm;
import com.probex.medicalerta.model.AppUtils;
import com.probex.medicalerta.receiver.AlarmIntentReceiver;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class AlarmSeekerService extends IntentService {
    //private List<Alarm> alarms = new ArrayList();
    private BancoDadosMed bancoDadosMed;
    private List<Alarme> listaAlarmes = new ArrayList<Alarme>();
    private long timeInMillis;
    private Medicamento medicamento;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * name Used to name the worker thread, important only for debugging.
     */
    public AlarmSeekerService() {
        super("AlarmSeekerService");
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

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
            long nextAlarm;

            if(alarme.getUltimo_alarme() == 0) {
                nextAlarm = alarme.getData_inicial();

            }else {
                nextAlarm = alarme.getUltimo_alarme() + 24 / alarme.getIntervalo() * 3600000;
            }

            if(nextAlarm - timeInMillis <= 900000 && nextAlarm >= timeInMillis){
                //Convert the time in milliseconds to a calendar instance
                Calendar calendar = AppUtils.convertMillisToCalendar(nextAlarm);

                System.out.println("Alarm set" + alarme.getId_med());

                medicamento = bancoDadosMed.selecionarMedicamento(alarme.getId_med());

                startAlarm(calendar, alarme.getId_alarme(), medicamento.getProduto());

                //Alarme all = bancoDadosMed.selecionarAlarme(alarme.getId_alarme());

                alarme.setUltimo_alarme(nextAlarm);
                bancoDadosMed.atualizaAlarme(alarme);

                bancoDadosMed.close();
            }
        }

    }

    @Override
    public void onDestroy() {
        System.out.println("Alarm Seeker service destroyed");
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startAlarm(Calendar calendar, int idAlarm, String medName) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intentAlm = new Intent(this, AlarmIntentReceiver.class);
        intentAlm.putExtra("idAlarm", idAlarm);
        intentAlm.putExtra("medName", medName);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intentAlm, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
}