package com.example.grabitandgo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private Button registerBtn;
    private Button loginBtn;
    private EditText emailText;
    private EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerBtn =findViewById(R.id.register_btn);
        loginBtn =findViewById(R.id.login_btn);
        emailText =findViewById(R.id.email_text);
        passwordText =findViewById(R.id.password_text);
    }
}