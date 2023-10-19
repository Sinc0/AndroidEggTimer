//namespace
package android.example.eggtimer;

//includes
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
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
    Button buttonSuperSoft;
    Button buttonExtraSoft;
    Button buttonSoft;
    Button buttonMediumSoft;
    Button buttonMedium;
    Button buttonMediumHard;
    Button buttonHard;
    Button buttonExtraHard;
    Button buttonSuperHard;
    CountDownTimer timer;
    MediaPlayer mp;
    Boolean counterIsActive = false;
    Boolean soundOn = true;
    int visible = 0;
    int invisible = 4;
    int gone = 8;

    //functions
    public void resetTimer()
    {
        textViewCounter.setText("0:00");

        seekBarAlarmTime.setEnabled(true);

        timer.cancel();

        seekBarAlarmTime.setProgress(0);

        buttonStartAlarm.setText("Start");
        //getWindow().setBackgroundDrawableResource(R.drawable.browneggs2642201);

        //reset UI
        buttonStartAlarm.setBackgroundColor(0xFF000000);
        textViewCounter.setVisibility(invisible);
        buttonSuperSoft.setVisibility(visible);
        buttonExtraSoft.setVisibility(visible);
        buttonSoft.setVisibility(visible);
        buttonMediumSoft.setVisibility(visible);
        buttonMedium.setVisibility(visible);
        buttonMediumHard.setVisibility(visible);
        buttonHard.setVisibility(visible);
        buttonExtraHard.setVisibility(visible);
        buttonSuperHard.setVisibility(visible);

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


    @SuppressLint("WrongConstant")
    public void startTimer(View view)
    {
        if (counterIsActive == true) { resetTimer(); }
        else
        {
            //variables
            String buttonTag = view.getTag().toString();
            counterIsActive = true;

            //getWindow().setBackgroundDrawableResource(R.drawable.browneggs2642201);

            seekBarAlarmTime.setEnabled(false);

            buttonStartAlarm.setText("Stop");

            //buttonSoft.setBackgroundColor(0x00000000);

            //reset UI
            textViewCounter.setVisibility(visible);
            buttonSuperSoft.setVisibility(visible);
            buttonExtraSoft.setVisibility(invisible);
            buttonSoft.setVisibility(invisible);
            buttonMediumSoft.setVisibility(invisible);
            buttonMedium.setVisibility(invisible);
            buttonMediumHard.setVisibility(invisible);
            buttonHard.setVisibility(invisible);
            buttonExtraHard.setVisibility(invisible);
            buttonSuperHard.setVisibility(invisible);
            textViewCounter.setTextColor(0xff000000);

            //update UI
            if(buttonTag.equals("1")) { seekBarAlarmTime.setProgress(120); buttonSuperSoft.setText("Super Soft"); }
            else if(buttonTag.equals("2")) { seekBarAlarmTime.setProgress(180); buttonSuperSoft.setText("Extra Soft"); }
            else if(buttonTag.equals("3")) { seekBarAlarmTime.setProgress(300); buttonSuperSoft.setText("Soft"); }
            else if(buttonTag.equals("4")) { seekBarAlarmTime.setProgress(420); buttonSuperSoft.setText("Medium Soft"); }
            else if(buttonTag.equals("5")) { seekBarAlarmTime.setProgress(540); buttonSuperSoft.setText("Temp"); }
            else if(buttonTag.equals("6")) { seekBarAlarmTime.setProgress(660); buttonSuperSoft.setText("Temp"); }
            else if(buttonTag.equals("7")) { seekBarAlarmTime.setProgress(780); buttonSuperSoft.setText("Temp"); }
            else if(buttonTag.equals("8")) { seekBarAlarmTime.setProgress(900); buttonSuperSoft.setText("Temp"); }
            else if(buttonTag.equals("9")) { seekBarAlarmTime.setProgress(1020); buttonSuperSoft.setText("Temp"); }


            timer = new CountDownTimer(seekBarAlarmTime.getProgress() * 1000 + 100, 1000)
            {
                @Override public void onTick(long millisUntilFinished)
                {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override public void onFinish()
                {
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.alarmsound);
                    mp.start();
                    //resetTimer();
                    textViewCounter.setTextColor(0xffff0000);
                    Log.i("Tag:", "Alarm Finished");
                }
            };

            timer.start();
        }
    }


    public void changeTab(View view)
    {
        //variables
        String buttonTag = view.getTag().toString();

        //widgets
        ConstraintLayout Tab1 = findViewById(R.id.Tab1);
        ConstraintLayout Tab2 = findViewById(R.id.Tab2);
        ConstraintLayout Tab3 = findViewById(R.id.Tab3);

        //reset UI
        Tab1.setVisibility(invisible);
        Tab2.setVisibility(invisible);
        Tab3.setVisibility(invisible);

        //update UI
        if(buttonTag.equals("timer")) { Tab1.setVisibility(visible); }
        else if(buttonTag.equals("settings")) { Tab2.setVisibility(visible); }
        else if(buttonTag.equals("about")) { Tab3.setVisibility(visible); }
    }


    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //set startup activity
        setContentView(R.layout.activity_main);

        //set backgrounds
        //getWindow().setBackgroundDrawableResource(R.drawable.browneggs2642201);

        //widgets
        seekBarAlarmTime = findViewById(R.id.seekBarAlarmTime);
        textViewCounter = findViewById(R.id.textViewCounter);
        textViewSoundOnOff = findViewById(R.id.textViewSoundOnOff);
        buttonStartAlarm = findViewById(R.id.buttonStartAlarm);
        buttonSuperSoft = findViewById(R.id.buttonSuperSoft);
        buttonExtraSoft = findViewById(R.id.buttonExtraSoft);
        buttonSoft = findViewById(R.id.buttonSoft);
        buttonMediumSoft = findViewById(R.id.buttonMediumSoft);
        buttonMedium = findViewById(R.id.buttonMedium);
        buttonMediumHard = findViewById(R.id.buttonMediumHard);
        buttonHard = findViewById(R.id.buttonHard);
        buttonExtraHard = findViewById(R.id.buttonExtraHard);
        buttonSuperHard = findViewById(R.id.buttonSuperHard);

        //set seekbar settings
        seekBarAlarmTime.setMax(1200);
        seekBarAlarmTime.setProgress(0);

        //set seekbar on listener
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
