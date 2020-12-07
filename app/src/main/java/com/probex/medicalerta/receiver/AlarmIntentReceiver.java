package com.probex.medicalerta.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.probex.medicalerta.model.Alarm;
import com.probex.medicalerta.services.AlarmNotificationService;

public class AlarmIntentReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Bundle data = intent.getExtras();
        int idAlarm = data.getInt("idAlarm");
        String medName = data.getString("medName");

        System.out.println("idAlarm receiver: " + idAlarm);
        System.out.println("medName receiver: " + medName);

        Intent service = new Intent(context, AlarmNotificationService.class);
        service.putExtra("idAlarm", idAlarm);
        service.putExtra("medName", medName);
        context.startService(service);
        Log.v("Tag", "Alarm intent receiver called.");
    }
}