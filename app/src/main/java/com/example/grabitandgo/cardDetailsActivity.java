package com.example.grabitandgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class cardDetailsActivity extends AppCompatActivity {

    private EditText cardholderText;
    private EditText cardNumberText;
    private EditText validText;
    private EditText cvvText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        getSupportActionBar().setTitle("Card details");

        cardholderText = findViewById(R.id.cardholder_text);
        cardNumberText = findViewById(R.id.cardnumber_text);
        validText = findViewById(R.id.valid_text);
        cvvText = findViewById(R.id.cvv_text);
        submitButton = findViewById(R.id.submit_btn);

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { trackingMethod();
            }
        });
    }

    public void trackingMethod() {
        System.out.println("Submit clicked");

        String s1 = cardholderText.getText().toString();
        int l1 = s1.length();

        String s2 = cardNumberText.getText().toString();
        int l2 = s2.length();

        String s3 = validText.getText().toString();
        int l3 = s3.length();

        String s4 = cvvText.getText().toString();
        int l4 = s4.length();


        if (l1 == 0 || l2 == 0 || l3 == 0 || l4 ==0) {
            Context context = getApplicationContext();
            CharSequence text = "Field cannot be empty";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();


        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}