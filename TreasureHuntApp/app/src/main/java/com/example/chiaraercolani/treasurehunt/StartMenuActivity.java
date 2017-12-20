package com.example.chiaraercolani.treasurehunt;

import android.content.Intent;
import android.preference.EditTextPreference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Main activity
 * Show the main/Start menu
 * Allow user to choose to join a hunt or create/edit a hunt or go to settings
 */
public class StartMenuActivity extends AppCompatActivity {

    private final static int CREATE_ACTIVITY=0;
    private final static int JOIN_ACTIVITY=1;
    private final static int SETTINGS_ACTIVITY=2;

    String InputUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
        InputUser = PreferenceManager.getDefaultSharedPreferences(this).getString("pref_username",null);

        TextView WelcomeMsg = (TextView) findViewById(R.id.welcome_msg_id);

        //use username to personalize main activity
        if (InputUser != null && !InputUser.isEmpty()){
            WelcomeMsg.setText("Welcome "+ InputUser+ "!");
        }else{
            WelcomeMsg.setText("Welcome to Treasure Hunt!");
        }



    }

    //close app
    @Override
    public void onBackPressed(){
        finish();
        System.exit(0);
    }

    // open activities according to which putton has been pressed
    public void buttonClicked(View view){
        Intent intent;
        switch(view.getId()){
            case R.id.btn_create:
                //user waints to create/edit a hunt
                intent = new Intent(this,OnBuildingHuntActivity.class);
                startActivityForResult(intent,CREATE_ACTIVITY);
                break;
            case R.id.btn_join:
                //user wants to join a hunt
                intent = new Intent(this,JoinHuntActivity.class);
                startActivityForResult(intent,JOIN_ACTIVITY);
                break;
            case R.id.btn_settings:
                //user wantw to change the settings
                intent = new Intent(this,SettingsActivity.class);
                startActivityForResult(intent,SETTINGS_ACTIVITY);
                break;
        }


    }
}
