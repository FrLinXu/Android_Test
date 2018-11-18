package com.example.musicservicec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btStart = (Button) findViewById(R.id.btStart);

        if (btStart != null) {
            btStart.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, MusicService.class);
                    startService(intent);
                }
            });
        }
        Button btStop = (Button) findViewById(R.id.btStop);
        if (btStop != null) {
            btStop.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, MusicService.class);
                    stopService(intent);
                }
            });
        }

    }
}
