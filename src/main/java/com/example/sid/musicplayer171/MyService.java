package com.example.sid.musicplayer171;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by SID on 9/7/2017.
 */

public class MyService extends Service {
    private MediaPlayer mediaPlayer;

    public void onCreate() {
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        Toast.makeText(getApplicationContext(), "On Create of Service", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //to start music
        Toast.makeText(getApplicationContext(), "On Start of Service", Toast.LENGTH_SHORT).show();
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        //to show notofication
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MyService.this);
        builder.setContentTitle("Music Player");
        builder.setContentText("Music Playing");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        Intent intent1 = new Intent(MyService.this, MainActivity.class);
        //when click on notifiction it will open main activity
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 100, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        //to stop music
        Toast.makeText(getApplicationContext(), "On Destroy of Service", Toast.LENGTH_SHORT).show();
        mediaPlayer.release();
        super.onDestroy();
    }
}
