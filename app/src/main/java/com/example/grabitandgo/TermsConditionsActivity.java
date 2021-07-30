package com.example.grabitandgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TermsConditionsActivity extends AppCompatActivity {

    private Button declineBtn;
    private Button acceptBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);

        declineBtn=findViewById(R.id.decline_btn);
        acceptBtn=findViewById(R.id.accept_btn);

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openMainActivity();
            }
        });

        declineBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openBackLoginActivity();
            }
        });
    }
    private void openMainActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void openBackLoginActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}