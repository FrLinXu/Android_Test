package com.example.minimalcontentprovider;

/**
 * Author by Deil,  Date on 2018/11/26.
 * PS: Not easy to write code, please indicate.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Dao {
    private SQLiteDatabase db;
    public Dao(Context context) {
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        db = mySQLiteOpenHelper.getReadableDatabase();

    }


    public  SQLiteDatabase getDb(){
        return db;
    }



    public void insert(Word word){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",word.getName());
        contentValues.put("frequency",word.getFrequency());

        db.insert("words",null,contentValues);

    }
    public void update(Word word,String id){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",word.getName());
        contentValues.put("frequency",word.getFrequency());
        db.update("words",contentValues,"id = ?",new String[] {id});
    }
}
