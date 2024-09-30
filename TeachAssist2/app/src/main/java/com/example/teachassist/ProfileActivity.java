package com.example.teachassist;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set the Profile item as checked by default
        bottomNavigationView.setSelectedItemId(R.id.nav_profile);

        // Handle item selection
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        // Go to HomeActivity
                        Intent homeIntent = new Intent(ProfileActivity.this, HomeActivity.class);
                        startActivity(homeIntent);
                        overridePendingTransition(0, 0); // No animation
                        return true;

                    case R.id.nav_settings:
                        // Go to SettingsActivity
                        Intent settingsIntent = new Intent(ProfileActivity.this, SettingsActivity.class);
                        startActivity(settingsIntent);
                        overridePendingTransition(0, 0); // No animation
                        return true;

                    case R.id.nav_profile:
                        // Stay in ProfileActivity
                        return true;
                }
                return false;
            }
        });
    }
}