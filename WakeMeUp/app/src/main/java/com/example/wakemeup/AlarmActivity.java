package com.example.wakemeup;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

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
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                playVideo();
            }
        }, 1000);
        Button go = (Button) findViewById(R.id.button2);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readCal();
            }
        });


    }

    public void playVideo() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=R0XjwtP_iTY")));

    }

    public void readCal() {

        // Get the events from your google calendar in some type of arrayList<String>


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
