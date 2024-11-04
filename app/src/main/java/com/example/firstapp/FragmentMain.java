package com.example.firstapp;


import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


public class FragmentMain extends Fragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = ((MainActivity) getActivity()).MainToolBar;

        // Set toolbar title
        toolbar.setTitle("Dashboard");

        // Clear previous menu items
        toolbar.getMenu().clear();

        // Inflate the menu to ensure the action_location item is present
        toolbar.inflateMenu(R.menu.shared_menu);

        // Show location icon in toolbar menu
        MenuItem locationIcon = toolbar.getMenu().findItem(R.id.action_location);
        if (locationIcon != null) {
            locationIcon.setVisible(true);
        }

        toolbar.setNavigationIcon(R.drawable.logout_with_background);
        toolbar.setNavigationOnClickListener(v -> {
            // Show a logout confirmation dialog instead of navigating
            new AlertDialog.Builder(getContext())
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to logout?")
                    .setPositiveButton("Yes", (dialog, which) -> ((MainActivity) getActivity()).handleLogout())
                    .setNegativeButton("No", null)
                    .show();
        });

        // Set menu item click listener for location
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_location) {
                ((MainActivity) getActivity()).handleLocation();
                return true;
            }
            return false;
        });


        GridView gridView = view.findViewById(R.id.gridView);

        //grid items + redirect
        String[] phonesName = {"iphone16", "iphone15", "iphone10", "iphone12"};
        int[] phonesImages = {R.mipmap.iphone16, R.mipmap.iphone15, R.mipmap.iphonex, R.mipmap.iphone12};
        String name = "Andy";

        GridAdapter gridAdapter = new GridAdapter(getContext(), phonesName, phonesImages);
        gridView.setAdapter(gridAdapter);

        String[][] specs = {
                { " 6.1″ display", "Apple A18 chipset, 512 GB storage"},
                { "48MP camera", "Starts at $799"},
                { "RAM: 3 GB", "Battery: 2716 mAh, Li-Ion"},
                { "Battery: 2,815 mAh", "Weight: 5.78 ounces"}
        };


        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            Fragment fragmentList = new FragmentList();

            // Prepare the data to be passed
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            bundle.putStringArray("specs", specs[position]); // Check that specs[position] exists
            bundle.putString("phone_name", phonesName[position]); // Check that phonesName[position] exists

            fragmentList.setArguments(bundle);

            // Navigate with the data
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.FragmentLayout, fragmentList);
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