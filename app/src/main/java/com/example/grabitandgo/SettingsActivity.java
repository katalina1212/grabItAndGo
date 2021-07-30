package com.example.grabitandgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {

    private TextView username;
    private TextView termsConditionsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        username = findViewById(R.id.username);
        termsConditionsBtn = findViewById(R.id.termsConditionsBtn);
        username.setText(App.userName);

        //Initialize and assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.settings);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.connect:
                        startActivity(new Intent(getApplicationContext(), ConnectActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.rewards:
                        startActivity(new Intent(getApplicationContext(), RewardsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.orders:
                        startActivity(new Intent(getApplicationContext(), OrdersActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.settings:
                        return true;
                }

                return false;
            }
        });

        termsConditionsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openTermsConditionsActivity();
            }
        });
    }

    private void openTermsConditionsActivity(){
        Intent intent = new Intent(this, TermsConditionsActivity.class);
        startActivity(intent);
    }
}