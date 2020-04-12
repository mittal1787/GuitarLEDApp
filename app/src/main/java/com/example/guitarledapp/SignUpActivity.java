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

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        final Button signUp = findViewById(R.id.signUp);
        final String TAG = "SignupActivity";
        signUp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = ((EditText)findViewById(R.id.emailId)).getText().toString();
                        Log.i(TAG, email);
                        String password = ((EditText)findViewById(R.id.password)).getText().toString();
                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            Log.i(TAG, user.toString());
                                            Intent intent = new Intent(SignUpActivity.this, MusicActivity.class);
                                            startActivity(intent);
                                            Log.i(TAG, "Success");
                                        } else {
                                            Log.i(TAG, "Fail");
                                        }
                                    }
                                });
                    }
                }
        );
        final Button goBack = findViewById(R.id.goBack);
        goBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
