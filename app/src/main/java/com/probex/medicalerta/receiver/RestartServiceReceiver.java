package com.probex.medicalerta.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.probex.medicalerta.services.AlarmService;

public class RestartServiceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context.getApplicationContext(), AlarmService.class));
    }
}