package com.example.database;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private MyDbOpenHelper myDbHelper;
    List items =null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDbHelper = new MyDbOpenHelper(this);

        SQLiteDatabase db = myDbHelper.getReadableDatabase();

        Cursor cursor = db.query(PersonContract.PersonEntry.TABLE_NAME, null, null, null, null, null, null);

         items = new ArrayList<Person>();
        while (cursor.moveToNext()) {
            long _id = cursor.getLong(cursor.getColumnIndex(PersonContract.PersonEntry._ID));
            String name = cursor.getString(cursor.getColumnIndex(PersonContract.PersonEntry.COLUMN_NAME_NAME));
            String tel = cursor.getString(cursor.getColumnIndex(PersonContract.PersonEntry.COLUMN_NAME_TEL));
            long age = cursor.getLong(cursor.getColumnIndex(PersonContract.PersonEntry.COLUMN_NAME_AGE));

            items.add(new Person(_id, name, tel, age));
        }

        cursor.close();

        final ListView lvContacts = (ListView) findViewById(R.id.lv_contacts);
        Button edit =(Button) findViewById(R.id.edit) ;

        final ArrayAdapter<Person> adapter = new ArrayAdapter<Person>(this, R.layout.list_item, items) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                Person person = getItem(position);
                LayoutInflater layoutInflater = getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.list_item, parent, false);
                TextView tvName = (TextView) view.findViewById(R.id.tv_name);
                TextView tvTel = (TextView) view.findViewById(R.id.tv_tel);
                tvName.setText(person.getName());
                tvTel.setText(person.getTel());

                return view;
            }
        };

        lvContacts.setAdapter(adapter);
        lvContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {


                final String name =  ( (Person) (items.get(position) ) ).getName () ;
               // lvContacts.removeViewAt(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this) ;
                builder.setTitle("数据库删除");
                builder.setMessage("是确定要删除"+name +"么?");
                builder.setCancelable(true) ;
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        myDbHelper.delete(name);

                        items.remove(position) ;
                        adapter.notifyDataSetChanged();


                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();


                return false;
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this) ;
                final View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.addtext,null) ;
                builder.setTitle("添加") ;
                builder.setView(view) ;
                builder.setCancelable(false) ;
                final EditText  nameedit = (EditText) view.findViewById(R.id.nameedit);
                final  EditText  phoneedit =  (EditText) view.findViewById(R.id.phoneedit);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = nameedit.getText().toString().trim() ;
                        String phone = phoneedit.getText().toString().trim() ;
                        Person item = new Person(name, phone) ;
                        items.add(item) ;
                        adapter.notifyDataSetChanged();
                        myDbHelper.addPerson(item);


                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show() ;
            }
        });

    }

    @Override
    protected void onDestroy() {

        myDbHelper.close();
        super.onDestroy();
    }
}
