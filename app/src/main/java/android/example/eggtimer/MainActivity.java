//namespace
package android.example.eggtimer;


//includes
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.annotation.SuppressLint;


//main
public class MainActivity extends AppCompatActivity {
    //variables
    Boolean counterIsActive = false;
    Boolean soundOn = true;
    int visible = 0;
    int invisible = 4;
    int gone = 8;

    //widgets
    TextView textViewCounter;
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
    MediaPlayer alarm;
    ConstraintLayout Tab1;
    ConstraintLayout Tab2;
    ConstraintLayout Tab3;
    ScrollView scrollviewAlarmButtons;
    LinearLayout bottomMenu;


    //functions
    public void resetTimer()
    {
        //reset alarm sound
        alarm.reset();

        //reset timer
        timer.cancel();

        //reset seekbar
        seekBarAlarmTime.setEnabled(true);
        seekBarAlarmTime.setProgress(0);

        //reset counter status
        counterIsActive = false;

        //reset UI
        buttonStartAlarm.setText("Start");
        textViewCounter.setText("0:00");
        buttonSuperSoft.setText("Super Soft");
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
        bottomMenu.setVisibility(visible);
    }


    public void updateTimer(int secondsLeft)
    {
        //variables
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);
        String currentMinute = Integer.toString(minutes);
        String currentSeconds = Integer.toString(seconds);

        //format timer string
        if (currentSeconds.length() == 1)
        { currentSeconds = "0" + currentSeconds; }

        //update UI
        textViewCounter.setText(currentMinute + ":" + currentSeconds);
        seekBarAlarmTime.setProgress(secondsLeft);
    }


    @SuppressLint("WrongConstant") public void startTimer(View view)
    {
        if (counterIsActive == true)
        { resetTimer(); }

        else
        {
            //variables
            String buttonTag = view.getTag().toString();
            counterIsActive = true;
            seekBarAlarmTime.setEnabled(false);
            buttonStartAlarm.setText("Stop");

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
            bottomMenu.setVisibility(invisible);
            textViewCounter.setTextColor(0xff000000);
            scrollviewAlarmButtons.scrollTo(0,0);

            //update UI
            if(buttonTag.equals("1")) { seekBarAlarmTime.setProgress(3); buttonSuperSoft.setText("Super Soft"); }
            else if(buttonTag.equals("2")) { seekBarAlarmTime.setProgress(180); buttonSuperSoft.setText("Extra Soft"); }
            else if(buttonTag.equals("3")) { seekBarAlarmTime.setProgress(300); buttonSuperSoft.setText("Soft"); }
            else if(buttonTag.equals("4")) { seekBarAlarmTime.setProgress(420); buttonSuperSoft.setText("Medium Soft"); }
            else if(buttonTag.equals("5")) { seekBarAlarmTime.setProgress(540); buttonSuperSoft.setText("Medium"); }
            else if(buttonTag.equals("6")) { seekBarAlarmTime.setProgress(660); buttonSuperSoft.setText("Medium Hard"); }
            else if(buttonTag.equals("7")) { seekBarAlarmTime.setProgress(780); buttonSuperSoft.setText("Hard"); }
            else if(buttonTag.equals("8")) { seekBarAlarmTime.setProgress(900); buttonSuperSoft.setText("Extra Hard"); }
            else if(buttonTag.equals("9")) { seekBarAlarmTime.setProgress(1020); buttonSuperSoft.setText("Super Hard"); }

            //set timer
            timer = new CountDownTimer(seekBarAlarmTime.getProgress() * 1000 + 100, 1000)
            {
                @Override public void onTick(long millisUntilFinished)
                { updateTimer((int) millisUntilFinished / 1000); }

                @Override public void onFinish()
                {
                    //start alarm sound
                    alarm = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    alarm.start();

                    //set alarm digits to red
                    textViewCounter.setTextColor(0xffff0000);

                    //debug
                    //Log.i("Tag:", "Alarm Finished");
                }
            };

            //start timer
            timer.start();
        }
    }


    public void changeTab(View view)
    {
        //variables
        String buttonTag = view.getTag().toString();

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
        //on create
        super.onCreate(savedInstanceState);

        //set startup activity
        setContentView(R.layout.activity_main);

        //variables
        alarm = MediaPlayer.create(getApplicationContext(), R.raw.beep);

        //widgets
        seekBarAlarmTime = findViewById(R.id.seekBarAlarmTime);
        textViewCounter = findViewById(R.id.textViewCounter);
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
        Tab1 = findViewById(R.id.Tab1);
        Tab2 = findViewById(R.id.Tab2);
        Tab3 = findViewById(R.id.Tab3);
        scrollviewAlarmButtons = findViewById(R.id.scrollviewAlarmButtons);
        bottomMenu = findViewById(R.id.BottomMenu);

        //set seekbar settings
        seekBarAlarmTime.setMax(1200);
        seekBarAlarmTime.setProgress(0);

        //set seekbar on listeners
        seekBarAlarmTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            { updateTimer(progress); }
            @Override public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }
}