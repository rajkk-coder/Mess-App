package com.ooslab.messfee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Menu extends AppCompatActivity {

    MenuAdapter menuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        RecyclerView recyclerView=findViewById(R.id.rv3);

        recyclerView.setLayoutManager(new LinearLayoutManager(Menu.this));

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("HostelD").child("items");
        Query query=ref.orderByKey();
        FirebaseRecyclerOptions<Item> options =
                new FirebaseRecyclerOptions.Builder<Item>()
                        .setQuery(query, Item.class)
                        .build();
        if(options==null) {
            Toast.makeText(Menu.this,"Mar ja",Toast.LENGTH_LONG).show();
        }
        menuAdapter = new MenuAdapter(options,"HostelD");

        recyclerView.setAdapter(menuAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        menuAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        menuAdapter.stopListening();
    }
}