//namespace
package android.example.eggtimerTest;


//includes
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import android.widget.Toast;


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
    TextView textViewThemeTitle;
    TextView textViewSettingsTitle;
    TextView textViewAboutTitle;
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
            scrollviewAlarmButtons.scrollTo(0,0);

            //set alarm color based on theme
            if(getSelectedTheme().equals("Original")) { textViewCounter.setTextColor(0xff000000); }
            else if(getSelectedTheme().equals("Inverted")) { textViewCounter.setTextColor(0xffffd880); }
            else if(getSelectedTheme().equals("Blueberry")) { textViewCounter.setTextColor(0xffffffff); }

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
        String buttonTag = view.getTag().toString();

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
        if(buttonTag.equals("Original"))
        {
            saveSelectedTheme("Original");

            //Tab 2
            Tab2.setBackgroundColor(0xffffd880);
            buttonBottom1original.setVisibility(visible);
            buttonBottom2original.setVisibility(visible);
            buttonBottom3original.setVisibility(visible);
            buttonThemeOriginal.setBackgroundColor(0xff000000);
            buttonThemeInverted.setBackgroundColor(0xff000000);
            buttonThemeBlueberry.setBackgroundColor(0xff000000);
            buttonThemeOriginal.setTextColor(0xffffd880);
            buttonThemeInverted.setTextColor(0xffffd880);
            buttonThemeBlueberry.setTextColor(0xffffd880);
            textViewThemeTitle.setTextColor(0xff000000);
            textViewSettingsTitle.setTextColor(0xff000000);

            //Tab 1
            constraintLayoutMain.setBackgroundColor(0xffffd880);
            buttonSuperSoft.setBackgroundColor(0xff000000); buttonSuperSoft.setTextColor(0xffffd880);
            buttonExtraSoft.setBackgroundColor(0xff000000); buttonExtraSoft.setTextColor(0xffffd880);
            buttonSoft.setBackgroundColor(0xff000000); buttonSoft.setTextColor(0xffffd880);
            buttonMediumSoft.setBackgroundColor(0xff000000); buttonMediumSoft.setTextColor(0xffffd880);
            buttonMedium.setBackgroundColor(0xff000000); buttonMedium.setTextColor(0xffffd880);
            buttonMediumHard.setBackgroundColor(0xff000000); buttonMediumHard.setTextColor(0xffffd880);
            buttonHard.setBackgroundColor(0xff000000); buttonHard.setTextColor(0xffffd880);
            buttonExtraHard.setBackgroundColor(0xff000000); buttonExtraHard.setTextColor(0xffffd880);
            buttonSuperHard.setBackgroundColor(0xff000000); buttonSuperHard.setTextColor(0xffffd880);
            textViewCounter.setTextColor(0xff000000);

            //Tab 3
            Tab3.setBackgroundColor(0xffffd880);
            textViewAboutTitle.setTextColor(0xff000000);
        }
        else if(buttonTag.equals("Inverted"))
        {
            saveSelectedTheme("Inverted");

            //Tab 2
            Tab2.setBackgroundColor(0xff000000);
            buttonBottom1inverted.setVisibility(visible);
            buttonBottom2inverted.setVisibility(visible);
            buttonBottom3inverted.setVisibility(visible);
            buttonThemeOriginal.setBackgroundColor(0xffffd880);
            buttonThemeOriginal.setTextColor(0xff000000);
            buttonThemeInverted.setBackgroundColor(0xffffd880);
            buttonThemeInverted.setTextColor(0xff000000);
            buttonThemeBlueberry.setBackgroundColor(0xffffd880);
            buttonThemeBlueberry.setTextColor(0xff000000);
            textViewThemeTitle.setTextColor(0xffffd880);
            textViewSettingsTitle.setTextColor(0xffffd880);

            //Tab 1
            constraintLayoutMain.setBackgroundColor(0xff000000);
            buttonSuperSoft.setBackgroundColor(0xffffd880); buttonSuperSoft.setTextColor(0xff000000);
            buttonExtraSoft.setBackgroundColor(0xffffd880); buttonExtraSoft.setTextColor(0xff000000);
            buttonSoft.setBackgroundColor(0xffffd880); buttonSoft.setTextColor(0xff000000);
            buttonMediumSoft.setBackgroundColor(0xffffd880); buttonMediumSoft.setTextColor(0xff000000);
            buttonMedium.setBackgroundColor(0xffffd880); buttonMedium.setTextColor(0xff000000);
            buttonMediumHard.setBackgroundColor(0xffffd880); buttonMediumHard.setTextColor(0xff000000);
            buttonHard.setBackgroundColor(0xffffd880); buttonHard.setTextColor(0xff000000);
            buttonExtraHard.setBackgroundColor(0xffffd880); buttonExtraHard.setTextColor(0xff000000);
            buttonSuperHard.setBackgroundColor(0xffffd880); buttonSuperHard.setTextColor(0xff000000);
            textViewCounter.setTextColor(0xffffd880);

            //Tab 3
            Tab3.setBackgroundColor(0xff000000);
            textViewAboutTitle.setTextColor(0xffffd880);
        }
        else if(buttonTag.equals("Blueberry"))
        {
            saveSelectedTheme("Blueberry");

            //Tab 2
            Tab2.setBackgroundColor(0xff0041C2);
            buttonBottom1blueberry.setVisibility(visible);
            buttonBottom2blueberry.setVisibility(visible);
            buttonBottom3blueberry.setVisibility(visible);
            buttonThemeOriginal.setBackgroundColor(0xffffffff);
            buttonThemeInverted.setBackgroundColor(0xffffffff);
            buttonThemeBlueberry.setBackgroundColor(0xffffffff);
            buttonThemeOriginal.setTextColor(0xff0041C2);
            buttonThemeInverted.setTextColor(0xff0041C2);
            buttonThemeBlueberry.setTextColor(0xff0041C2);
            textViewThemeTitle.setTextColor(0xffffffff);
            textViewSettingsTitle.setTextColor(0xffffffff);

            //Tab 1
            constraintLayoutMain.setBackgroundColor(0xff0041C2);
            buttonSuperSoft.setBackgroundColor(0xffffffff); buttonSuperSoft.setTextColor(0xff0041C2);
            buttonExtraSoft.setBackgroundColor(0xffffffff); buttonExtraSoft.setTextColor(0xff0041C2);
            buttonSoft.setBackgroundColor(0xffffffff); buttonSoft.setTextColor(0xff0041C2);
            buttonMediumSoft.setBackgroundColor(0xffffffff); buttonMediumSoft.setTextColor(0xff0041C2);
            buttonMedium.setBackgroundColor(0xffffffff); buttonMedium.setTextColor(0xff0041C2);
            buttonMediumHard.setBackgroundColor(0xffffffff); buttonMediumHard.setTextColor(0xff0041C2);
            buttonHard.setBackgroundColor(0xffffffff); buttonHard.setTextColor(0xff0041C2);
            buttonExtraHard.setBackgroundColor(0xffffffff); buttonExtraHard.setTextColor(0xff0041C2);
            buttonSuperHard.setBackgroundColor(0xffffffff); buttonSuperHard.setTextColor(0xff0041C2);
            textViewCounter.setTextColor(0xffffffff);

            //Tab 3
            Tab3.setBackgroundColor(0xff0041C2);
            textViewAboutTitle.setTextColor(0xffffffff);
        }
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
        {
            //Tab 2
            Tab2.setBackgroundColor(0xffffd880);
            buttonBottom1original.setVisibility(visible);
            buttonBottom2original.setVisibility(visible);
            buttonBottom3original.setVisibility(visible);
            buttonThemeOriginal.setBackgroundColor(0xff000000);
            buttonThemeInverted.setBackgroundColor(0xff000000);
            buttonThemeBlueberry.setBackgroundColor(0xff000000);
            buttonThemeOriginal.setTextColor(0xffffd880);
            buttonThemeInverted.setTextColor(0xffffd880);
            buttonThemeBlueberry.setTextColor(0xffffd880);
            textViewThemeTitle.setTextColor(0xff000000);
            textViewSettingsTitle.setTextColor(0xff000000);

            //Tab 1
            constraintLayoutMain.setBackgroundColor(0xffffd880);
            buttonSuperSoft.setBackgroundColor(0xff000000); buttonSuperSoft.setTextColor(0xffffd880);
            buttonExtraSoft.setBackgroundColor(0xff000000); buttonExtraSoft.setTextColor(0xffffd880);
            buttonSoft.setBackgroundColor(0xff000000); buttonSoft.setTextColor(0xffffd880);
            buttonMediumSoft.setBackgroundColor(0xff000000); buttonMediumSoft.setTextColor(0xffffd880);
            buttonMedium.setBackgroundColor(0xff000000); buttonMedium.setTextColor(0xffffd880);
            buttonMediumHard.setBackgroundColor(0xff000000); buttonMediumHard.setTextColor(0xffffd880);
            buttonHard.setBackgroundColor(0xff000000); buttonHard.setTextColor(0xffffd880);
            buttonExtraHard.setBackgroundColor(0xff000000); buttonExtraHard.setTextColor(0xffffd880);
            buttonSuperHard.setBackgroundColor(0xff000000); buttonSuperHard.setTextColor(0xffffd880);
            textViewCounter.setTextColor(0xff000000);

            //Tab 3
            Tab3.setBackgroundColor(0xffffd880);
            textViewAboutTitle.setTextColor(0xff000000);
        }
        else if(selectedTheme.equals("Inverted"))
        {
            //Tab 2
            Tab2.setBackgroundColor(0xff000000);
            buttonBottom1inverted.setVisibility(visible);
            buttonBottom2inverted.setVisibility(visible);
            buttonBottom3inverted.setVisibility(visible);
            buttonThemeOriginal.setBackgroundColor(0xffffd880);
            buttonThemeOriginal.setTextColor(0xff000000);
            buttonThemeInverted.setBackgroundColor(0xffffd880);
            buttonThemeInverted.setTextColor(0xff000000);
            buttonThemeBlueberry.setBackgroundColor(0xffffd880);
            buttonThemeBlueberry.setTextColor(0xff000000);
            textViewThemeTitle.setTextColor(0xffffd880);
            textViewSettingsTitle.setTextColor(0xffffd880);

            //Tab 1
            constraintLayoutMain.setBackgroundColor(0xff000000);
            buttonSuperSoft.setBackgroundColor(0xffffd880); buttonSuperSoft.setTextColor(0xff000000);
            buttonExtraSoft.setBackgroundColor(0xffffd880); buttonExtraSoft.setTextColor(0xff000000);
            buttonSoft.setBackgroundColor(0xffffd880); buttonSoft.setTextColor(0xff000000);
            buttonMediumSoft.setBackgroundColor(0xffffd880); buttonMediumSoft.setTextColor(0xff000000);
            buttonMedium.setBackgroundColor(0xffffd880); buttonMedium.setTextColor(0xff000000);
            buttonMediumHard.setBackgroundColor(0xffffd880); buttonMediumHard.setTextColor(0xff000000);
            buttonHard.setBackgroundColor(0xffffd880); buttonHard.setTextColor(0xff000000);
            buttonExtraHard.setBackgroundColor(0xffffd880); buttonExtraHard.setTextColor(0xff000000);
            buttonSuperHard.setBackgroundColor(0xffffd880); buttonSuperHard.setTextColor(0xff000000);
            textViewCounter.setTextColor(0xffffd880);

            //Tab 3
            Tab3.setBackgroundColor(0xff000000);
            textViewAboutTitle.setTextColor(0xffffd880);
        }
        else if(selectedTheme.equals("Blueberry"))
        {
            //Tab 2
            Tab2.setBackgroundColor(0xff0041C2);
            buttonBottom1blueberry.setVisibility(visible);
            buttonBottom2blueberry.setVisibility(visible);
            buttonBottom3blueberry.setVisibility(visible);
            buttonThemeOriginal.setBackgroundColor(0xffffffff);
            buttonThemeInverted.setBackgroundColor(0xffffffff);
            buttonThemeBlueberry.setBackgroundColor(0xffffffff);
            buttonThemeOriginal.setTextColor(0xff0041C2);
            buttonThemeInverted.setTextColor(0xff0041C2);
            buttonThemeBlueberry.setTextColor(0xff0041C2);
            textViewThemeTitle.setTextColor(0xffffffff);
            textViewSettingsTitle.setTextColor(0xffffffff);

            //Tab 1
            constraintLayoutMain.setBackgroundColor(0xff0041C2);
            buttonSuperSoft.setBackgroundColor(0xffffffff); buttonSuperSoft.setTextColor(0xff0041C2);
            buttonExtraSoft.setBackgroundColor(0xffffffff); buttonExtraSoft.setTextColor(0xff0041C2);
            buttonSoft.setBackgroundColor(0xffffffff); buttonSoft.setTextColor(0xff0041C2);
            buttonMediumSoft.setBackgroundColor(0xffffffff); buttonMediumSoft.setTextColor(0xff0041C2);
            buttonMedium.setBackgroundColor(0xffffffff); buttonMedium.setTextColor(0xff0041C2);
            buttonMediumHard.setBackgroundColor(0xffffffff); buttonMediumHard.setTextColor(0xff0041C2);
            buttonHard.setBackgroundColor(0xffffffff); buttonHard.setTextColor(0xff0041C2);
            buttonExtraHard.setBackgroundColor(0xffffffff); buttonExtraHard.setTextColor(0xff0041C2);
            buttonSuperHard.setBackgroundColor(0xffffffff); buttonSuperHard.setTextColor(0xff0041C2);
            textViewCounter.setTextColor(0xffffffff);

            //Tab 3
            Tab3.setBackgroundColor(0xff0041C2);
            textViewAboutTitle.setTextColor(0xffffffff);
        }
        else
        {
            buttonBottom1original.setVisibility(visible);
            buttonBottom2original.setVisibility(visible);
            buttonBottom3original.setVisibility(visible);
        }
    }


    public void saveSelectedTheme(String selectedTheme)
    {
        //variables
        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        sharedPref.edit().putString("selectedTheme", selectedTheme).apply();

        //change app icon
        //changeIcon(selectedTheme);

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
        Log.i("getSelectedTheme", st);

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


    public void changeIcon(String selectedTheme)
    {
        PackageManager manager = getPackageManager();

        //disable icons
        //manager.setComponentEnabledSetting(new ComponentName(MainActivity.this, "android.example.eggtimerTest.MainActivity"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        manager.setComponentEnabledSetting(new ComponentName(MainActivity.this, "android.example.eggtimerTest.MainActivityOriginal"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        manager.setComponentEnabledSetting(new ComponentName(MainActivity.this, "android.example.eggtimerTest.MainActivityInverted"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        manager.setComponentEnabledSetting(new ComponentName(MainActivity.this, "android.example.eggtimerTest.MainActivityBlueberry"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

        //enable icon
        if(selectedTheme.equals("Original"))
        {
            manager.setComponentEnabledSetting(new ComponentName(MainActivity.this, "android.example.eggtimerTest.MainActivityOriginal"), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
            Toast.makeText(MainActivity.this, "Original Theme Selected", Toast.LENGTH_LONG).show();
        }
        else if(selectedTheme.equals("Inverted"))
        {
            manager.setComponentEnabledSetting(new ComponentName(MainActivity.this, "android.example.eggtimerTest.MainActivityInverted"), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
            Toast.makeText(MainActivity.this, "Inverted Theme Selected", Toast.LENGTH_LONG).show();
        }
        else if(selectedTheme.equals("Blueberry"))
        {
            manager.setComponentEnabledSetting(new ComponentName(MainActivity.this, "android.example.eggtimerTest.MainActivityBlueberry"), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
            Toast.makeText(MainActivity.this, "Blueberry Theme Selected", Toast.LENGTH_LONG).show();
        }
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
        textViewCounter = findViewById(R.id.textViewCounter);
        textViewThemeTitle = findViewById(R.id.textViewThemeTitle);
        textViewSettingsTitle = findViewById(R.id.textViewSettingsTitle);
        textViewAboutTitle = findViewById(R.id.textViewAboutTitle);
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

//        requestWindowFeature(Window.FEATURE_LEFT_ICON);
//        setContentView(R.layout.main);
//        setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,R.drawable.ic_launcher);

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