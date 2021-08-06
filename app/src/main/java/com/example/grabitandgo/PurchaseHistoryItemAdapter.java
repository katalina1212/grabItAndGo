package com.example.grabitandgo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;

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

         purchase_date.setText(currentItem.getOrderDate().toString());
         purchase_price.setText("$ "+currentItem.getPrice());

          QRGEncoder qrgEncoder = new QRGEncoder(currentItem.getOrder_id(), null, QRGContents.Type.TEXT, 300);
          Bitmap bitmap;

           try {
               // Getting QR-Code as Bitmap
               bitmap = qrgEncoder.encodeAsBitmap();

               qrCode_image.setImageBitmap(bitmap);

           } catch (WriterException e) {

           }


           return convertView;
       }


   }