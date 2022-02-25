package com.ooslab.messfee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentList extends AppCompatActivity {
    RecyclerView recyclerView;
    StudentAdapter studentAdapter;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        user=(User)getIntent().getSerializableExtra("user");
        TextView textView=findViewById(R.id.hostelStudent);
        textView.setText("Student List :"+user.getHostel());
        recyclerView=findViewById(R.id.rv1);
        display();
    }

    private void display(){

        FirebaseDatabase.getInstance().getReference().child(user.getUsername())
                .child("students")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        final List<Detail>list=new ArrayList<>();
                            for(DataSnapshot snapshot1:snapshot.getChildren()) {
                                Detail detail = snapshot1.getValue(Detail.class);
                                list.add(detail);
                            }
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(StudentList.this));
                        studentAdapter=new StudentAdapter(StudentList.this,list);
                        recyclerView.setAdapter(studentAdapter);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
}