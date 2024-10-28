package com.example.firstapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentRegister extends Fragment{

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Toolbar toolbar =((MainActivity)getActivity()).MainToolBar;
        toolbar.getMenu().clear();
        toolbar.setTitle("Register");

        toolbar.setNavigationIcon(R.drawable.back_button_background);
        toolbar.setNavigationOnClickListener(v -> {
            ((MainActivity)getActivity()).handleBackBtn();
        });

        EditText EditUserName = view.findViewById(R.id.userName);
        EditText EditPassword = view.findViewById(R.id.password);
        Button BtnSave = view.findViewById(R.id.save_button);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());

        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = EditUserName.getText().toString().trim();
                String password = EditPassword.getText().toString().trim();
                if (dataBaseHelper.checkUser(name, password)){
                    Toast.makeText(getContext(),"The user already registered.",Toast.LENGTH_SHORT).show();
                }
                else {
                    dataBaseHelper.insertUser(name,password);
                    Toast.makeText(getContext(),"Successfully registered!!",Toast.LENGTH_SHORT).show();

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    Fragment fragmentFirst = new FragmentFirst();
                    ft.replace(R.id.FragmentLayout, fragmentFirst);
                    ft.commit();

                }
            }
        });
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}
