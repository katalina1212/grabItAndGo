package com.example.grabitandgo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameText;
    private Button registerBtn;
    private Button loginBtn;
    private EditText emailText;
    private EditText passwordText;
    private EditText password2Text;

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
    }
}