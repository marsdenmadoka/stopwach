package com.madoka.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickStart(View view) {
        running=true;//start the stopwatch runnin
    }

    public void onClickStop(View view) {

        running=false; //stopthe stiop wathc runing
    }

    public void onClickReset(View view) {

        running=false; //stop the stop watch running and set the seconds to zero
        seconds=0;
    }



    private void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.time_view);

        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        String time = String.format("%d:%02d:%02d",hours, minutes, secs);

        timeView.setText(time); //set the textview text to take time

        if (running) {//if running is true increment the seconds
            seconds++;
        }
    }
}
