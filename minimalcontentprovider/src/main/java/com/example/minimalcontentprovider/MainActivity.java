package com.example.minimalcontentprovider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    ArrayList<Word> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn_toSecondActivity);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerViewiew);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        getData();
        for(int  i=0;i<list.size();i++){
            Log.e("11111111",list.get(i).getName());
        }
        MyAdapter myAdapter = new MyAdapter(list);
        recyclerView.setAdapter(myAdapter);

    }
    public void getData() {

        Uri uri = Uri.parse("content://com.example.minimalcontentprovider.WordsProvider/words");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if(cursor!=null){
            while(cursor.moveToNext()){
                Word word = new Word();
                int id = cursor.getInt(cursor.getColumnIndex("_ID"));
                int frequency = cursor.getInt(cursor.getColumnIndex("frequency"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                word.set_id(id);
                word.setName(name);
                word.setFrequency(frequency);
                list.add(word);

            }
        }
    }

    class MyAdapter extends RecyclerView.Adapter{
        ArrayList<Word> list = new ArrayList<>();

        public MyAdapter(ArrayList<Word> list){
            this.list = list;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycle_item,
                    parent, false);
            MyHolder myHolder = new MyHolder(rootView);
            return myHolder;

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            ((MyHolder)holder).tv_ID.setText(list.get(position).get_id()+"");
            ((MyHolder)holder).tvName.setText(list.get(position).getName());
            ((MyHolder)holder).tvFrequency.setText(list.get(position).getFrequency()+"");
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        private class MyHolder extends RecyclerView.ViewHolder{
            private TextView tv_ID;
            private TextView tvName;
            private TextView tvFrequency;

            public MyHolder(View itemView) {
                super(itemView);
                tv_ID = itemView.findViewById(R.id.tv_ID);
                tvName = itemView.findViewById(R.id.tvName);
                tvFrequency = itemView.findViewById(R.id.tvFrequency);
            }
        }
    }

}
