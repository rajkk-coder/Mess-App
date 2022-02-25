package com.ooslab.messfee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity2 extends AppCompatActivity {
    BillAdapter billAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
        FloatingActionButton floatingActionButton=findViewById(R.id.add_person_fab);

        RecyclerView recyclerView=findViewById(R.id.rv1);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference()
                .child("HostelD").child("Bill");
        Query query=ref.orderByKey();
        /*Query query =ref.orderByChild("name");*/
        FirebaseRecyclerOptions<Order> options =
                new FirebaseRecyclerOptions.Builder<Order>()
                        .setQuery(query, Order.class)
                        .build();
        if(options==null) {
            Toast.makeText(MainActivity2.this,"List is Empty",Toast.LENGTH_LONG).show();
        }
        billAdapter = new BillAdapter(options);

        recyclerView.setAdapter(billAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,Menu.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        billAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        billAdapter.stopListening();
    }



    @Override
    protected void onPostResume() {
        super.onPostResume();
        billAdapter.startListening();
    }
}