package com.probex.medicalerta.services;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.util.Log;


import androidx.annotation.Nullable;
import com.probex.medicalerta.receiver.AlarmReceiver;


public class AlarmService extends IntentService {
    final String TAG = "Test";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public AlarmService() {
        super("AlarmService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e(TAG, "Comecou");
        System.out.println("Oi");
        //Intent dialogIntent = new Intent(this, AlarmActivity.class);
        //dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //startActivity(dialogIntent);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intentAlm = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intentAlm, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, 1, 30000, pendingIntent);

    }

    @Override
    public void onDestroy() {
        System.out.println("Alarm service destroyed");
        super.onDestroy();
        sendBroadcast(new Intent("RestartService"));
    }

}
