package com.example.firstapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentList extends Fragment {
    private View FView;
    private ListView listView;
    private String[] specs;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null){
            specs = bundle.getStringArray("specs");
        }

        listView = view.findViewById(R.id.listview);

        if (specs != null) {
            ListAdapter listAdapter = new com.example.firstapp.ListAdapter(getContext(), specs);
            listView.setAdapter(listAdapter);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return FView = inflater.inflate(R.layout.list_view, container, false);

    }




}
