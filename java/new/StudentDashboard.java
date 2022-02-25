package com.ooslab.messfee;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public  class StudentDashboard extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studashboard);
        TextView name = findViewById(R.id.nam);
        TextView roll = findViewById(R.id.roll);
        TextView hostel = findViewById(R.id.hostel);
        TextView email=findViewById(R.id.email);

        Intent intent=getIntent();
        Student st=(Student) intent.getSerializableExtra("user");
        hostel.setText(st.getHostel());
        name.setText(st.getName());
        roll.setText(st.getUsername());
        email.setText(st.getEmail());
        Toast.makeText(StudentDashboard.this,st.getEmail(), Toast.LENGTH_LONG).show();
    }
}
