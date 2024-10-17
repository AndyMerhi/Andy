package com.example.firstapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentFirst extends Fragment {
    private EditText textUsername, textPassword;
    private Button loginButton, registerButton;
    private DataBaseHelper dataBaseHelper;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textUsername = view.findViewById(R.id.userName);
        textPassword = view.findViewById(R.id.password);
        loginButton = view.findViewById(R.id.login_Button);
        registerButton = view.findViewById(R.id.register_Button);

        dataBaseHelper = new DataBaseHelper(getContext());

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = textUsername.getText().toString();
                String password = textPassword.getText().toString();

                if (dataBaseHelper.checkUser(username,password)){
                    dataBaseHelper.updateUSerLastLogin(username);

                    // Navigate to FragmentMain on successful login
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    Fragment fragmentMain = new FragmentMain();
                    ft.replace(R.id.FragmentLayout, fragmentMain);
                    ft.commit();
                }
                else {
                    Toast.makeText(getContext(),"You have to register.",Toast.LENGTH_SHORT).show();
                }
            }

        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                Fragment fragmentRegister = new FragmentRegister();
                ft.replace(R.id.FragmentLayout, fragmentRegister);
                ft.commit();
            }
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
