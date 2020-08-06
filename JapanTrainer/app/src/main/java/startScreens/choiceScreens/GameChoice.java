
// FOr the choice of the game
// Created 30.07.2020

package startScreens.choiceScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.japantrainer.R;

import gameScreens.QuizGame;
import gameScreens.TextGame;
import helpClasses.managerClasses.PointsManager;

public class GameChoice extends AppCompatActivity implements View.OnClickListener {

    // So set points to 0
    PointsManager points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_game);

        // Initialization
        points = new PointsManager(this);

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