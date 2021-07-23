package com.example.grabitandgo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextView registerBtn;
    private Button loginBtn;
    private EditText emailText;
    private EditText passwordText;

    private FirebaseAuth mAuth;

    private LoginActivity thisActivity=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        registerBtn =findViewById(R.id.register_btn);
        loginBtn =findViewById(R.id.login_btn);
        emailText =findViewById(R.id.email_text);
        passwordText =findViewById(R.id.password_text);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAuth.signInWithEmailAndPassword(emailText.getText().toString(), passwordText.getText().toString())
                        .addOnCompleteListener(thisActivity, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

                                    App.user = mAuth.getCurrentUser();

                                    openHomePage(App.user.getUid());

                                    System.out.println(App.user.getEmail());
                                } else {
                                    // If sign in fails, display a message to the user.

                                    System.out.println("Failed to login");
                                }

                            }
                        });
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { openRegisterActivity();}
        });
    }
    private void openHomePage(String userid){
        Intent intent = new Intent(this, MainActivity.class);
        Bundle b = new Bundle();
        b.putString("user", userid); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
    }

    private void openRegisterActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}