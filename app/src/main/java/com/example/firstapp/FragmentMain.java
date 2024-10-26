package com.example.firstapp;

import static android.content.Intent.ACTION_VIEW;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class FragmentMain extends Fragment {
    private View FView;
    private GridView gridView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = ((MainActivity)getActivity()).MainToolBar;
        toolbar.setTitle("Dashboard");

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_logout) {
                // Handle logout action
            } else if (item.getItemId() == R.id.action_location) {
                // Handle location action
            }
            return true;
        });


        gridView = view.findViewById(R.id.gridView);

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


        gridView.setOnItemClickListener((parent, view1, position, id)->{

                Fragment FragmentList = new FragmentList();

               //prepare the data  to be passed
                Bundle bundle = new Bundle();
                bundle.putInt("positon",position);
                bundle.putStringArray("specs",specs[position]);

                //prepare the bundle to be passed
                FragmentList.setArguments(bundle);

                //navigate with the data
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.FragmentLayout,FragmentList);
                transaction.addToBackStack(null);
                transaction.commit();
        });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);

    }

}