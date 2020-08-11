
// Screen where user can start the game
// Created 24.07.2020

package startScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
// Import for the animation 
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import helpClasses.DatabaseManager;
import helpClasses.managerClasses.InsertManager;
import helpClasses.managerClasses.PointsManager;
import startScreens.choiceScreens.FontChoice;

import com.example.japantrainer.R;

import java.util.Locale;

public class HomeScreen extends AppCompatActivity {


    private TextView textView;
    private PointsManager points;
    private Toolbar toolbar;
    private Animation homescreen_animation;
    private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_home);


        homescreen_animation= AnimationUtils.loadAnimation(this,R.anim.homescreen_animation);
        image=findViewById(R.id.imageView2);
        image.setAnimation(homescreen_animation);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                /* Create an Intent that will start the Menu-Activity. */
                Intent homeIntent = new Intent(HomeScreen.this, HomeScreen.class);
//https://stackoverflow.com/questions/13397709/android-hide-imageview
                ImageView imgView = (ImageView)findViewById(R.id.imageView2);
                imgView .setVisibility(View.GONE);



            }
        }, 1000);




        // Initilializing
        points = new PointsManager(this);

        // Setting Toolbar
        toolbar = findViewById(R.id.homescreen_toolbar);
        setSupportActionBar(toolbar);

        // Inserting the words
        InsertManager insert = new InsertManager(this);
        insert.insertWords();

        // Setting points from to TextView
        textView = findViewById(R.id.points);
        textView.setText(String.valueOf(points.getPoints()));

        // Button for starting the game and changing Language
        Button btn = findViewById(R.id.startGame);
        Button btn2= findViewById(R.id.languagechange);


        //Switch for Changing Language
        Switch sb = findViewById(R.id.Switch);

        if(locale)


        sb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    changeLanguage("de");



                } else {
                    changeLanguage("eng");

                }
            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openGameActivity();
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            changeLanguage("de");
            }
        });


    }

    private void changeLanguage(String lang) {

        Resources res = getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale(lang);
        res.updateConfiguration(conf, dm);

    }

    // Opens Font Choice (for Button)
    private void openGameActivity(){
        Intent intent = new Intent(this, FontChoice.class);
        startActivity(intent);
    }


}
