package com.probex.medicalerta.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;

import com.probex.medicalerta.R;
import com.probex.medicalerta.activity.AlarmActivity;
import com.probex.medicalerta.receiver.AlarmReceiver;


public class AlarmService extends Service {
    final String TAG = "Test";

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "Comecou");
        System.out.println("Oi");
        //Intent dialogIntent = new Intent(this, AlarmActivity.class);
        //dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //startActivity(dialogIntent);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intentAlm = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intentAlm, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, 1, 60000, pendingIntent);
        Log.e(TAG, "Terminou");
        // START_STICKY serve para executar seu serviço até que você pare ele, é reiniciado automaticamente sempre que termina
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
