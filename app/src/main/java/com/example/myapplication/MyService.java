package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyService extends Service {
    public static String Name;

    MediaPlayer mediaPlayer;
    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(1,doNotification());
         if (intent.getAction() != null && intent.getAction().equals("close")) {
             System.out.println("yes stop");
             stopSelf();
        }
        if (!mediaPlayer.isPlaying())
            mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(getBaseContext(),R.raw.bling);
        mediaPlayer.setLooping(true);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @SuppressLint("NotificationTrampoline")
    public Notification doNotification(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel ch = new NotificationChannel("Notify","configure change", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(ch);
        }
        Intent intent = new Intent(getBaseContext(), MyReceiver.class);
        PendingIntent contentIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext(),"Notify");
        builder.setSmallIcon(R.drawable.baseline_person_24).setContentTitle("Update "+Name).setContentText("updated user success").setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setSilent(true);
        builder.setContentIntent(contentIntent);
        return builder.build();
    }
}