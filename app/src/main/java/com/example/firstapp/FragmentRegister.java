package com.example.firstapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.zip.Inflater;

public class FragmentRegister extends Fragment{
    private EditText userName, Password;
    private Button save;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userName.findViewById(R.id.userName);
        Password.findViewById(R.id.password);
        save.findViewById(R.id.save_button);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String password = Password.getText().toString();
                if (dataBaseHelper.checkUser(name, password)){
                    Toast.makeText(getContext(),"The user already registered.",Toast.LENGTH_SHORT).show();
                }
                else {
                    dataBaseHelper.insertUser(name,password);
                    Toast.makeText(getContext(),"Successfully registered!!",Toast.LENGTH_SHORT).show();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    Fragment FragmentFirst = new FragmentFirst();
                    ft.replace(R.id.FragmentLayout, FragmentFirst, null);
                    ft.commit();

                }
            }

            private FragmentManager getSupportFragmentManager() {
                return null;
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
