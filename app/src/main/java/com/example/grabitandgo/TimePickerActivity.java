package com.example.grabitandgo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimePickerActivity extends AppCompatActivity {

    TextView tvTimer1;
    int t1Hour, t1Minute;
    private Button confirm_time_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        Order o = (Order) getIntent().getExtras().get("order");
        System.out.println(o.getCoffee_id());

        tvTimer1 =findViewById(R.id.tv_timer1);
        confirm_time_btn =findViewById(R.id.confirm_time_btn);

        confirm_time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfirmationActivity(o);
            }
        });

        Date date = new Date();
        t1Hour= date.getHours();
        t1Minute = date.getMinutes();


        tvTimer1.setOnClickListener(new View.OnClickListener(){


            @Override

            public void onClick(View v){
                //initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        TimePickerActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //initialize hour and minute
                                t1Hour = hourOfDay;
                                t1Minute = minute;

                                //Store hour and minute in string
                                String time = t1Hour + ":" + t1Minute;
                                //Initialize 24 hours time format
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );

                                try {
                                    Date parsedDate = f24Hours.parse(time);
                                    Date date = new Date();
                                    date.setHours(parsedDate.getHours());
                                    date.setMinutes(parsedDate.getMinutes());
                                    date.setSeconds(parsedDate.getSeconds());
                                    o.setPickUpDate(date);


                                    //Initialize 12 hours time format
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    tvTimer1.setText(f12Hours.format(parsedDate));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,true
                );
                //Set transparent background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Display previous selected time
                timePickerDialog.updateTime(t1Hour, t1Minute);
                //Show dialog
                timePickerDialog.show();
            }
        }

        );
    }
    private void openConfirmationActivity(Order o){
        Intent intent = new Intent(this, ConfirmationActivity.class);
        // Bundle b = new Bundle();
        intent.putExtra("order", o); //Your id
        // intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
    }
}