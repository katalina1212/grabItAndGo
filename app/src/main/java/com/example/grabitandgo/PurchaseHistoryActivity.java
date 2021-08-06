package com.example.grabitandgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchaseHistoryActivity extends AppCompatActivity {

    private ListView purchaseHistoryList;
    private PurchaseHistoryActivity thisActivity = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);
        purchaseHistoryList = findViewById(R.id.purchaseHistoryList);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Category");

        final List<CoffeeCategory> items = new ArrayList<>();

        // myRef.setValue("Hello, World!");
        // Read from the database

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    if (postSnapshot.getKey().equals("coffeeimage")) {
                        continue;
                    }
                    CoffeeCategory item = new CoffeeCategory(
                            postSnapshot.getKey(),
                            postSnapshot.child("coffeename").getValue(String.class),
                            postSnapshot.child("coffeeimage").getValue(String.class),
                            postSnapshot.child("coffeeprice").getValue(Double.class));

                    items.add(item);


                    // here you can access to name property like university.name

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Firebase", "Failed to read value.", error.toException());
            }
        });

        myRef = database.getReference("users/"+App.user.getUid()+"/orders");

        final List<Order> orderList = new ArrayList<>();

        // myRef.setValue("Hello, World!");
        // Read from the database

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    // public Order(String order_id, String coffee_id, Double price, int qty, int size, int sugar, int milk, Date orderDate, java.util.Date
                    //  pickUpDate, String coffeeName, String CoffeeImage, String userId, int freeQuantity)



//                        myRef.child("CoffeeId").setValue(o.getCoffee_id());
//                        myRef.child("CoffeeQty").setValue(o.getQty());
//                        myRef.child("CoffeeSize").setValue(o.getSize());
//                        myRef.child("CoffeeSugar").setValue(o.getSugar());
//                        myRef.child("CoffeeMilk").setValue(o.getMilk());
//                        myRef.child("CoffeePrice").setValue(o.getPrice());
//                        myRef.child("CoffeePickDate").setValue(o.getPickUpDate().getTime());
//                        myRef.child("CoffeeFreeQty").setValue(o.getFreeQuantity());

                    CoffeeCategory coffeeCategory = null;
                    for (CoffeeCategory c : items) {
                        if (c.getId().equals(postSnapshot.child("CoffeeId").getValue(String.class))) {
                            coffeeCategory = c;
                        }
                    }

                    Order item = new Order(
                            postSnapshot.getKey(),
                            postSnapshot.child("CoffeeId").getValue(String.class),
                            postSnapshot.child("CoffeePrice").getValue(Double.class),
                            postSnapshot.child("CoffeeQty").getValue(Integer.class),
                            postSnapshot.child("CoffeeSize").getValue(Integer.class),
                            postSnapshot.child("CoffeeSugar").getValue(Integer.class),
                            postSnapshot.child("CoffeeMilk").getValue(Integer.class),
                            new Date(Integer.parseInt(postSnapshot.getKey())),
                            new Date(postSnapshot.child("CoffeePickDate").getValue(Long.class)),
                            coffeeCategory.getName(),
                            coffeeCategory.getImage(),
                            App.user.getUid(),
                            postSnapshot.child("CoffeeFreeQty").getValue(Integer.class));

                    orderList.add(item);


                    // here you can access to name property like university.name

                }
                purchaseHistoryList.setAdapter(new CategoryListItemAdapter(thisActivity, orderList));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Firebase", "Failed to read value.", error.toException());
            }
        });

        //Initialize and assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.settings);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.connect:
                        startActivity(new Intent(getApplicationContext(), ConnectActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.rewards:
                        startActivity(new Intent(getApplicationContext(), RewardsActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.orders:
                        startActivity(new Intent(getApplicationContext(), OrdersActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.settings:
                        return true;
                }

                return false;
            }
        });
    }
}