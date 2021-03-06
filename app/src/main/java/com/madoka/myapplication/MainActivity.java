package com.madoka.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;//a new variable was running records whether the stopwatch was running before the onStop() method was called
                               //so that we know whether to set it running again when the activity becomes visible again


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds); ///saving the instance of a class
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

        runTimer();//We want the runTimer() method to start running when StopwatchActivity gets created, so we’ll call it in the activity onCreate() method:

    }


    @Override
    protected void onStop() {//We need to get the stopwatch to stop when the onStop() method is called.
        super.onStop();      //note there is big difference between onStop and onDestroy//here we made the invisible activity due to phonecall etc interruptions i.e activity is no longer visible there is interruption but we have NOT destroyed it
        wasRunning = running; //Record whether the stopwatch was running when the onStop() method was called
        running = false;     //We need to get the stopwatch to stop when the onStop() method is
                             //called. To do this, we need to set the value of the running boolean to false.
    }


    @Override
    protected void onStart() {
        super.onStart();  //Implement the onStart() method. If the stopwatch was running, set it running again.
        if (wasRunning) {
            running = true;
        }
    }
/*
* @Override
protected void onPause() {
super.onPause();
wasRunning = running;
running = false;
}
@Override
protected void onResume() {
super.onResume();
if (wasRunning) {
running = true;
}
}
* */

    public void onClickStart(View view) { //when button click...NOTE THIS ONCLICK AND  onStart() ARE NOT EVEN CLOSE TO BEING TE SAME
        running=true;//start the stopwatch running
    }

    public void onClickStop(View view) {

        running=false; //stop the stop watch running
    }

    public void onClickReset(View view) {

        running=false; //stop the stop watch running and set the seconds to zero
        seconds=0;
    }



    private void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();//create a new Handler A Handler is an Android class you can use to schedule code that should be run at some point in the future.
        handler.post(new Runnable() {//Call the post() method, passing in a new Runnable. The post()method processes codes without a delay, so the code in theRunnable will run almost immediately.
            @Override
            public void run() {

        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        String time = String.format("%d:%02d:%02d",hours, minutes, secs); //The Runnable run() method contains the code you want to be run—in our case, the code to update the text view.

        timeView.setText(time); //set the textview text to take time

        if (running) {//if running is true increment the seconds
            seconds++;
        }
                handler.postDelayed(this, 1000); //Post the code in the Runnable to be run again after a delay of 1,000 milliseconds, or 1 second.As this line of code is included in the Runnable run() method, this will keep getting called.
            }
        });
    }
}
/*
*Using the post() and postDelayed() methods in this way means
that the code will run as soon as possible after the required delay, which
in practice means almost immediately.
*
*
*
*
*
* */