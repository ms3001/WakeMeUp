package com.example.wakemeup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/*
 * Class to look after alarm has been set.
 */
public class PassiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passive);
        //pull alarm time to display it on screen
        Intent intent = getIntent();
        String message = intent.getStringExtra("AlarmTime");
        TextView msg = (TextView) findViewById(R.id.textView2);
        msg.setText("The alarm will occur at " + message);
    }
}
