package com.example.wakemeup;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;


public class MainActivity extends AppCompatActivity {


    private AlarmManagerBroadcastReceiver alarm;
    private TextToSpeech reader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarm = new AlarmManagerBroadcastReceiver();
    }

    public void onetimeTimer(View view){
        TimePicker whatTime = (TimePicker) findViewById(R.id.timePicker);
        whatTime.
        System.out.println("AYyyyyyy");
        Context context = this.getApplicationContext();
        if(alarm != null){
            alarm.setOnetimeTimer(context);
        }else{
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }


}
