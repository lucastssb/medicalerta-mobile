package com.probex.medicalerta.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.probex.medicalerta.services.AlarmNotificationService;
import com.probex.medicalerta.services.AlarmSeekerService;

import java.util.Date;

public class AlarmIntentReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Bundle data = intent.getExtras();
        String medNome = data.getString("Nome");
        int idMed = data.getInt("Id");
        String hora = data.getString("Hora");

        Intent service = new Intent(context, AlarmNotificationService.class);
        service.putExtra("Nome", medNome);
        service.putExtra("Id", idMed);
        service.putExtra("Hora", hora);
        context.startService(service);
        Log.v("Tag", "Alarm intent receiver called.");
    }
}