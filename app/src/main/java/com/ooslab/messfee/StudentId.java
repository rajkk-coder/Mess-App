package com.ooslab.messfee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentId extends AppCompatActivity {
    private EditText username;
    private Button button;
    private  User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_id);
        user=(User)getIntent().getSerializableExtra("user");
        username=findViewById(R.id.editTextTextPersonName);
        button=findViewById(R.id.button3);

        //Toast.makeText(StudentId.this, user.getUsername(), Toast.LENGTH_SHORT).show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=username.getText().toString();

                FirebaseDatabase.getInstance().getReference().child(user.getUsername())
                        .child("students").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            int cnt=0;
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                Detail detail=snapshot1.getValue(Detail.class);
                                if(name.toUpperCase().equals(detail.getId().toUpperCase())){
                                    String message=user.getUsername()+" "+name.toUpperCase();
                                    Intent intent =new Intent(StudentId.this,Billing.class);
                                    intent.putExtra("message",message);
                                    startActivity(intent);
                                    cnt=1;
                                }
                            }
                            if(cnt==0)
                            Toast.makeText(StudentId.this, "Student not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(StudentId.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}