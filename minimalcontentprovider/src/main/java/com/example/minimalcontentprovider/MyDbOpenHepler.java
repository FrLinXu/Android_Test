package com.example.minimalcontentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Author by Deil,  Date on 2018/11/22.
 * PS: Not easy to write code, please indicate.
 */
public class MyDbOpenHepler  extends  SQLiteOpenHelper{



    private static  final String TAG = "MyDbOpenHepler";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE words(_ID INTEGER PRIMARY KEY, " +
                   " name VARCHAR(30), " +
                    " frequency integer " +
                    ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS wrods ";
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "words.db";
    public  MyDbOpenHepler (Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d(TAG, "MyDbOpenHepler: create.....");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        initDb(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    private void initDb(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put("name", "大黄");
        values.put("frequency", 0);
        db.insert("words", null, values);
        values.clear();
        values.put("name", "小明");
        values.put("frequency", 0);
        db.insert("words", null, values);
        values.clear();
        values.put("name", "小牛");
        values.put("frequency", 0);
        db.insert("words", null, values);
        values.clear();
        values.put("name", "大明");
        values.put("frequency", 0);
        db.insert("words", null, values);
        values.clear();

    }

    public void insert(Word word){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",word.getName());
        contentValues.put("frequency",word.getFrequency());

        this.getReadableDatabase().insert("words",null,contentValues);

    }
    public void update(Word word,String id){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",word.getName());
        contentValues.put("frequency",word.getFrequency());
        this.getReadableDatabase().update("words",contentValues,"id = ?",new String[] {id});
    }

}
