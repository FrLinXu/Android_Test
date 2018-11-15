package com.example.triex;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CheckBox che =null ;
    EditText text1 = null ;
    EditText text2 = null ;

    String name=null  ;
    String passwd=null;
    private SharedPreferences preferences ;
    private SharedPreferences.Editor editor ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = (EditText) findViewById(R.id.name) ;
        text2 = (EditText) findViewById(R.id.passwd) ;
        che = (CheckBox) findViewById(R.id.che1) ;
        if ("".equals(text1.getText().toString().trim()) )che.setChecked(true);
        else che.setChecked(false);
        preferences =getSharedPreferences("config" ,MODE_PRIVATE) ;
        editor = preferences.edit() ;
        text1.setText(preferences.getString("name",""));
        text2.setText(preferences.getString("passwd",""));
         che.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
         {

             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                 editor.putString("name",text1.getText().toString().trim());
                 editor.putString("passwd",text2.getText().toString().trim());
                 editor.commit() ;

                }
                else
                {
                    editor.putString("name","");
                    editor.putString("passwd","");
                    editor.commit() ;
                }
             }
         });
    }
}
