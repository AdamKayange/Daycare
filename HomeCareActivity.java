package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomeCareActivity extends AppCompatActivity {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    ImageView announcement;
    ImageView babyreport;
    TextView announ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_care);
        babyreport = findViewById(R.id.imageView4);
        announcement = findViewById(R.id.imageView11);
        drawerLayout = findViewById(R.id.drawableLayout);
        navigationView = findViewById(R.id.navigation55);
        toolbar = findViewById(R.id.toolbar);
        announ = findViewById(R.id.textView22);

        announ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Announcement.class);
                startActivity(intent);
            }
        });

        babyreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CildreportActivity.class);
                startActivity(intent);
            }
        });
        announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Announcement.class);
                startActivity(intent);
            }
        });

                NavigationView navigationView = findViewById(R.id.navigation55);
                navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.login_menu) {
                            // Handle login click
                            Intent loginIntent = new Intent(HomeCareActivity.this, LoginCaregiver.class);
                            startActivity(loginIntent);
                            // Close the drawer after starting the login activity
                            drawerLayout.closeDrawers();
                            return true;
                        } else if (id == R.id.logout_menu) {
                            // Handle logout click
                            // Perform logout actions and update UI accordingly
                            Intent intent = new Intent(HomeCareActivity.this, MainActivity.class);
                            startActivity(intent);
                            // Close the drawer after starting the main activity
                            drawerLayout.closeDrawers();
                            return true;
                        }
                        return false;
                    }
                });


        announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }
}