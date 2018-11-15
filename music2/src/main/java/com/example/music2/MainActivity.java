package com.example.music2;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar mSeekbarAudio;
    private boolean mUserIsSeeking;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_start:
                    if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                        try {
                            mediaPlayer.start();

                        } catch (IllegalStateException ex) {
                            ex.printStackTrace();
                        }

                    } else if (mediaPlayer == null) {
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.tiger);
                        try {
                            mediaPlayer.start();
                        }catch (IllegalStateException ex) {
                            ex.printStackTrace();
                        }
                    }

                    break;
                case R.id.btn_stop:
                    if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    break;
                case R.id.btn_pause:
                    if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                    }
                    break;
                case R.id.btn_resume:
                    if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                        mediaPlayer.start();
                    }
                    break;
            }

        }
    };

    private MediaPlayer.OnPreparedListener onPreparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            // mediaPlayer.start();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.tiger);

            mediaPlayer = new MediaPlayer();

            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.tiger);
            try {
                mediaPlayer.setDataSource(this, uri);
                mediaPlayer.setOnPreparedListener(onPreparedListener);
                mediaPlayer.prepareAsync();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

        Button btnStart = (Button) findViewById(R.id.btn_start);
        Button btnStop = (Button) findViewById(R.id.btn_stop);
        Button btnResume = (Button) findViewById(R.id.btn_resume);
        Button btnPause = (Button) findViewById(R.id.btn_pause);

        btnStart.setOnClickListener(onClickListener);
        btnStop.setOnClickListener(onClickListener);
        btnResume.setOnClickListener(onClickListener);
        btnPause.setOnClickListener(onClickListener);

        initializeSeekbar();
    }

    @Override
    protected void onStop() {

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        super.onStop();
    }

    private void initializeSeekbar() {

        mSeekbarAudio = (SeekBar) findViewById(R.id.seekBar);

        mSeekbarAudio.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int userSelectedPosition = 0;

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        mUserIsSeeking = true;
                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            userSelectedPosition = progress;
                        }
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        mUserIsSeeking = false;
                        if (mediaPlayer != null) {
                            int mediaDuration = mediaPlayer.getDuration();
                            mediaPlayer.seekTo(userSelectedPosition * mediaDuration / 100);
                        }
                    }
                });
    }
}
