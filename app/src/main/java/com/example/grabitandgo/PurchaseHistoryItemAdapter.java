package com.example.grabitandgo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;

import java.text.SimpleDateFormat;
import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;


public class PurchaseHistoryItemAdapter extends ArrayAdapter <Order> {
    public PurchaseHistoryItemAdapter(@NonNull Context context, @NonNull List<Order> objects) {
        super(context, 0, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Order currentItem = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_purchase_history_item, parent, false);
        TextView purchase_date = convertView.findViewById(R.id.purchase_date);
        ImageView qrCode_image = convertView.findViewById(R.id.qrCode_image);
        TextView purchase_price = convertView.findViewById(R.id.purchase_price);
        //categoryPrice.setText("$" + currentItem.getPrice());

        // ImageView categoryImage = convertView.findViewById(R.id.category_image);
        // Picasso.with(parent.getContext()).load(currentItem.getImage()).into(categoryImage);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
        purchase_date.setText(sdf.format(currentItem.getOrderDate()));
        purchase_price.setText("$ " + currentItem.getPrice());

        QRGEncoder qrgEncoder = new QRGEncoder(currentItem.getOrder_id(), null, QRGContents.Type.TEXT, 300);
        Bitmap bitmap;

        try {
            // Getting QR-Code as Bitmap
            bitmap = qrgEncoder.encodeAsBitmap();

            qrCode_image.setImageBitmap(bitmap);

        } catch (WriterException e) {

        }
       purchase_date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openQRCodeActivity(currentItem);

            }
        });

        qrCode_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openQRCodeActivity(currentItem);

            }
        });

        return convertView;
    }
    void openQRCodeActivity(Order o){
        Intent intent = new Intent(this.getContext(), QRCodeActivity.class);
        // Bundle b = new Bundle();
        intent.putExtra("order", o); //Your id
        // intent.putExtras(b); //Put your id to your next Intent
        getContext().startActivity(intent);
    }
    }

