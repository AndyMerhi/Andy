package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public Toolbar MainToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the toolbar
        MainToolBar = findViewById(R.id.toolbar);
        MainToolBar.setTitle("");
        MainToolBar.setTitleMarginStart(200);
        setSupportActionBar(MainToolBar);

        // Listen for back stack changes to manage back button visibility
        getSupportFragmentManager().addOnBackStackChangedListener(this::handleBackButtonVisibility);


        // Load the initial fragment
        loadFragment(new FragmentFirst());
    }

    // Method to load a fragment
    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FragmentLayout, fragment);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shared_menu, menu);
        return true;
    }

    public void handleLogout() {
        // Optionally, show a toast or dialog instead of navigating to FragmentFirst
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();


        loadFragment(new FragmentFirst());
    }

    public void handleLocation() {
        // Open Google Maps with a search query
        String search = "Phones shop";

        // geo:0,0 to set the current location
        String geoUri = "geo:0,0?q=" + Uri.encode(search);

        // Parse the URI
        Uri gmmIntentUri = Uri.parse(geoUri);

        // Create the intent to launch Google Maps
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        // Check if Google Maps is available
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    public void handleBackBtn() {
        //pop the fragment back stack
        getSupportFragmentManager().popBackStack();
    }

    private void handleBackButtonVisibility() {
        // Check if at the root fragment
        boolean isRootFragment = getSupportFragmentManager().getBackStackEntryCount() == 0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(!isRootFragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        handleBackBtn();
        return true;
    }
}
