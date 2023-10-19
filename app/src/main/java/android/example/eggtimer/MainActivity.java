//namespace
package android.example.eggtimer;

//includes
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

//main class
public class MainActivity extends AppCompatActivity {
    //variables
    TextView textViewCounter;
    TextView textViewSoundOnOff;
    SeekBar seekBarAlarmTime;
    Button buttonStartAlarm;
    Button buttonStartAlarmRare;
    Button buttonStartAlarmMedium;
    Button buttonStartAlarmHard;
    CountDownTimer timer;
    MediaPlayer mp;
    Boolean counterIsActive = false;
    Boolean soundOn = true;

    //functions
    public void resetTimer()
    {
        textViewCounter.setText("0:00");

        seekBarAlarmTime.setEnabled(true);

        timer.cancel();

        seekBarAlarmTime.setProgress(0);

        buttonStartAlarm.setText("Start");
        //getWindow().setBackgroundDrawableResource(R.drawable.browneggs2642201);

        buttonStartAlarm.setBackgroundColor(Color.rgb(224, 218, 218));
        buttonStartAlarmRare.setBackgroundColor(Color.rgb(224, 218, 218));
        buttonStartAlarmMedium.setBackgroundColor(Color.rgb(224, 218, 218));
        buttonStartAlarmHard.setBackgroundColor(Color.rgb(224, 218, 218));

        counterIsActive = false;
    }

    public void updateTimer(int secondsLeft)
    {
        //variables
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

    public void onClicktextViewSoundOnOff(View view)
    {
        //debug
        Log.i("Tag", "Clicked");

        if (soundOn == true)
        {
            mp.stop();
            textViewSoundOnOff.setText("Sound is Off");
            soundOn = false;
        }
        else if (soundOn == false)
        {
            textViewSoundOnOff.setText("Sound is On");
            soundOn = true;
        }

    }

    public void onClickStartAlarm(View view)
    {
        if (counterIsActive == true)
        {
            resetTimer();
        }
        else
        {
            //getWindow().setBackgroundDrawableResource(R.drawable.browneggs2642201);
            counterIsActive = true;

            seekBarAlarmTime.setEnabled(false);

            buttonStartAlarm.setText("Stop");

            buttonStartAlarm.setBackgroundColor(Color.GRAY);
            buttonStartAlarmRare.setBackgroundColor(Color.rgb(224, 218, 218));
            buttonStartAlarmMedium.setBackgroundColor(Color.rgb(224, 218, 218));
            buttonStartAlarmHard.setBackgroundColor(Color.rgb(224, 218, 218));

            timer = new CountDownTimer(seekBarAlarmTime.getProgress() * 1000 + 100, 1000) {
                @Override public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override public void onFinish() {
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.alarmsound);
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
        else
        {
            //getWindow().setBackgroundDrawableResource(R.drawable.browneggs2642201goldorange);
            counterIsActive = true;

            seekBarAlarmTime.setEnabled(false);

            buttonStartAlarm.setText("Stop");

            buttonStartAlarm.setBackgroundColor(Color.rgb(224, 218, 218));
            buttonStartAlarmRare.setBackgroundColor(Color.GRAY);
            buttonStartAlarmMedium.setBackgroundColor(Color.rgb(224, 218, 218));
            buttonStartAlarmHard.setBackgroundColor(Color.rgb(224, 218, 218));

            seekBarAlarmTime.setProgress(180);

            timer = new CountDownTimer(seekBarAlarmTime.getProgress() * 1000 + 100, 1000) {
                @Override public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override public void onFinish() {
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.alarmsound);
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
        else
        {
            //getWindow().setBackgroundDrawableResource(R.drawable.browneggs2642201summertime);
            counterIsActive = true;

            seekBarAlarmTime.setEnabled(false);

            buttonStartAlarm.setText("Stop");

            buttonStartAlarm.setBackgroundColor(Color.rgb(224, 218, 218));
            buttonStartAlarmRare.setBackgroundColor(Color.rgb(224, 218, 218));
            buttonStartAlarmMedium.setBackgroundColor(Color.GRAY);
            buttonStartAlarmHard.setBackgroundColor(Color.rgb(224, 218, 218));

            seekBarAlarmTime.setProgress(300);

            timer = new CountDownTimer(seekBarAlarmTime.getProgress() * 1000 + 100, 1000) {
                @Override public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override public void onFinish() {
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.alarmsound);
                    mp.start();
                    resetTimer();
                    Log.i("Tag:", "Alarm Finished");
                }
            };

            timer.start();
        }
    }

    public void onClickStartHard(View view) {
        if (counterIsActive == true)
        {
            resetTimer();
        }
        else
        {
            //getWindow().setBackgroundDrawableResource(R.drawable.browneggs2642201moonlight);

            counterIsActive = true;

            seekBarAlarmTime.setEnabled(false);

            buttonStartAlarm.setText("Stop");

            buttonStartAlarm.setBackgroundColor(Color.rgb(118, 118, 118));
            buttonStartAlarmRare.setBackgroundColor(Color.rgb(42, 42, 42));
            buttonStartAlarmMedium.setBackgroundColor(Color.rgb(68, 68, 68));
            buttonStartAlarmHard.setBackgroundColor(Color.GRAY);

            seekBarAlarmTime.setProgress(480);

            timer = new CountDownTimer(seekBarAlarmTime.getProgress() * 1000 + 100, 1000) {
                @Override public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override public void onFinish() {
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.alarmsound);
                    mp.start();
                    resetTimer();
                    Log.i("Tag:", "Alarm Finished");
                }
            };

            timer.start();
        }
    }

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //set startup activity
        setContentView(R.layout.activity_main);

        //set backgrounds
        //getWindow().setBackgroundDrawableResource(R.drawable.browneggs2642201);

        //variables
        seekBarAlarmTime = findViewById(R.id.seekBarAlarmTime);
        textViewCounter = findViewById(R.id.textViewCounter);
        textViewSoundOnOff = findViewById(R.id.textViewSoundOnOff);
        buttonStartAlarm = findViewById(R.id.buttonStartAlarm);
        buttonStartAlarmRare = findViewById(R.id.buttonSoft);
        buttonStartAlarmMedium = findViewById(R.id.buttonMedium);
        buttonStartAlarmHard = findViewById(R.id.buttonHard);

        //set seekbar settings
        seekBarAlarmTime.setMax(600);
        seekBarAlarmTime.setProgress(0);
        seekBarAlarmTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                updateTimer(progress);
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }
}
