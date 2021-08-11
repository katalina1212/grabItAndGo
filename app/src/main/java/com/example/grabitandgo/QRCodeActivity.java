package com.example.grabitandgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QRCodeActivity extends AppCompatActivity {

    ImageView qrimg;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    private Button location_btn;
    private Button contshop_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        Order o = (Order) getIntent().getExtras().get("order");

        qrgEncoder = new QRGEncoder(o.getOrder_id(), null, QRGContents.Type.TEXT, 300);

        try {
            // Getting QR-Code as Bitmap
            bitmap = qrgEncoder.encodeAsBitmap();

        } catch (WriterException e) {

        }

        qrimg=(ImageView)findViewById(R.id.qrcode);

        // Setting Bitmap to ImageView
        qrimg.setImageBitmap(bitmap);

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
                        startActivity(new Intent(getApplicationContext(), PurchaseHistoryActivity.class));
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


        location_btn=findViewById(R.id.location_btn);
        contshop_btn=findViewById(R.id.contshop_btn);



        location_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openConnectActivity();
            }
        });

        contshop_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openCoffeeSelectionActivity();
            }
        });
    }
    private void openConnectActivity(){
        Intent intent = new Intent(this, ConnectActivity.class);
        startActivity(intent);
    }

    private void openCoffeeSelectionActivity(){
        Intent intent = new Intent(this, CoffeeSelectionActivity.class);
        startActivity(intent);
    }

    }

