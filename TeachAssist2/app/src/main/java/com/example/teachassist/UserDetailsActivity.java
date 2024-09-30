package com.example.teachassist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class UserDetailsActivity extends AppCompatActivity {

    EditText editName, editGrade, editSubject;
    Button submitButton; // Renamed button
    FirebaseAuth mAuth;
    FirebaseDatabase mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        // Handle window insets for full screen compatibility
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mDB = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        editName = findViewById(R.id.editTextName);
        editGrade = findViewById(R.id.editTextGrade);
        editSubject = findViewById(R.id.editTextSubject);
        submitButton = findViewById(R.id.submitButton); // Updated

        // Handle button click to push data into Firebase
        submitButton.setOnClickListener(view -> {
            DatabaseReference myRef = mDB.getReference("TeachAssist").child("UserData");
            String username = editName.getText().toString();
            String grade = editGrade.getText().toString();
            String subject = editSubject.getText().toString();
            String uid = mAuth.getUid();

            // Push Values into HashMap
            HashMap<String, Object> UserData = new HashMap<>();
            UserData.put("UID", uid);
            UserData.put("Username", username);
            UserData.put("Grade Level", grade);
            UserData.put("Subject", subject);

            // Push HashMap values into Firebase Realtime Database
            myRef.push().setValue(UserData);

            // Start HomeActivity after pushing data
            startActivity(new Intent(UserDetailsActivity.this, HomeActivity.class));
            finish();
        });
    }
}
