package com.example.wakemeup;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    private AlarmManagerBroadcastReceiver alarm;
    private int hour, min;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarm = new AlarmManagerBroadcastReceiver();
    }

    public void onetimeTimer(View view){

        Context context = this.getApplicationContext();
        if(alarm != null){
            TimePicker whatTime = (TimePicker) findViewById(R.id.timePicker);
            hour =  whatTime.getHour() % 12;
            min = whatTime.getMinute();
            alarm.setOnetimeTimer(context, hour, min);
            sendMessage();
        }else{
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendMessage() {
        TimePicker whatTime = (TimePicker) findViewById(R.id.timePicker);
        hour =  whatTime.getHour() % 12;
        min = whatTime.getMinute();
        Date date = new Date(System.currentTimeMillis() + 3600000*hour + 60000*min);
        Format formatter = new SimpleDateFormat("HH:mm a");
        String dateFormatted = formatter.format(date);
        Intent intent = new Intent(this, PassiveActivity.class);
        intent.putExtra("AlarmTime", dateFormatted);
        startActivity(intent);
    }


}
