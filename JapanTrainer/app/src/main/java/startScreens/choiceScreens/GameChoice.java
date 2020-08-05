
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

public class GameChoice extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_game);

        Button text_game = findViewById(R.id.text_game);
        Button quiz_game = findViewById(R.id.quiz_game);

        text_game.setOnClickListener(this);
        quiz_game.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.text_game:
                Intent intent = new Intent(this, TextGame.class);
                startActivity(intent);
                break;

            case R.id.quiz_game:
                intent = new Intent(this, QuizGame.class);
                startActivity(intent);
                break;

        }
    }
}