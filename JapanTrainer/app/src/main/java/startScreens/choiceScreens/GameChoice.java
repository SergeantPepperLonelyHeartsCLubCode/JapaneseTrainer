
// FOr the choice of the game
// Created 30.07.2020

package startScreens.choiceScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.japantrainer.R;

import gameScreens.QuizGame;
import gameScreens.TextGame;
import helpClasses.managerClasses.PointsManager;

public class GameChoice extends AppCompatActivity implements View.OnClickListener {

    PointsManager points;
    private Toolbar toolbar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_game);

        // Initialization
        points = new PointsManager(this);

        // Setting Toolbar
        toolbar = findViewById(R.id.homescreen_toolbar);
        setSupportActionBar(toolbar);
        //Setting up the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Setting points from to TextView
        textView = findViewById(R.id.points);
        textView.setText(String.valueOf(points.getPoints()));

        // Setting the buttons
        Button text_game = findViewById(R.id.text_game);
        Button quiz_game = findViewById(R.id.quiz_game);

        text_game.setOnClickListener(this);
        quiz_game.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.text_game:
                // Setting points to 0
                points.setPointsNull();

                Intent intent = new Intent(this, TextGame.class);
                startActivity(intent);
                break;

            case R.id.quiz_game:
                // Setting points to 0
                points.setPointsNull();

                intent = new Intent(this, QuizGame.class);
                startActivity(intent);
                break;

        }
    }
}