package com.example.grabitandgo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        Order o = (Order) getIntent().getExtras().get("order");
        System.out.println(o.getCoffee_id());

        tvTimer1 =findViewById(R.id.tv_timer1);

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
                                String time = t1Hour + "=" + t1Minute;
                                //Initialize 24 hours time format
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );

                                try {
                                    Date date = f24Hours.parse(time);

                                    //Initialize 12 hours time format
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    tvTimer1.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
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
}