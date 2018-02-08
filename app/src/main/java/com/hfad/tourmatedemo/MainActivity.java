package com.hfad.tourmatedemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailEt, passwordEt;
    private Button signIn,signUp;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEt=findViewById(R.id.signInEmail);
        passwordEt=findViewById(R.id.signInpassword);
        progressBar=findViewById(R.id.sProgressbar);
        mAuth = FirebaseAuth.getInstance();

        signIn=findViewById(R.id.signinButton);
        signUp=findViewById(R.id.signupButton);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEt.getText().toString();
                String password=passwordEt.getText().toString();

                if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){

                    emailEt.setError("Email is not valid");
                    emailEt.requestFocus();
                    return;
                }
                if(password.isEmpty()||password.length()<6){

                    passwordEt.setError("password can't be empty and less then 6 charecter");
                    passwordEt.requestFocus();
                    return;

                }

                progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);

                                if(task.isSuccessful()){

                                    finish();

                                    Toast.makeText(MainActivity.this, "Welcome to your Event", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MainActivity.this,EventActivity.class));
                                }else{

                                    Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SignUpActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

            finish();
            startActivity(new Intent(MainActivity.this,EventActivity.class));
        }else{

            Toast.makeText(this, "please sign in to your account", Toast.LENGTH_SHORT).show();
        }
    }
}
