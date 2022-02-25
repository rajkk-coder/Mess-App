package com.ooslab.messfee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        User user=(User)getIntent().getSerializableExtra("user");
        Button button=findViewById(R.id.addButton);
        EditText name=(EditText) findViewById(R.id.idName);
        EditText price=(EditText) findViewById(R.id.idPrice);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Item item=new Item(name.getText().toString(),price.getText().toString());
                FirebaseDatabase.getInstance()
                        .getReference().child(user.getUsername()).child("items")
                        .push().setValue(item).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            Toast.makeText(AddItemActivity.this, "Items Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
                name.setText(null);
                price.setText(null);
            }
        });
    }
}