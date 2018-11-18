package com.example.music2;

import android.media.MediaPlayer;
import android.widget.SeekBar;
import android.widget.TextView;

public class updateProcess extends Thread {

    TextView textView ;
    MediaPlayer mediaPlayer ;
    SeekBar seekBar;
    updateProcess (TextView textView , MediaPlayer mediaPlayer , SeekBar seekBar)
    {
        this.textView = textView ;
        this.mediaPlayer = mediaPlayer ;
        this.seekBar = seekBar ;
    }
    @Override
    public void run() {

        while (true) {

            int now = mediaPlayer.getCurrentPosition();
            int len = mediaPlayer.getDuration();
            int pro = (int ) (100.0 * now / len ) ;
            int nows = now/1000;

            seekBar.setProgress(pro);


            try {
                sleep(1000);
            }
            catch (Exception e) {e.printStackTrace();  }
        }
    }
}
