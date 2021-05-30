package com.example.grabitandgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameText;
    private Button registerBtn;
    private TextView loginBtn;
    private EditText emailText;
    private EditText passwordText;
    private EditText password2Text;

    //private FirebaseAuth mAuth;

    public RegisterActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameText = findViewById(R.id.username_text);
        registerBtn=findViewById(R.id.register_btn);
        loginBtn=findViewById(R.id.login_btn);
        emailText=findViewById(R.id.email_text);
        passwordText=findViewById(R.id.password_text);
        password2Text=findViewById(R.id.password2_text);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openTermsConditionsActivity();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openLoginActivity();
            }
        });
    }
    private void openTermsConditionsActivity(){
        Intent intent = new Intent(this, TermsConditionsActivity.class);
        startActivity(intent);
    }

    private void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}