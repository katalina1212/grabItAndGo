package com.example.grabitandgo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private TextView registerBtn;
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

        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openHomePage();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { openRegisterActivity();}
        });
    }
    private void openHomePage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openRegisterActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}