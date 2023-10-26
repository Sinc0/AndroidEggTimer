/****** namespace ******/
package android.egg.timer;


/****** includes ******/
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.util.Log;
import android.content.pm.PackageManager;
import android.content.ComponentName;
import android.widget.Toast;


/****** main ******/
public class MainActivity extends AppCompatActivity {
    //variables
    Boolean counterIsActive = false;
    int visible = 0;
    int invisible = 4;
    int gone = 8;

    //widgets
    TextView textViewCounter;
    TextView textViewThemeTitle;
    TextView textViewSettingsTitle;
    TextView textViewAboutTitle;
    TextView textViewAboutText;
    TextView textViewPrivacyPolicyTitle;
    TextView textViewPrivacyPolicyText;
    TextView textViewContactTitle;
    TextView textViewContactText;
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
    Button buttonBottom1original;
    Button buttonBottom1inverted;
    Button buttonBottom1blueberry;
    Button buttonBottom2original;
    Button buttonBottom2inverted;
    Button buttonBottom2blueberry;
    Button buttonBottom3original;
    Button buttonBottom3inverted;
    Button buttonBottom3blueberry;
    Button buttonThemeOriginal;
    Button buttonThemeInverted;
    Button buttonThemeBlueberry;
    CountDownTimer timer;
    MediaPlayer alarm;
    ConstraintLayout Tab1;
    ConstraintLayout Tab2;
    ConstraintLayout Tab3;
    ConstraintLayout constraintLayoutMain;
    ScrollView scrollviewAlarmButtons;
    LinearLayout bottomMenu;

    //on startup
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        //on create
        super.onCreate(savedInstanceState);

        //set startup activity
        setContentView(R.layout.activity_main);

        //variables
        alarm = MediaPlayer.create(getApplicationContext(), R.raw.beep);

        //widgets
        textViewCounter = findViewById(R.id.textViewCounter);
        textViewThemeTitle = findViewById(R.id.textViewThemeTitle);
        textViewSettingsTitle = findViewById(R.id.textViewSettingsTitle);
        textViewAboutTitle = findViewById(R.id.textViewAboutTitle);
        textViewAboutText = findViewById(R.id.textViewAboutText);
        textViewPrivacyPolicyTitle = findViewById(R.id.textViewPrivacyPolicyTitle);
        textViewPrivacyPolicyText = findViewById(R.id.textViewPrivacyPolicyText);
        textViewContactTitle = findViewById(R.id.textViewContactTitle);
        textViewContactText = findViewById(R.id.textViewContactText);
        seekBarAlarmTime = findViewById(R.id.seekBarAlarmTime);
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
        constraintLayoutMain = findViewById(R.id.constraintLayoutMain);
        buttonBottom1original = findViewById(R.id.buttonBottom1original);
        buttonBottom1inverted = findViewById(R.id.buttonBottom1inverted);
        buttonBottom1blueberry = findViewById(R.id.buttonBottom1blueberry);
        buttonBottom2original = findViewById(R.id.buttonBottom2original);
        buttonBottom2inverted = findViewById(R.id.buttonBottom2inverted);
        buttonBottom2blueberry = findViewById(R.id.buttonBottom2blueberry);
        buttonBottom3original = findViewById(R.id.buttonBottom3original);
        buttonBottom3inverted = findViewById(R.id.buttonBottom3inverted);
        buttonBottom3blueberry = findViewById(R.id.buttonBottom3blueberry);
        buttonThemeOriginal = findViewById(R.id.buttonThemeOriginal);
        buttonThemeInverted = findViewById(R.id.buttonThemeInverted);
        buttonThemeBlueberry = findViewById(R.id.buttonThemeBlueberry);

        //set startup theme
        setStartupTheme(getSelectedTheme());

        //set seekbar settings
        seekBarAlarmTime.setMax(1200);
        seekBarAlarmTime.setProgress(0);

        //set seekbar on listeners
        seekBarAlarmTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            { updateTimer(progress); }

            @Override public void onStartTrackingTouch(SeekBar seekBar)
            { }

