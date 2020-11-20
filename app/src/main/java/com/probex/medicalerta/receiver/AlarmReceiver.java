package com.probex.medicalerta.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.probex.medicalerta.services.AlarmSeekerService;


public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent service=new Intent(context, AlarmSeekerService.class);
            context.startService(service);
        Log.v("Tag", "AlarmReceiver called.");

    }
}
