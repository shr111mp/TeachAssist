package com.example.teachassist;

import static com.example.teachassist.R.id.nav_home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.example.teachassist.R;


public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private View signout;
    private TextView welcomeText;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Handle window insets for full screen compatibility
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Firebase authentication
        mAuth = FirebaseAuth.getInstance();
        signout = findViewById(R.id.buttonLogOut);
        welcomeText = findViewById(R.id.textWelcome);
        String uid = mAuth.getCurrentUser().getUid();
        welcomeText.setText("Welcome, " + uid);

        // Handle signout
        signout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            finish();
        });

        // Setup bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    // Stay in HomeActivity
                    return true;
                case R.id.nav_settings:
                    startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.nav_profile:
                    startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
    }
