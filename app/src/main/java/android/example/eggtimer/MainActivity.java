package android.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    TextView textViewCounter;
    SeekBar seekBarAlarmTime;
    Button buttonStartAlarm;
    Boolean counterIsActive = false;
    CountDownTimer timer;

    public void resetTimer()
    {
        textViewCounter.setText("0:00");
        seekBarAlarmTime.setEnabled(true);
        timer.cancel();
        seekBarAlarmTime.setProgress(0);
        buttonStartAlarm.setText("Start");
        counterIsActive = false;

    }

    public void onClickStartAlarm(View view)
    {
        if (counterIsActive == true)
        {

            resetTimer();

        }

        else {
            counterIsActive = true;
            seekBarAlarmTime.setEnabled(false);
            buttonStartAlarm.setText("Stop");

            timer = new CountDownTimer(seekBarAlarmTime.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {

                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.alarmsound);
                    mp.start();
                    resetTimer();
                    Log.i("Tag:", "Alarm Finished");

                }
            };

            timer.start();
        }
    }

    public void onClickStartAlarmRare(View view)
    {
        if (counterIsActive == true)
        {

            resetTimer();

        }

        else {
            counterIsActive = true;
            seekBarAlarmTime.setEnabled(false);
            buttonStartAlarm.setText("Stop");
            seekBarAlarmTime.setProgress(180);

            timer = new CountDownTimer(seekBarAlarmTime.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {

                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.alarmsound);
                    mp.start();
                    resetTimer();
                    Log.i("Tag:", "Alarm Finished");

                }
            };

            timer.start();
        }
    }

    public void onClickStartAlarmMedium(View view)
    {
        if (counterIsActive == true)
        {

            resetTimer();

        }

        else {
            counterIsActive = true;
            seekBarAlarmTime.setEnabled(false);
            buttonStartAlarm.setText("Stop");
            seekBarAlarmTime.setProgress(300);

            timer = new CountDownTimer(seekBarAlarmTime.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {

                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.alarmsound);
                    mp.start();
                    resetTimer();
                    Log.i("Tag:", "Alarm Finished");

                }
            };

            timer.start();
        }
    }

    public void onClickStartAlarmWellDone(View view)
    {
        if (counterIsActive == true)
        {

            resetTimer();

        }

        else {
            counterIsActive = true;
            seekBarAlarmTime.setEnabled(false);
            buttonStartAlarm.setText("Stop");
            seekBarAlarmTime.setProgress(480);

            timer = new CountDownTimer(seekBarAlarmTime.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {

                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.alarmsound);
                    mp.start();
                    resetTimer();
                    Log.i("Tag:", "Alarm Finished");

                }
            };

            timer.start();
        }
    }

    public void updateTimer(int secondsLeft)
    {

        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);
        String m = Integer.toString(minutes);
        String s = Integer.toString(seconds);

        if (s.length() == 1)
        {
            s = "0" + s;
        }


        textViewCounter.setText(m + ":" + s);

        seekBarAlarmTime.setProgress(secondsLeft);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        //Element variables
        seekBarAlarmTime = findViewById(R.id.seekBarAlarmTime);
        textViewCounter = findViewById(R.id.textViewCounter);
        buttonStartAlarm = findViewById(R.id.buttonStartAlarm);

        //SeekBar settings
        seekBarAlarmTime.setMax(600);
        seekBarAlarmTime.setProgress(0);

        seekBarAlarmTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
