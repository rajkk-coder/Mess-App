package com.ooslab.messfee;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Billing extends AppCompatActivity {
    //private String Biller,billTo;
    BillAdapter billAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
        FloatingActionButton fbtn=findViewById(R.id.add_person_fab);
        //Bundle bundle=getIntent().getExtras();
        RecyclerView recyclerView=findViewById(R.id.rv1);
        //String str[]=bundle.getString("message").split(" ");
        //Biller=str[0];
        //billTo=str[1];
       // TextView textView=findViewById(R.id.textView13);
        //t
        // extView.setText("Roll no : "+billto);

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("HostelD").child("Bill");
        Query query=ref.orderByKey();
        FirebaseRecyclerOptions<Order> options =
                new FirebaseRecyclerOptions.Builder<Order>()
                        .setQuery(query, Order.class)
                        .build();
        if(options==null) {
            Toast.makeText(Billing.this,"Mar ja",Toast.LENGTH_LONG).show();
        }
        billAdapter = new BillAdapter(options);
        recyclerView.setAdapter(billAdapter);

        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Billing.this, Menu.class));
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

}