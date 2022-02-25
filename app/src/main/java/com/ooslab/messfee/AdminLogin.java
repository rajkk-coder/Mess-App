package com.ooslab.messfee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {
    private EditText username, password;
    private Button login;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        username=findViewById(R.id.editUser);
        password=findViewById(R.id.editPassword);
        login=findViewById(R.id.btnLogin);
        auth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=username.getText().toString();
                String pwd=password.getText().toString();
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pwd)){
                    auth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(AdminLogin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(AdminLogin.this,EnterDetail.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(AdminLogin.this, "Provide details", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}