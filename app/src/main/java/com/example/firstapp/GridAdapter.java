package com.example.firstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter{

    Context context;
    String[] phoneName;
    int[] image;

    public GridAdapter(Context context, String[] phoneName, int[] image) {
        this.context=context;
        this.phoneName = phoneName;
        this.image = image;
    }

    @Override
    public int getCount() {
        return phoneName.length;
    }

    @Override
    public Object getItem(int position) {
        return phoneName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item,parent,false);
        }
        ImageView imageView = convertView.findViewById(R.id.grid_image);
        TextView textView = convertView.findViewById(R.id.item_name);

        //change name according to the position

        imageView.setImageResource(image[position]);
        textView.setText(phoneName[position]);

        return convertView;
    }
}
