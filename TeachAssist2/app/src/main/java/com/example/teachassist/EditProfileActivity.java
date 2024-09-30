package com.example.teachassist;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Button saveChangesButton = findViewById(R.id.btn_save_changes);
        saveChangesButton.setOnClickListener(v -> {
            // Logic to save changes (e.g. saving to database)
            Toast.makeText(EditProfileActivity.this, "Changes saved!", Toast.LENGTH_SHORT).show();
            finish();  // Go back to profile screen after saving
        });
    }
}
