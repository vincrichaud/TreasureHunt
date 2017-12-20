package com.example.chiaraercolani.treasurehunt;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Class used to display the SettingsFragment
 */
public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(R.style.SettingsStyle);
        //Display preferences fragment
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }



}

