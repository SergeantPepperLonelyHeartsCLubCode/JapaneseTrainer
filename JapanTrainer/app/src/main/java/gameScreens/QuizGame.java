

// Screen for a Quiz
// Created 30.07.2020

package gameScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.japantrainer.R;

import java.util.ArrayList;
import java.util.Random;

import helpClasses.managerClasses.GlobalVariables;
import helpClasses.managerClasses.PointsManager;
import helpClasses.managerClasses.WordsManager;

public class QuizGame extends AppCompatActivity {

    // For showing and changing points
    private PointsManager points;
    // For getting the word
    WordsManager wordsManager;

    // Strings for question (for TextView) and answer (for Edit), 3 words for buttons
    private String question;
    private String answer;
    private String[] otherWords;

    // UI Variables
    private TextView textView;

    private ArrayList<Button> buttons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_quiz);

        // Initialization
        points = new PointsManager(this);
        wordsManager = new WordsManager(this);

        // Getting all the words
        String[] tmp = wordsManager.getWord();
        answer = tmp[0];
        question = tmp[1];
        otherWords = wordsManager.getThreeWords();

        // Setting the word to the textview
        textView = findViewById(R.id.question);
        textView.setText(answer);

        // Setting points to the TextView
        textView = findViewById(R.id.points);
        textView.setText(String.valueOf(points.getPoints()));

        //Setting text to buttons
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        // Add buttons to ArrayList
        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btn4);

        int rightButton = RandomBtn();

        // Setting text and setOnClickListener to Buttons
        int i = 0;
        int j = 0;
        for (Button btn : buttons){

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button b = (Button)v;
                    String buttonText = b.getText().toString();

                    if (buttonText.equals(question)) {
                        points.incrementPoints();
                    }

                    // To store points in the phone memory
                    points.storePoints();

                    openQuizGame();
                }
            });

            if (i == rightButton){
                btn.setText(question);
            }

            if (i != rightButton){
                btn.setText(otherWords[j]);
                j++;
            }
            i++;
        }

    }

    // Gives random number between 0 and 3
    private int RandomBtn(){
        Random rand = new Random();
        int tmp = rand.nextInt(4);
        return tmp;
    }

    // Going to the next screen
    private void openQuizGame(){
        Intent intent = new Intent(this, QuizGame.class);
        startActivity(intent);
    }

}