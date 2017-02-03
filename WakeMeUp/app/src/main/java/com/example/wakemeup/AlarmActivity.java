package com.example.wakemeup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/*
 * Class that is started when alarm occurs
 */
public class AlarmActivity extends Activity {

    private TextToSpeech reader;
    private Voice myVoice;
    private String toBeRead = "Good morning Manyu. ";
    private Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        //setting all these flags allows us to start the activity from almost any state
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        //Need to delay a quick second, or else for some reason the screen doesn't always wake
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                playVideo();
            }
        }, 1000);

        // After you come back to the app from the youtube video, you can click this button
        // to have it read your google calendar
        go = (Button) findViewById(R.id.button2);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go.setClickable(false);
                readCal();
                go.setClickable(true);
            }
        });


    }

    /*
     * Function to start youtube app and play video.
     * I want to make this video dynamic later
     */
    public void playVideo() {
        //maxes out the media volume before playing video
        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        am.setStreamVolume(
                AudioManager.STREAM_MUSIC,
                am.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
                0);
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=R0XjwtP_iTY")));
    }

    /*
     * Function to read your google calendar events out loud
     */
    public void readCal() {
        //maxes out media volume before reading calendar
        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        am.setStreamVolume(
                AudioManager.STREAM_MUSIC,
                am.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
                0);
        //Start new activity to querry GCal api and return results
        Intent intent = new Intent(this, UserCal.class);
        startActivityForResult(intent, 99);

    }

    /**
     * Function called when UserCal activty returns values
     * @param requestCode the code that should match the code used to start the activty
     * @param resultCode the code returned by the activiy that should be RESULT_OK
     * @param data the data returned by the UserCal activity
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //toBeRead is the string that will be read all at once when ready to read
        Calendar now = Calendar.getInstance();
        toBeRead += "Today is  " + now.get(Calendar.DAY_OF_WEEK) + " " + now.get(Calendar.MONTH) +
                " " + now.get(Calendar.DAY_OF_MONTH) + " , and you have ";
        ArrayList<String> events;
        int numEvents;
        if (requestCode == 99 && resultCode == RESULT_OK) {
            events = data.getStringArrayListExtra("Events");
            numEvents = events.size();
            if (numEvents == 0) {
                toBeRead += "nothing planned. Enjoy your day off, my love.";
            } else if (numEvents == 1) {
                toBeRead += "one activity planned, and it is ";
            } else {
                toBeRead += Integer.toString(numEvents) + " events planned. First you have ";
            }

            for (String event : events) {
                toBeRead += event.substring(0, event.indexOf('(')) + ", followed by ";
            }

            toBeRead = toBeRead.substring(0, toBeRead.length() - 13);
            toBeRead += " . Now get out of bed and get to work, you fucking lazy dumbass. Just kidding" +
                    " love you. Kappa.";

            /*
             * This is the object that will let us actually speak
             */
            reader = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int i) {
                    if (i == TextToSpeech.SUCCESS) {
                        myVoice = new Voice("Lmao",
                                Locale.ENGLISH,
                                Voice.QUALITY_VERY_HIGH,
                                Voice.LATENCY_VERY_LOW,
                                false,
                                null);
                        reader.setVoice(myVoice);
                        reader.setLanguage(Locale.ENGLISH);
                        reader.speak(AlarmActivity.this.toBeRead, TextToSpeech.QUEUE_ADD, null, "one");
                        System.out.println(toBeRead);

                    }

                }
            });
        }



    }

}
