package com.probex.medicalerta.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.probex.medicalerta.R;

public class AlarmReceiver extends BroadcastReceiver {
    final String TAG = "Teste";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "Comecou outro");
        System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "123")
                .setSmallIcon(R.drawable.add_feed_rounded)
                .setContentTitle("Alert")
                .setContentText("Notification is working!!!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(100, builder.build());
    }
}
