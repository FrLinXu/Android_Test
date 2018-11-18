package com.example.music2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.List;

public class MusicAdater extends BaseAdapter{

    private  Context context ;
    private String data [] ;
    private int mposition_lv ;

    MusicAdater (Context context,String data [])
    {
        this.data = data ;
        this.context = context ;
    }
    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    static class Mitem
    {
         TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Mitem mitem;
        View view;
        if (convertView ==null )
        {
            view = LayoutInflater.from(context).inflate(R.layout.listitem,parent,false) ;
            mitem= new Mitem() ;
            mitem.textView = (TextView) view.findViewById(R.id.mes) ;
            view.setTag(mitem);
        }
        else {
            view = convertView;
            mitem = (Mitem) view.getTag();
        }


        return null;
    }
}
