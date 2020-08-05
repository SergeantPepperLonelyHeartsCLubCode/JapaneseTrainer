
// Screen where user can start the game
// Created 24.07.2020

package startScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import helpClasses.DatabaseManager;
import helpClasses.managerClasses.InsertManager;
import startScreens.choiceScreens.FontChoice;

import com.example.japantrainer.R;

public class HomeScreen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_home);

        // Inserting the words
        InsertManager insert = new InsertManager(this);
        insert.insertWords();

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
