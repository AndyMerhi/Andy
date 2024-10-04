package com.example.firstapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.net.SocketOption;

public class FragmentMain extends Fragment {
    private View FView;
    private GridView gridView;
    private ListView listView;
    private Button backButton;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = FView.findViewById(R.id.gridView);
        listView = FView.findViewById(R.id.listview);
        backButton = FView.findViewById(R.id.backButton);

        //grid items + redirect
        String[] phonesName = {"iphone16", "iphone15", "iphone10", "iphone12"};
        int[] phonesImages = {R.mipmap.iphone16, R.mipmap.iphone15, R.mipmap.iphonex, R.mipmap.iphone12};
        String name = "Andy";

        GridAdapter gridAdapter = new GridAdapter(getContext(), phonesName, phonesImages);
        gridView.setAdapter(gridAdapter);

        String[][] specs = {
                {"Details for iPhone 16", " 6.1″ display", "Apple A18 chipset, 512 GB storage"},
                {"Details for iPhone 15", "48MP camera", "Starts at $799"},
                {"Details for iPhone 10", "RAM: 3 GB", "Battery: 2716 mAh, Li-Ion"},
                {"Details for iPhone 12", "Battery: 2,815 mAh", "Weight: 5.78 ounces"}
        };
        listView.setVisibility(View.GONE);
        backButton.setVisibility(View.GONE);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setVisibility(View.VISIBLE);
                backButton.setVisibility(View.VISIBLE);
                gridView.setVisibility(View.GONE);
                System.out.println(name);
                ListAdapter listAdapter = new com.example.firstapp.ListAdapter(getContext(),specs[position]);
                listView.setAdapter(listAdapter);
            }
        });

        backButton.setOnClickListener(v -> {
            listView.setVisibility(View.GONE);
            backButton.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);

        });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FView = inflater.inflate(R.layout.fragment_main, container, false);
        return FView;
    }

}