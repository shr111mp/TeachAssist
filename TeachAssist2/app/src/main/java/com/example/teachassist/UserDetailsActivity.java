package com.example.teachassist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
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
    Button tangina;
    FirebaseAuth mAuth;
    FirebaseDatabase mDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_details);
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


        tangina = findViewById(R.id.TnginaButton);


        tangina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("TeachAssist").child("UserData");
                String username = editName.getText().toString();
                String grade = editGrade.getText().toString();
                String subject = editSubject.getText().toString();
                String uid = mAuth.getUid().toString();

                //Push Values into Hashmap
                HashMap<String, Object> UserData = new HashMap<>();
                UserData.put("UID", uid);
                UserData.put("Username", username);
                UserData.put("Grade Level", grade);
                UserData.put("subject", subject);
                //Push Hashmap values into Firebase Realtime Database
                myRef.push().setValue(UserData);

                //Start HomeActivity after pushing data
                Intent intent = new Intent(UserDetailsActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });





    }
}