package com.example.firstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
    private Context context;
    private String[] specs;

    public ListAdapter(Context context, String[] specs) {
        this.context = context;
        this.specs = specs;
    }

    @Override
    public int getCount() {
        return specs.length;
    }

    @Override
    public Object getItem(int position) {
        return specs[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(specs[position]);

        return convertView;
    }
}
