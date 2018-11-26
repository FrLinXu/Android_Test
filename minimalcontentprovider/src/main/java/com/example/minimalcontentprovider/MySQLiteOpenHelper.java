package com.example.minimalcontentprovider;

/**
 * Author by Deil,  Date on 2018/11/26.
 * PS: Not easy to write code, please indicate.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MySQLiteOpenHelper extends  android.database.sqlite.SQLiteOpenHelper{

    static final String CREAT_TABLE_WORDS = "create table words(" +
            "_ID integer primary key autoincrement," +
            "name varchar(20)," +
            "frequency integer" +
            ")";

    public MySQLiteOpenHelper(Context context) {
        super(context,"MyDatabase.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_TABLE_WORDS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
