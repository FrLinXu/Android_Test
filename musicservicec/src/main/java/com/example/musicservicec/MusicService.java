package com.example.musicservicec;

/**
 * Author by Deil,  Date on 2018/11/18.
 * PS: Not easy to write code, please indicate.
 */

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {
    MediaPlayer mMediaPlayer;

    public MusicService() {
    }

    @Override
    public void onDestroy() {
        mMediaPlayer.stop();
        mMediaPlayer.release();
        mMediaPlayer = null;
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = MediaPlayer.create(this, R.raw.tiger);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        mMediaPlayer.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.




        throw new UnsupportedOperationException("Not yet implemented");
    }
}
