package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.NoCopySpan;


public class MyDbOpenHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PersonContract.PersonEntry.TABLE_NAME + " (" +
                    PersonContract.PersonEntry._ID + " INTEGER PRIMARY KEY, " +
                    PersonContract.PersonEntry.COLUMN_NAME_NAME + " VARCHAR(30), " +
                    PersonContract.PersonEntry.COLUMN_NAME_TEL + " VARCHAR(20), " +
                    PersonContract.PersonEntry.COLUMN_NAME_AGE + " INTEGER" +
                    ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PersonContract.PersonEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "mydb.db";

    public MyDbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
        initDb(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    private void initDb(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put(PersonContract.PersonEntry.COLUMN_NAME_NAME, "大黄");
        values.put(PersonContract.PersonEntry.COLUMN_NAME_TEL, "13758664125");
        values.put(PersonContract.PersonEntry.COLUMN_NAME_AGE, 18);

        db.insert(PersonContract.PersonEntry.TABLE_NAME, null, values);

        values.clear();

        values.put(PersonContract.PersonEntry.COLUMN_NAME_NAME, "local");
        values.put(PersonContract.PersonEntry.COLUMN_NAME_TEL, "13758664125");
        values.put(PersonContract.PersonEntry.COLUMN_NAME_AGE, 19);

        db.insert(PersonContract.PersonEntry.TABLE_NAME, null, values);

        values.clear();

        values.put(PersonContract.PersonEntry.COLUMN_NAME_NAME, "sorry");
        values.put(PersonContract.PersonEntry.COLUMN_NAME_TEL, "13758664125");
        values.put(PersonContract.PersonEntry.COLUMN_NAME_AGE, 20);

        db.insert(PersonContract.PersonEntry.TABLE_NAME, null, values);

        values.clear();

        values.put(PersonContract.PersonEntry.COLUMN_NAME_NAME, "amazing");
        values.put(PersonContract.PersonEntry.COLUMN_NAME_TEL, "13758664125");
        values.put(PersonContract.PersonEntry.COLUMN_NAME_AGE, 21);

        db.insert(PersonContract.PersonEntry.TABLE_NAME, null, values);

        db.execSQL("INSERT INTO person VALUES(NULL, ?,?,?)", new Object[] {"Intersting..", "13758664125", 22});
    }

 public  void delete (String name  )
 {
     SQLiteDatabase db = this.getWritableDatabase();
     db.delete("person","name=?",new String[] {name}) ;
     return ;
 }

 public  void addPerson (Person person)
 {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues() ;
     values.put(PersonContract.PersonEntry.COLUMN_NAME_NAME, person.getName().trim());
     values.put(PersonContract.PersonEntry.COLUMN_NAME_TEL, person.getTel().trim());
     values.put(PersonContract.PersonEntry.COLUMN_NAME_AGE, 21 );
     db.insert(PersonContract.PersonEntry.TABLE_NAME,null,values) ;
        return ;
 }

}
