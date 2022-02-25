package com.ooslab.messfee;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

public class EnterDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_detail);
        EditText name=(EditText) findViewById(R.id.idName);
        EditText roll=(EditText)findViewById(R.id.idRollNumber);
        EditText email=(EditText)findViewById(R.id.idEmail);
        EditText contact=(EditText)findViewById(R.id.idNumber);
        EditText hostel=(EditText)findViewById(R.id.idHostel);
        String Name=name.getText().toString();
        String Email=email.getText().toString();
        String Contact=contact.getText().toString();
        String Roll=roll.getText().toString();
        String Hostel=hostel.getText().toString();

        Button btn=findViewById(R.id.addButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(Name) || !TextUtils.isEmpty(Roll) || !TextUtils.isEmpty(Email) ||
                        !TextUtils.isEmpty(Contact) || !TextUtils.isEmpty(Hostel)){
                    User user=new User(Roll,Name,Email,Contact,Hostel);
                    FirebaseDatabase.getInstance().getReference()
                            .push()
                            .setValue(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(EnterDetail.this, "Added", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(EnterDetail.this, "Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else{
                    Toast.makeText(EnterDetail.this, "Enter details", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}