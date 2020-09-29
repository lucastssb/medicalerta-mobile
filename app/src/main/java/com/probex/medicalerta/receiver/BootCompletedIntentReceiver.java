package com.probex.medicalerta.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.probex.medicalerta.services.AlarmService;

public class BootCompletedIntentReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent pushIntent = new Intent(context, AlarmService.class);
            context.startService(pushIntent);
        }
    }

}