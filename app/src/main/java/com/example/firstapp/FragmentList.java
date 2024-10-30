package com.example.firstapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class FragmentList extends Fragment {
    private View FView;
    private ListView listView;
    private String[] specs;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = ((MainActivity)getActivity()).MainToolBar;
        toolbar.getMenu().clear();

        toolbar.setNavigationIcon(R.drawable.back_button_background);
        toolbar.setNavigationOnClickListener(v -> {
            ((MainActivity)getActivity()).handleBackBtn();
        });

        MenuItem item1 = toolbar.getMenu().findItem(R.id.action_location);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_location){
                ((MainActivity)getActivity()).handleLocation();
                return true;
            }
            return false;
        });

        Bundle bundle = getArguments();
        if (bundle != null){
            specs = bundle.getStringArray("specs");
            String phoneName = bundle.getString("phonesName");
            toolbar.setTitle(phoneName);
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
