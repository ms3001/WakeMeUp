package com.example.wakemeup;

import android.app.Application;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import java.util.Locale;

public class AlarmActivity extends Activity {

    private TextToSpeech reader;
    private Voice myVoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        playVideo();


        readCal();


    }

    public void playVideo() {

    }

    public void readCal() {
        reader = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    myVoice = new Voice("Lmao",Locale.ENGLISH,Voice.QUALITY_VERY_HIGH,Voice.LATENCY_VERY_LOW,false,null);
                    reader.setVoice(myVoice);
                    reader.setLanguage(Locale.ENGLISH);
                    reader.speak("Hello world", TextToSpeech.QUEUE_ADD, null, "one");
                    System.out.println("hello");

                }

            }
        });
        //reader.shutdown();

    }

}
