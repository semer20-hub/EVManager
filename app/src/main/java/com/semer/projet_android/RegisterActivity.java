package com.semer.projet_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.semer.projet_android.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText username,password;
    Button btn;
    TextView login;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        btn=findViewById(R.id.register);
        login=findViewById(R.id.login);
        mauth = FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = username.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(email)){
                    username.setError("Email cannot be empty");
                    username.requestFocus();
                }else if(TextUtils.isEmpty(pass)){
                    password.setError("Password cannot be empty");
                    password.requestFocus();
                }else{
                    mauth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "User registred successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, SigninActivity.class));
                            }else{
                                Toast.makeText(RegisterActivity.this, "Registration failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, SigninActivity.class));
            }
        });
    }
}