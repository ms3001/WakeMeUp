package com.example.wakemeup;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    private AlarmManagerBroadcastReceiver alarm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarm = new AlarmManagerBroadcastReceiver();
    }

    public void onetimeTimer(View view){

        Context context = this.getApplicationContext();
        if(alarm != null){
            alarm.setOnetimeTimer(context);
            sendMessage();
        }else{
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendMessage() {
        Intent intent = new Intent(this, PassiveActivity.class);
        TimePicker whatTime = (TimePicker) findViewById(R.id.timePicker);
        int hour =  whatTime.getHour();
        int min = whatTime.getMinute();
        String msg = hour + ":" + min;
        intent.putExtra("AlarmTime", msg);
        startActivity(intent);
    }


}
