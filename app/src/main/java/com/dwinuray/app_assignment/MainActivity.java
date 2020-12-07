package com.dwinuray.app_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        NavController navController  = Navigation.findNavController(this, R.id.fragment);
        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);

        // render
        NavigationUI.setupWithNavController(navView, navController);
    }
}
