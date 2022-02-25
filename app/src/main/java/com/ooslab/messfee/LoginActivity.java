package com.ooslab.messfee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private EditText user,password;
    private String message, username;
    Button button;
    private FirebaseAuth auth;

    private String getEmail(){
        final String[] eml = {""};
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference()
                .child(username)
                .child("email");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                eml[0] =snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return eml[0];
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        user= findViewById(R.id.editUser);
        password= findViewById(R.id.editPassword);
        button= findViewById(R.id.btnLogin);
        Bundle bundle=getIntent().getExtras();
        message= bundle.getString("category");

        button.setOnClickListener(view -> {

            username=user.getText().toString();
            String pwd=password.getText().toString();
            if(TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)){
                Toast.makeText(LoginActivity.this,"username or password is not provided",Toast.LENGTH_LONG).show();
            }else {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                        .child(username)
                        .child("email");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String eml = snapshot.getValue().toString();
                        if (!TextUtils.isEmpty(pwd)) {

                            auth.signInWithEmailAndPassword(eml, pwd)
                                    .addOnCompleteListener(LoginActivity.this, task -> {
                                        if(task.isSuccessful()){
                                       FirebaseDatabase.getInstance().getReference().child(username)
                                               .addValueEventListener(new ValueEventListener() {
                                                   @Override
                                                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                       User usr=snapshot.getValue(User.class);
                                                       Intent intent;
                                                       if (message.equals("student")) {

                                                           intent = new Intent(LoginActivity.this, StudentDashboard.class);
                                                       } else {

                                                           intent = new Intent(LoginActivity.this, StaffDashboard.class);
                                                       }
                                                       intent.putExtra("user",usr);
                                                       startActivity(intent);
                                                   }

                                                   @Override
                                                   public void onCancelled(@NonNull DatabaseError error) {

                                                   }
                                               });

                                        } else
                                            Toast.makeText(LoginActivity.this, "Enter valid username or password", Toast.LENGTH_SHORT).show();
                                    });

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(LoginActivity.this, "Error !!", Toast.LENGTH_LONG).show();

                    }
                });
            }

        });
    }
}