package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //todo this procedure is used everytime to switch fragments
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment FragmentFirst = new FragmentFirst();
        ft.replace(R.id.FragmentLayout, FragmentFirst, null);
        ft.commit();
    }

}