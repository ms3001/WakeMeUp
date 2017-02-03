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


/*
 * Class that app starts on.
 */
public class MainActivity extends AppCompatActivity {

    private AlarmManagerBroadcastReceiver alarm;
    private int hour, min;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarm = new AlarmManagerBroadcastReceiver();
    }

    /**
     * Function to set an alarm, called when button is clicked.
     * @param view the view that was clicked
     */
    public void onetimeTimer(View view){

        Context context = this.getApplicationContext();
        if(alarm != null){
            TimePicker whatTime = (TimePicker) findViewById(R.id.timePicker);
            hour =  whatTime.getHour() % 12;
            min = whatTime.getMinute();
            //Currently sets the alarm to occur in hour hours and min minutes from current time
            alarm.setOnetimeTimer(context, hour, min);
            sendMessage();
        }else{
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Function to start a new activity (passive activity)
     */
    public void sendMessage() {
        TimePicker whatTime = (TimePicker) findViewById(R.id.timePicker);
        hour =  whatTime.getHour() % 12;
        min = whatTime.getMinute();
        //allows me to post what time the alarm will ring so I can display it on the next activity
        Date date = new Date(System.currentTimeMillis() + 3600000*hour + 60000*min);
        Format formatter = new SimpleDateFormat("HH:mm a");
        String dateFormatted = formatter.format(date);
        Intent intent = new Intent(this, PassiveActivity.class);
        intent.putExtra("AlarmTime", dateFormatted);
        startActivity(intent);
    }


}
