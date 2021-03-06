
// First Screen of the App
// Shows Logo
// Created 24.07.2020


package startScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.japantrainer.R;

public class SplashScreen extends AppCompatActivity {

    // Duration of wait
    private static int SplashTimeOut = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_splash);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                /* Create an Intent that will start the Menu-Activity. */
                Intent homeIntent = new Intent(SplashScreen.this, HomeScreen.class);
                startActivity(homeIntent);
                finish();
            }
        }, SplashTimeOut);
    }
}
