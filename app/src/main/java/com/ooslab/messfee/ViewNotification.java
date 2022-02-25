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

public class ViewNotification extends AppCompatActivity {
    NotificationAdapter notificationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notification);
        RecyclerView recyclerView=findViewById(R.id.rv5);

        recyclerView.setLayoutManager(new LinearLayoutManager(ViewNotification.this));

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("B190528CS").child("notification");
        Query query=ref.orderByKey();
        /*Query query =ref.orderByChild("name");*/
        FirebaseRecyclerOptions<Notification> options =
                new FirebaseRecyclerOptions.Builder<Notification>()
                        .setQuery(query, Notification.class)
                        .build();
        if(options==null) {
            Toast.makeText(ViewNotification.this,"Mar ja",Toast.LENGTH_LONG).show();
        }
        notificationAdapter = new NotificationAdapter(options);

        recyclerView.setAdapter(notificationAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        notificationAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        notificationAdapter.stopListening();
    }
}