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

public class ItemList extends AppCompatActivity {
    ItemAdapter itemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        RecyclerView recyclerView=findViewById(R.id.rv);
        User user=(User)getIntent().getSerializableExtra("user");
        recyclerView.setLayoutManager(new LinearLayoutManager(ItemList.this));

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference()
                .child(user.getUsername()).child("items");
        Query query=ref.orderByKey();
        /*Query query =ref.orderByChild("name");*/
        FirebaseRecyclerOptions<Item> options =
                new FirebaseRecyclerOptions.Builder<Item>()
                        .setQuery(query, Item.class)
                        .build();
        if(options==null) {
            Toast.makeText(ItemList.this,"List is Empty",Toast.LENGTH_LONG).show();
        }
        itemAdapter = new ItemAdapter(options);

        recyclerView.setAdapter(itemAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        itemAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        itemAdapter.stopListening();
    }
}