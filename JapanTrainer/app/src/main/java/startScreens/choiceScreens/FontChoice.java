package startScreens.choiceScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.japantrainer.R;

import helpClasses.managerClasses.FontManager;
import helpClasses.managerClasses.GlobalVariables;
import helpClasses.managerClasses.WordsManager;

public class FontChoice extends AppCompatActivity implements View.OnClickListener {

    // Variable for stroing font choice
    private FontManager font;
    // Variable for setting all IDs
    private WordsManager words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_choice);
        // Initialization
        font = new FontManager(this);
        words = new WordsManager(this);

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













