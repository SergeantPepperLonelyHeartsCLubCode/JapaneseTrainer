
// Screen where user can start the game
// Created 24.07.2020

package startScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import helpClasses.DatabaseManager;
import helpClasses.managerClasses.InsertManager;
import helpClasses.managerClasses.PointsManager;
import startScreens.choiceScreens.FontChoice;

import com.example.japantrainer.R;

public class HomeScreen extends AppCompatActivity {


    TextView textView;
    PointsManager points;
    Animation homescreen_animation;
    ImageView image;

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

        // Inserting the words
        InsertManager insert = new InsertManager(this);
        insert.insertWords();

        // Setting points from to TextView
        textView = findViewById(R.id.points);
        textView.setText(String.valueOf(points.getPoints()));

        // Button for starting the game
        Button btn = findViewById(R.id.startGame);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openGameActivity();
            }
        });
    }

    // Opens Font Choice (for Button)
    private void openGameActivity(){
        Intent intent = new Intent(this, FontChoice.class);
        startActivity(intent);
    }

}
