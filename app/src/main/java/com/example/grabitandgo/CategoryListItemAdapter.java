package com.example.grabitandgo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class CategoryListItemAdapter extends ArrayAdapter<CoffeeCategory> {
    public CategoryListItemAdapter(@NonNull Context context, @NonNull List<CoffeeCategory> objects) {
        super(context, 0, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final CoffeeCategory currentItem = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_list_item, parent, false);
        TextView categoryName = convertView.findViewById(R.id.category_name);

        ImageView categoryImage = convertView.findViewById(R.id.category_image);
        //Picasso.with(parent.getContext()).load(currentItem.getImage()).into(categoryImage);


        categoryName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openMenu(getContext(), currentItem);

            }
        });

        categoryImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openMenu(getContext(), currentItem);

            }
        });

        categoryName.setText(currentItem.getName());
        return convertView;


    }

    private void openMenu(Context context, CoffeeCategory category) {
        Intent intent = new Intent(context, SelectionItemActivity.class);
        Bundle b = new Bundle();
        b.putString("name", category.getName()); //Your id
        b.putString("image", category.getImage()); //Your id
        b.putDouble("price", category.getPrice()); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        context.startActivity(intent);
    }
}