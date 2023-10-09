package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView imageView;
    ImageView health;
    ImageView childrenList;
    ImageView babyReport;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawableLayout);
        navigationView = findViewById(R.id.navigationView);
        imageView = findViewById(R.id.imageView55);
        health = findViewById(R.id.imageView11);
        childrenList = findViewById(R.id.imageView4);
        babyReport = findViewById(R.id.imageView44);
        toolbar = findViewById(R.id.toolbar);


        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle item clicks here
                int id = item.getItemId();
                if (id == R.id.login_menu) {
                    // Start the LoginActivity

                    Intent loginIntent = new Intent(getApplicationContext(), Login.class);
                    startActivity(loginIntent);
                    return true;
                } else if (id == R.id.logout_menu) {
                    // Handle logout click
                    // Perform logout actions and update UI accordingly
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.home_menu) {
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });



        childrenList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BabyListActivity.class);
                startActivity(intent);
            }
        });
        babyReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),BabyRport.class);
                startActivity(intent);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
            }
        });
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewAnnouncement.class);
                startActivity(intent);


            }
        });

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle  toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
}