package com.example.firstapp;

import android.os.Bundle;
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

public class FragmentFirst extends Fragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = ((MainActivity)getActivity()).MainToolBar;
        toolbar.setTitle("Login");

        EditText textUsername = view.findViewById(R.id.userName);
        EditText textPassword = view.findViewById(R.id.password);
        Button loginButton = view.findViewById(R.id.login_Button);
        Button registerButton = view.findViewById(R.id.register_Button);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());

        loginButton.setOnClickListener(v -> {
            String username = textUsername.getText().toString().trim();
            String password = textPassword.getText().toString().trim();

            if (dataBaseHelper.checkUser(username,password)){
                dataBaseHelper.updateLastLogin(username);

                // Navigate to FragmentMain on successful login
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                Fragment fragmentMain = new FragmentMain();
                ft.addToBackStack(null);
                ft.replace(R.id.FragmentLayout, fragmentMain);
                ft.commit();
            }
            else {
                Toast.makeText(getContext(),"You have to register.",Toast.LENGTH_SHORT).show();
            }
        });

        registerButton.setOnClickListener(v -> {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            Fragment fragmentRegister = new FragmentRegister();
            ft.addToBackStack(null);
            ft.replace(R.id.FragmentLayout, fragmentRegister);
            ft.commit();
        });
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }
}
