package com.example.minimalcontentprovider;

/**
 * Author by Deil,  Date on 2018/11/26.
 * PS: Not easy to write code, please indicate.
 */


import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

public class SecondActivity  extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton fab;
    Button btn_update;
    Button btn_insert;
    EditText et_name;
    EditText et_id;

    Dao dao ;
    Word word = new Word();
    int frequency = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
        dao = new Dao(this);
        fab.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_insert.setOnClickListener(this);
    }

    private void init() {
        fab = findViewById(R.id.fab);
        btn_insert  = findViewById(R.id.btn_insert);
        btn_update  = findViewById(R.id.btn_update);
        et_name = findViewById(R.id.et_name);
        et_id = findViewById(R.id.et_id);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.fab:
                frequency++;

                Uri uri = Uri.parse("content://com.example.minimalcontentprovider.WordsProvider/words");
                ContentValues contentValues = new ContentValues();
                contentValues.put("name",et_name.getText().toString());
                contentValues.put("frequency",frequency);
                getContentResolver().insert(uri,contentValues);
                Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_insert:
                frequency++;

                Uri uri1 = Uri.parse("content://com.example.minimalcontentprovider.WordsProvider/words");
                ContentValues contentValues1 = new ContentValues();
                contentValues1.put("name",et_name.getText().toString());
                contentValues1.put("frequency",frequency);
                getContentResolver().insert(uri1,contentValues1);
                Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_update:
                frequency++;
                Uri uri2 = Uri.parse("content://com.example.minimalcontentprovider.WordsProvider/words");
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("name",et_name.getText().toString());
                contentValues2.put("frequency",frequency);
                getContentResolver().update(uri2,contentValues2,"_ID = ?",new String[]{et_id.getText().toString()});
                Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();
                break;
            default:break;

        }
    }

}
