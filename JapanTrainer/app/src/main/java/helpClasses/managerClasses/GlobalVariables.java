


// Class for storing variables across the app
// Created 24.07.2020


package helpClasses.managerClasses;

import android.app.Application;

import java.util.ArrayList;

public class GlobalVariables extends Application {
    // For choosing font and letters
    String font;
    String letter;

    int points = 0;

    // List of all IDs
    ArrayList<Integer> id_permanent = new ArrayList<>();
    // List of IDs to not to repeat the words
    ArrayList<Integer> id_tmp = new ArrayList<>();

    // To know which word is right at each moment of the game
    int current_id;
}
