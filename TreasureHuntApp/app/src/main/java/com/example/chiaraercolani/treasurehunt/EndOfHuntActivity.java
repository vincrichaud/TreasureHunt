package com.example.chiaraercolani.treasurehunt;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Activity used to display the end of a hunt
 * Display a congrats message and info (distance walked and calories burned)
 */
public class EndOfHuntActivity extends AppCompatActivity {

    private int weight = 62;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_hunt);

        //if the user set it, get the user weight (to calculate calories)
        String weightString = PreferenceManager.getDefaultSharedPreferences(this).getString("pref_user_weight","");
        if(weightString.matches("\\d+(?:\\.\\d+)?"))
        {
            weight = Integer.valueOf(weightString);
        }

        //button to go back to the main menu
        Button backToMainMenuButton = (Button) findViewById(R.id.back_to_main_menu_button);
        backToMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //display the distance walked and the calories burned
        TextView tvDistanceWalked = (TextView) findViewById(R.id.textview_distance_walked);
        TextView tvCaloriesBurned = (TextView) findViewById(R.id.textview_calories_burned);
        Intent intent = getIntent();
        double distanceWalked = intent.getDoubleExtra(JoinedHuntStartActivity.DISTANCE_WALKED_EXTRA, 0);
        if(distanceWalked==0){
            tvDistanceWalked.setVisibility(View.INVISIBLE);
            tvCaloriesBurned.setVisibility(View.INVISIBLE);
        } else {
            double caloriesBurned = 3.4937*weight*(distanceWalked/4000); //calories=(0.0215xspeed^3 - 0.1765xspeed^2 + 0.8710xspeed + 1.4577)xweightx(distance/speed) speed=4kph

            tvDistanceWalked.setText(tvDistanceWalked.getText() + String.valueOf((int)distanceWalked + " meters"));
            tvCaloriesBurned.setText(tvCaloriesBurned.getText() + String.valueOf((int)caloriesBurned));
        }


    }

    @Override
    public void onBackPressed() {
        //return to main menu
        Intent intent = new Intent(this, StartMenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
