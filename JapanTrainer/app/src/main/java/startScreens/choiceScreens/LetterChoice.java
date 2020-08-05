package startScreens.choiceScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.japantrainer.R;

import helpClasses.managerClasses.FontManager;
import helpClasses.managerClasses.GlobalVariables;

public class LetterChoice extends AppCompatActivity implements View.OnClickListener {

    FontManager font;
    GlobalVariables global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_letter);

        font = new FontManager(this);
        global = (GlobalVariables) getApplication();

        Button romaji = findViewById(R.id.romaji);
        Button japanese = findViewById(R.id.japanese);

        romaji.setOnClickListener(this);
        japanese.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.romaji:
                font.setRomaji();
                openGameChoice();
                break;

            case R.id.japanese:
                font.setJapanese();
                openGameChoice();
                break;

        }
    }

    // Opens Game Activity (for Button)
    public void openGameChoice(){
        Intent intent = new Intent(this, GameChoice.class);
        startActivity(intent);
    }
}