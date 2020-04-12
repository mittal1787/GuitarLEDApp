package com.example.guitarledapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user != null)
        {
            Intent intent = new Intent(MainActivity.this, MusicActivity.class);
            startActivity(intent);
        }
        else
        {
            final Button signUpButton = findViewById(R.id.signUp);
            signUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                    startActivity(intent);
                }
            });
            final Button loginButton = findViewById(R.id.login);
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = ((EditText) findViewById(R.id.email)).getText().toString();
                    Log.i("MainActivity", email);
                    String password = ((EditText) findViewById(R.id.password)).getText().toString();
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.i("MainActivity", String.valueOf(task.isSuccessful()));
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        System.out.println(user.toString());
                                        Intent intent = new Intent(MainActivity.this, MusicActivity.class);
                                        startActivity(intent);
                                    } else {
                                        System.out.println("Error");

                                    }
                                }
                            });
                }
            });
        }

    }
}
