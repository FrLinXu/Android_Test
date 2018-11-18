package com.example.music2;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceActivity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    int currentmusic ;
    private MediaPlayer mediaPlayer;
    private SeekBar mSeekbarAudio;
    private boolean mUserIsSeeking;
    private TextView time ;
    private int nowp = 0;
    private int songs ;
    private String[] MusicName = {
            "两只老虎", "无赖-郑中基","吉克隽逸 - 即刻出发","毛华锋 - 奇迹再现"
    };
    private int[] Musicpath = {
           R.raw.tiger,
            R.raw.wulai,
            R.raw.jikechufa,
            R.raw.qijizaixian
    };
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
                case R.id.btn_last:
                    if( mediaPlayer != null)
                {
                    if (mediaPlayer.isPlaying())
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(MainActivity.this , R.raw .tiger) ;
                    mediaPlayer = new MediaPlayer();
                    nowp =nowp -1 >-1 ? nowp-1 : songs-nowp-1;
                    Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" +Musicpath[ nowp % songs ]);
                   try
                   {
                       mediaPlayer.setDataSource(MainActivity.this,uri);

                       mediaPlayer.prepareAsync();
                   }
                   catch (IOException e )
                   {e.printStackTrace();}
                    mediaPlayer.start();
                }
                    break;
                case  R.id.btn_next:
                    if( mediaPlayer != null)
                    {
                        if (mediaPlayer.isPlaying())
                            mediaPlayer.stop();
                        mediaPlayer = MediaPlayer.create(MainActivity.this , R.raw .tiger) ;
                        mediaPlayer = new MediaPlayer();
                        nowp =nowp +1 <songs ? nowp+1 : nowp+1-songs;
                        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" +Musicpath[ nowp % songs ]);
                        try
                        {
                            mediaPlayer.setDataSource(MainActivity.this,uri);

                            mediaPlayer.prepareAsync();
                        }
                        catch (IOException e )
                        {e.printStackTrace();}
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
        currentmusic=0;
        nowp=1;
        songs=MusicName.length;
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.tiger);

            mediaPlayer = new MediaPlayer();

            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.wulai);
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
        Button last = (Button) findViewById(R.id.btn_last) ;
        Button next = (Button) findViewById(R.id.btn_next) ;
        last.setOnClickListener(onClickListener);
        next.setOnClickListener(onClickListener);
        btnStart.setOnClickListener(onClickListener);
        btnStop.setOnClickListener(onClickListener);
        btnResume.setOnClickListener(onClickListener);
        btnPause.setOnClickListener(onClickListener);

        time = (TextView) findViewById(R.id.time) ;
        initializeSeekbar();
        Handler handler= new Handler() ;
        updateProcess update = new updateProcess(time,mediaPlayer,mSeekbarAudio);
        update.start();
        ListView listView = (ListView) findViewById(R.id.musiclist) ;
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1,MusicName);
        listView.setAdapter(adapter);
      /*  MusicAdater adapter = new MusicAdater(MainActivity.this,MusicName);
        ListView listView = (ListView) findViewById(R.id.musiclist) ;
        listView.setAdapter(adapter);
        */
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



                        int timems =mediaPlayer.getCurrentPosition() ;
                        timems/=1000;
                        if (time!=null)
                        time.setText(timems/60+":"+ ( (timems%60>9 )?timems%60 : "0"+timems%60 )  );
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
