package com.probex.medicalerta.services;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.probex.medicalerta.R;
import com.probex.medicalerta.activity.AlarmActivity;
import com.probex.medicalerta.model.Alarm;

import java.io.Serializable;
import java.util.Date;

public class AlarmNotificationService extends IntentService {

    public static final String CHANNEL_ID = "NOTIFICATION_MED";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * name Used to name the worker thread, important only for debugging.
     */
    public AlarmNotificationService() {
        super("AlarmNotificationService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
            Bundle data = intent.getExtras();
            int idAlarm = data.getInt("idAlarm");
            String medName = data.getString("medName");

            System.out.println("idAlarm Alarm Notification service: " + idAlarm);
            System.out.println("medName Alarm Notification service: " + medName);

            createNotificationChannel();
            createNotification(idAlarm, medName);
    }

    private void createNotification(int idAlarm, String medName) {
        Intent fullScreenIntent = new Intent(this, AlarmActivity.class);
        fullScreenIntent.putExtra("idAlarm", idAlarm);
        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(this, 0,
                fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_medicalerta_contornos_brancos_10)
                .setContentTitle("Hora do remédio")
                .setContentText(medName)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setFullScreenIntent(fullScreenPendingIntent, true);

        //Notification appNotification = builder.build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(5534, builder.build());
    }

    @Override
    public void onDestroy() {
        System.out.println("Alarm notification service destroyed");
        super.onDestroy();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}