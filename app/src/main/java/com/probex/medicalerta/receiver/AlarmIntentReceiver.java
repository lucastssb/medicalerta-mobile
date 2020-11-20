package com.probex.medicalerta.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.probex.medicalerta.services.AlarmNotificationService;
import com.probex.medicalerta.services.AlarmSeekerService;

public class AlarmIntentReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Intent service=new Intent(context, AlarmNotificationService.class);
        context.startService(service);
        Log.v("Tag", "Alarm intent receiver called.");
    }
}