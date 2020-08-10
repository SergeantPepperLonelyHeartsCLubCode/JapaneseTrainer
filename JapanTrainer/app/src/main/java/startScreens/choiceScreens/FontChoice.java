package startScreens.choiceScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.japantrainer.R;

import helpClasses.managerClasses.ChoiceManager;
import helpClasses.managerClasses.PointsManager;
import helpClasses.managerClasses.WordsManager;

public class FontChoice extends AppCompatActivity implements View.OnClickListener {

    // Variable for stroing font choice
    private ChoiceManager font;
    // Variable for setting all IDs
    private WordsManager words;

    private TextView textView;
    private PointsManager points;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_font);
        // Initialization
        font = new ChoiceManager(this);
        words = new WordsManager(this);
        points = new PointsManager(this);

        // Setting Toolbar
        toolbar = findViewById(R.id.homescreen_toolbar);
        setSupportActionBar(toolbar);
        //Setting up the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Setting points from to TextView
        textView = findViewById(R.id.points);
        textView.setText(String.valueOf(points.getPoints()));


        Button katakana = findViewById(R.id.katakana);
        Button hiragana = findViewById(R.id.hiragana);
        Button kanji = findViewById(R.id.kanji);

        katakana.setOnClickListener(this);
        hiragana.setOnClickListener(this);
        kanji.setOnClickListener(this);

    }

    // Setting font choice
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.katakana:
                font.setKatakana();
                openLetterChoice();
                break;

            case R.id.hiragana:
                font.setHiragana();
                openLetterChoice();
                break;

            case R.id.kanji:
                font.setKanji();
                openLetterChoice();
                break;

        }
    }

    // Opens Letter Choice (for Button)
    public void openLetterChoice(){
        // Setting IDs of the words
        words.setID();

        Intent intent = new Intent(this, LetterChoice.class);
        startActivity(intent);
    }
}













