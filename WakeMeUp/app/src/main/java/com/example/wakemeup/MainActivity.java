package com.example.wakemeup;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


/*
 * Class that app starts on.
 */
public class MainActivity extends AppCompatActivity {

    private AlarmManagerBroadcastReceiver alarm;
    private int hour, min;
    private int state = 0;
    public static String URL;
    public static String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarm = new AlarmManagerBroadcastReceiver();
    }

    /**
     * Function to get URL, name, and set alarm, called when button is clicked.
     * @param view the view that was clicked
     */
    public void onetimeTimer(View view){
        view.setClickable(false);
        TimePicker whatTime = (TimePicker) findViewById(R.id.timePicker);
        EditText url = (EditText) findViewById(R.id.editText);
        TextView msg = (TextView) findViewById(R.id.textView);
        EditText name = (EditText) findViewById(R.id.editText2);
        TextView msg2 = (TextView) findViewById(R.id.textView2);
        //URL & name has not been entered
        if (state == 1) {
            Context context = this.getApplicationContext();
            if (alarm != null) {
                hour = whatTime.getHour() % 12;
                min = whatTime.getMinute();
                //Currently sets the alarm to occur in hour hours and min minutes from current time
                alarm.setOnetimeTimer(context, hour, min);
                sendMessage();
            } else {
                Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
            }
            msg.setText("Please enter a youtube URL to wake up to:");
            url.setClickable(true);
            url.setVisibility(View.VISIBLE);
            msg2.setText("Please enter your first name:");
            name.setClickable(true);
            name.setVisibility(View.VISIBLE);
            whatTime.setClickable(false);
            whatTime.setVisibility(View.INVISIBLE);
            state = 0;

        } else {
            userName = url.getText().toString();
            msg.setText("Please select how long till the alarm should ring.");
            url.setClickable(false);
            url.setVisibility(View.INVISIBLE);
            msg2.setVisibility(View.INVISIBLE);
            name.setClickable(false);
            name.setVisibility(View.INVISIBLE);
            whatTime.setClickable(true);
            whatTime.setVisibility(View.VISIBLE);
            state = 1;
        }
        view.setClickable(true);
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
