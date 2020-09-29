package com.probex.medicalerta.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


public class AlarmService extends Service {
    final String TAG = "Test";

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "Comecou");
        System.out.println("Oi");
        // START_STICKY serve para executar seu serviço até que você pare ele, é reiniciado automaticamente sempre que termina
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
