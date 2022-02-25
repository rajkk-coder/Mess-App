package com.ooslab.messfee;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentDashboard extends AppCompatActivity {
    ImageView fee,notification,print,payment;
    TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        User user=(User)getIntent().getSerializableExtra("user");

        t1=findViewById(R.id.textView5);
        t2=findViewById(R.id.textView6);
        Button btn=findViewById(R.id.button);

        t1.setText(user.getName().toUpperCase());
        t2.setText("email : "+user.getEmail().toLowerCase());
        btn.setText("Contact : "+user.getContact()+"\n"+"Hostel : "+user.getHostel());

        fee=findViewById(R.id.fees);
        notification=findViewById(R.id.notify);
        payment=findViewById(R.id.payment);
        print=findViewById(R.id.printing);
        fee.setOnClickListener(view -> Toast.makeText(StudentDashboard.this, "hai app sab", Toast.LENGTH_SHORT).show());

        notification.setOnClickListener(view -> {
            startActivity(new Intent(StudentDashboard.this,ViewNotification.class));
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent intent=new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.onlinesbi.com/sbicollect/icollecthome.htm"));
                startActivity(intent);
            }
        });
        print.setOnClickListener(view -> Toast.makeText(StudentDashboard.this, "thanks", Toast.LENGTH_SHORT).show());

    }

}