            @Override public void onStopTrackingTouch(SeekBar seekBar)
            { }
        });
    }

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
        {
            resetTimer();
        }
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
            scrollviewAlarmButtons.scrollTo(0,0);

            //set alarm color based on theme
            if(getSelectedTheme().equals("Original")) { textViewCounter.setTextColor(0xff000000); }
            else if(getSelectedTheme().equals("Inverted")) { textViewCounter.setTextColor(0xffffd880); }
            else if(getSelectedTheme().equals("Blueberry")) { textViewCounter.setTextColor(0xffffffff); }

            //update UI
            if(buttonTag.equals("1")) { seekBarAlarmTime.setProgress(120); buttonSuperSoft.setText("Super Soft"); }
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

                    //set main background color
                    //constraintLayoutMain.setBackgroundColor(0xffff0000);

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


    public void changeTheme(View view)
    {
        //variables
        String selectedTheme = view.getTag().toString();

        //reset bottom menu
        buttonBottom1original.setVisibility(gone);
        buttonBottom2original.setVisibility(gone);
        buttonBottom3original.setVisibility(gone);
        buttonBottom1inverted.setVisibility(gone);
        buttonBottom2inverted.setVisibility(gone);
        buttonBottom3inverted.setVisibility(gone);
        buttonBottom1blueberry.setVisibility(gone);
        buttonBottom2blueberry.setVisibility(gone);
        buttonBottom3blueberry.setVisibility(gone);

        //check type
        if(selectedTheme.equals("Original"))
        { saveSelectedTheme(selectedTheme); updateThemeColors(selectedTheme); }
        else if(selectedTheme.equals("Inverted"))
        { saveSelectedTheme(selectedTheme); updateThemeColors(selectedTheme); }
        else if(selectedTheme.equals("Blueberry"))
        { saveSelectedTheme(selectedTheme); updateThemeColors(selectedTheme); }
    }


    public void setStartupTheme(String selectedTheme)
    {
        //reset widgets
        buttonBottom1original.setVisibility(gone);
        buttonBottom1inverted.setVisibility(gone);
        buttonBottom1blueberry.setVisibility(gone);
        buttonBottom2original.setVisibility(gone);
        buttonBottom2inverted.setVisibility(gone);
        buttonBottom2blueberry.setVisibility(gone);
        buttonBottom3original.setVisibility(gone);
        buttonBottom3inverted.setVisibility(gone);
        buttonBottom3blueberry.setVisibility(gone);

        //check type
        if(selectedTheme.equals("Original"))
        { updateThemeColors(selectedTheme); }
        else if(selectedTheme.equals("Inverted"))
        { updateThemeColors(selectedTheme); }
        else if(selectedTheme.equals("Blueberry"))
        { updateThemeColors(selectedTheme); }
        else { updateThemeColors("Original"); }
    }


    public void saveSelectedTheme(String selectedTheme)
    {
        //variables
        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        sharedPref.edit().putString("selectedTheme", selectedTheme).apply();

        //debugging
        //String st = sharedPref.getString("selectedTheme", "");
        //Log.i("saveSelectedTheme", st);
    }


    public String getSelectedTheme()
    {
        //variables
        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        String st = sharedPref.getString("selectedTheme", "");

        //debugging
        //Log.i("getSelectedTheme", st);

        //return value
        return st;
    }


    public void clearSharedPreferences()
    {
        //variables
        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);

        //clear shared preferences data
        sharedPref.edit().clear();
    }


    public void updateThemeColors(String type)
    {
        //variables
        int color1 = 0;
        int color2 = 0;

        //set theme colors
        if(type.equals("Original")) { color1 = 0xff000000; color2 = 0xffffd880; }
        else if(type.equals("Inverted")) { color1 = 0xffffd880; color2 = 0xff000000; }
        else if(type.equals("Blueberry")) { color1 = 0xffffffff; color2 = 0xff0041C2; }

        //Tab 1
        constraintLayoutMain.setBackgroundColor(color2);
        buttonSuperSoft.setBackgroundColor(color1); buttonSuperSoft.setTextColor(color2);
        buttonExtraSoft.setBackgroundColor(color1); buttonExtraSoft.setTextColor(color2);
        buttonSoft.setBackgroundColor(color1); buttonSoft.setTextColor(color2);
        buttonMediumSoft.setBackgroundColor(color1); buttonMediumSoft.setTextColor(color2);
        buttonMedium.setBackgroundColor(color1); buttonMedium.setTextColor(color2);
        buttonMediumHard.setBackgroundColor(color1); buttonMediumHard.setTextColor(color2);
        buttonHard.setBackgroundColor(color1); buttonHard.setTextColor(color2);
        buttonExtraHard.setBackgroundColor(color1); buttonExtraHard.setTextColor(color2);
        buttonSuperHard.setBackgroundColor(color1); buttonSuperHard.setTextColor(color2);
        textViewCounter.setTextColor(color1);

        //Tab 2
        Tab2.setBackgroundColor(color2);
        buttonThemeOriginal.setBackgroundColor(color1);
        buttonThemeOriginal.setTextColor(color2);
        buttonThemeInverted.setBackgroundColor(color1);
        buttonThemeInverted.setTextColor(color2);
        buttonThemeBlueberry.setBackgroundColor(color1);
        buttonThemeBlueberry.setTextColor(color2);
        textViewThemeTitle.setTextColor(color1);
        textViewSettingsTitle.setTextColor(color1);

        //Tab 3
        Tab3.setBackgroundColor(color2);
        textViewAboutTitle.setTextColor(color1);
        textViewAboutText.setTextColor(color1);
        textViewPrivacyPolicyTitle.setTextColor(color1);
        textViewPrivacyPolicyText.setTextColor(color1);
        textViewContactTitle.setTextColor(color1);
        textViewContactText.setTextColor(color1);

        //Bottom Menu
        if(type.equals("Original"))
        {
            buttonBottom1original.setVisibility(visible);
            buttonBottom2original.setVisibility(visible);
            buttonBottom3original.setVisibility(visible);
        }
        else if(type.equals("Inverted"))
        {
            buttonBottom1inverted.setVisibility(visible);
            buttonBottom2inverted.setVisibility(visible);
            buttonBottom3inverted.setVisibility(visible);
        }
        else if(type.equals("Blueberry"))
        {
            buttonBottom1blueberry.setVisibility(visible);
            buttonBottom2blueberry.setVisibility(visible);
            buttonBottom3blueberry.setVisibility(visible);
        }
    }
}