package com.ooslab.messfee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StaffDashboard extends AppCompatActivity {
    private TextView t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_dashboard);

        User user=(User)getIntent().getSerializableExtra("user");

        t1=findViewById(R.id.textView5);
        t2=findViewById(R.id.textView6);
        Button btn=findViewById(R.id.button);

        t1.setText(user.getName().toUpperCase());
        t2.setText("Contact : "+user.getContact());
        btn.setText("Hostel : "+user.getHostel());

        ImageView vmg= findViewById(R.id.imageView3);
        ImageView vmg3= findViewById(R.id.imageView4);
        ImageView vmg1= findViewById(R.id.imageView);
        ImageView imageView=findViewById(R.id.imageView1);
        vmg.setOnClickListener(view -> {
            Intent intent=new Intent(StaffDashboard.this, ItemList.class);
            intent.putExtra("user",user);
            startActivity(intent);
        });
        vmg3.setOnClickListener(view -> {

            Intent intent=new Intent(StaffDashboard.this,AddItemActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);
        });
        vmg1.setOnClickListener(view -> {
            Intent intent=new Intent(StaffDashboard.this, StudentList.class);
            intent.putExtra("user",user);
            startActivity(intent);
        });
        imageView.setOnClickListener(view -> {
            Intent intent=new Intent(StaffDashboard.this,StudentId.class);
            intent.putExtra("user",user);
            startActivity(intent);
        });
    }
}