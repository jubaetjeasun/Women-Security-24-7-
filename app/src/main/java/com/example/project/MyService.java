package com.example.project;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;


public class MyService extends Service {
    //creating a mediaplayer object
    private MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //getting systems default ringtone

        //player = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI);
        player = MediaPlayer.create(this,R.raw.siren);

        //setting loop play to true
        //this will make the ringtone continuously playing
        player.setLooping(true);

        //staring the player
        player.start();

        //we have some options for service
        //start sticky means service will be explicity started and stopped
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //stopping the player when service is destroyed
        player.stop();
    }
}