package com.ooslab.messfee;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class StudentLogin extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private FirebaseAuth auth;
    private  String user;
    private Student student;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Button reg = findViewById(R.id.button2);
        auth = FirebaseAuth.getInstance();

        reg.setOnClickListener(V -> {
            user = username.getText().toString();
            String pwd = password.getText().toString();
            DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child(user);
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    student=snapshot.getValue(Student.class);
                    if (student != null && !TextUtils.isEmpty(pwd)) {
                        Toast.makeText(StudentLogin.this,student.getName()+" "+student.getHostel(),Toast.LENGTH_LONG).show();

                        auth.signInWithEmailAndPassword(student.getEmail(), pwd).addOnCompleteListener(StudentLogin.this, task -> {
                            if(task.isSuccessful()) {

                                Intent intent= new Intent(StudentLogin.this, StudentDashboard.class);
                                intent.putExtra("user",(Serializable) student);
                                Toast.makeText(StudentLogin.this,"n ", Toast.LENGTH_LONG).show();
                                startActivity(intent);
                                //startActivity(new Intent(StudentLogin.this, StudentDashboard.class));

                            }else
                                Toast.makeText(StudentLogin.this, "It's not fair", Toast.LENGTH_SHORT).show();
                        });
                        registration(student.getEmail(), pwd);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        });
    }
    private void registration(String email, String pwd){
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(pwd)) {
            Toast.makeText(StudentLogin.this, "Enter Correct  Data", Toast.LENGTH_SHORT).show();
        }
    }
}
