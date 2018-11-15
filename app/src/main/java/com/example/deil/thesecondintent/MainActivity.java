package com.example.deil.thesecondintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button )findViewById(R.id.btn1) ;
        Button btn1 = (Button )findViewById(R.id.btn2) ;
        Button btn3= (Button ) findViewById(R.id.btn3) ;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , fristIntent.class) ;
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("intent_start") ;
                startActivity(intent);
            }
        });

        btn3 .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Intent.ACTION_VIEW) ;
                intent.setData(Uri.parse("http://www.bilibili.com" ) ) ;
                startActivity(intent);
            }
        });
    }
}
