package com.hfad.tourmatedemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth sAuth;
    private EditText emailt, passwordt;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sAuth = FirebaseAuth.getInstance();

        emailt = findViewById(R.id.signUpemail);
        passwordt = findViewById(R.id.signUpPassword);
        progressBar=findViewById(R.id.progressbar);



    }

    public void signUp(View view) {

        String email = emailt.getText().toString();
        String password = passwordt.getText().toString();

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            emailt.setError("Email is not valid");
            emailt.requestFocus();
            return;
        }
        if(password.isEmpty()||password.length()<6){

            passwordt.setError("password can't be empty and more then 6 charecter");
            passwordt.requestFocus();
            return;

        }

        progressBar.setVisibility(View.VISIBLE);

        sAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressBar.setVisibility(View.GONE);

                        if(task.isSuccessful()){

                            finish();

                            Toast.makeText(SignUpActivity.this, "User added successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUpActivity.this,EventActivity.class));

                        }else{

                            Toast.makeText(SignUpActivity.this, ""+task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
