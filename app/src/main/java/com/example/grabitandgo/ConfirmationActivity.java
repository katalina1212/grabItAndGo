package com.example.grabitandgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

public class ConfirmationActivity extends AppCompatActivity {

    private ImageView imageView12;
    private TextView item_name;
    private TextView qty;
    private TextView item_price;
    private TextView reward_point;
    private TextView collection_time;
    private Button confirm_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        imageView12 =findViewById(R.id.imageView12);
        item_name =findViewById(R.id.item_name);
        qty =findViewById(R.id.qty);
        item_price =findViewById(R.id.item_price);
        reward_point =findViewById(R.id.reward_point);
        collection_time =findViewById(R.id.collection_time);
        confirm_btn =findViewById(R.id.confirm_btn);

        Order o = (Order) getIntent().getExtras().get("order");
        item_name.setText(o.getCoffeeName());
        qty.setText(String.valueOf(o.getQty()));
        reward_point.setText(String.valueOf(o.getQty()));
        collection_time.setText(o.getPickUpDateDate().toString());
        item_price.setText(String.valueOf(o.getPrice()));

        Picasso.with(this).load(o.getCoffeeImage()).into(imageView12);

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCheckoutActivity(o);
            }
        });


        //Initialize and assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
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
                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });
    }

    private void openCheckoutActivity(Order o){
        Intent intent = new Intent(this, CheckoutActivity.class);
        // Bundle b = new Bundle();
        intent.putExtra("order", o); //Your id
        // intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
    }
    }
