package android.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewCounter;

    public void onClickStartAlarm(View view) {
        CountDownTimer Timer = new CountDownTimer(10000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        }.start();
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

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        //Element variables
        SeekBar seekBarAlarmTime = findViewById(R.id.seekBarAlarmTime);
        TextView textViewCounter = findViewById(R.id.textViewCounter);
        Button buttonStartAlarm = findViewById(R.id.buttonStartAlarm);

        //SeekBar settings
        seekBarAlarmTime.setMax(600);
        seekBarAlarmTime.setProgress(60);

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
