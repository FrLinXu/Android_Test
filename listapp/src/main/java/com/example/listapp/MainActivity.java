package com.example.listapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private String[] data = {
            "Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
            "Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango"
    };


    private List<Fruit> fruitList = new ArrayList<>();

    private String[] fruitName = {
            "Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
    };

    private int[] fruitImageId = {
            R.drawable.apple_pic,
            R.drawable.banana_pic,
            R.drawable.orange_pic,
            R.drawable.watermelon_pic,
            R.drawable.pear_pic,
            R.drawable.grape_pic,
            R.drawable.pineapple_pic,
            R.drawable.strawberry_pic,
            R.drawable.cherry_pic,
            R.drawable.mango_pic
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_list_item_1, data);

        ListView listView = (ListView) findViewById(R.id.list_view);



        initFruits();

        FruitAdapter fruitAdapter = new FruitAdapter(MainActivity.this,
                R.layout.fruit_item, fruitList);



        listView.setAdapter(fruitAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(MainActivity.this,
                        "You selected " + fruitList.get(i).getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFruits() {
        for (int i = 0; i < fruitName.length; i++) {
            Fruit fruit = new Fruit(fruitName[i], fruitImageId[i]);
            fruitList.add(fruit);
        }

        for (int i = 0; i < fruitName.length; i++) {
            Fruit fruit = new Fruit(fruitName[i], fruitImageId[i]);
            fruitList.add(fruit);
        }
    }
}
