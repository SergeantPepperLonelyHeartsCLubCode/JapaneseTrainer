
// Class for giving words to the TextGame and QuizGame

package helpClasses.managerClasses;

import android.app.Activity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import helpClasses.syllableClasses.HiraganaSyllable;
import helpClasses.syllableClasses.KatakanaSyllable;
import helpClasses.wordClasses.HiraganaWord;
import helpClasses.wordClasses.KatakanaWord;

public class WordsManager {

    // For initilialization of GlobalVariables
    Activity activity;
    GlobalVariables global;
    ChoiceManager font;

    public WordsManager(Activity activity){
        this.activity = activity;
        global = (GlobalVariables) activity.getApplication();
        font = new ChoiceManager(activity);
    }

    // Get IDs of all the words in the DB
    // Used in FontChoice
    public void setID(){

        DatabaseManager db = new DatabaseManager(activity);

        // If we have syllables
        if (font.isSyllables()){
            if (font.isKatakana()){
                global.id_permanent = db.getAllIdSyllable(font.katakana);

                for (Integer tmp : global.id_permanent)
                {
                    global.id_tmp.add(tmp);
                }
            }
            else {
                global.id_permanent = db.getAllIdSyllable(font.hiragana);

                for (Integer tmp : global.id_permanent)
                {
                    global.id_tmp.add(tmp);
                }
            }
        }
        // If we have words
        else {

            if (font.isKatakana()) {
                global.id_permanent = db.getAllId(font.katakana);

                for (Integer tmp : global.id_permanent) {
                    global.id_tmp.add(tmp);
                }
            } else {
                global.id_permanent = db.getAllId(font.hiragana);

                for (Integer tmp : global.id_permanent) {
                    global.id_tmp.add(tmp);
                }
            }
        }
    }

    // Gives the word
    // Used in TextGame, QuizGame
    public String[] getWord(){

        String[] word = new String[2];

        // if game mode sillables
        if (font.isSyllables()){
            // if user wants to train with Katakana font
            if (font.isKatakana()) {
                DatabaseManager db = new DatabaseManager(activity);
                KatakanaSyllable katakanaSyllable;
                global.current_id = getID();
                katakanaSyllable = (KatakanaSyllable) db.getSyllable(global.current_id, font.katakana);

                // Romaji Training
                if (font.isRomaji()) {
                    word[0] = katakanaSyllable.getRomaji();
                    word[1] = katakanaSyllable.getKatakana();
                }
                // Katakana Training
                else {
                    word[0] = katakanaSyllable.getKatakana();
                    word[1] = katakanaSyllable.getRomaji();

                    Toast toast = Toast.makeText(activity.getApplicationContext(), word[0], Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                    toast.show();
                }
            }

            // if user wants to train with Kanji or Hiragana fonts
            else {
                DatabaseManager db = new DatabaseManager(activity);
                HiraganaSyllable hiraganaSyllable;
                global.current_id = getID();
                hiraganaSyllable = (HiraganaSyllable) db.getSyllable(global.current_id, font.hiragana);

                // Hiragana
                if (font.isHiragana()) {
                    // Romaji Training
                    if (font.isRomaji()) {
                        word[0] = hiraganaSyllable.getRomaji();
                        word[1] = hiraganaSyllable.getHiragana();
                    }
                    // Hiragana Training
                    else {
                        word[0] = hiraganaSyllable.getHiragana();
                        word[1] = hiraganaSyllable.getRomaji();
                    }
                }
            }
        }

        // if game mode words
        else {
            // if user wants to train with Katakana font
            if (font.isKatakana()) {
                DatabaseManager db = new DatabaseManager(activity);
                KatakanaWord katakanaWord;
                global.current_id = getID();
                katakanaWord = (KatakanaWord) db.getWord(global.current_id, font.katakana);

                // Romaji Training
                if (font.isRomaji()) {
                    word[0] = katakanaWord.getRomaji();
                    word[1] = katakanaWord.getKatakana();
                }
                // Katakana Training
                else {
                    word[0] = katakanaWord.getKatakana();
                    word[1] = katakanaWord.getRomaji();
                }
            }

            // if user wants to train with Kanji or Hiragana fonts
            else {

                DatabaseManager db = new DatabaseManager(activity);
                HiraganaWord hiraganaWord;
                global.current_id = getID();
                hiraganaWord = (HiraganaWord) db.getWord(global.current_id, font.hiragana);

                // Hiragana
                if (font.isHiragana()) {
                    // Romaji Training
                    if (font.isRomaji()) {
                        word[0] = hiraganaWord.getRomaji();
                        word[1] = hiraganaWord.getHiragana();
                    }
                    // Hiragana Training
                    else {
                        word[0] = hiraganaWord.getHiragana();
                        word[1] = hiraganaWord.getRomaji();
                    }
                }

                // Kanji
                else {
                    // Romaji Training
                    if (font.isRomaji()) {
                        word[0] = hiraganaWord.getRomaji();
                        word[1] = hiraganaWord.getKanji();
                    }
                    // Kanji Training
                    else {
                        word[0] = hiraganaWord.getKanji();
                        word[1] = hiraganaWord.getRomaji();
                    }
                }
            }
        }

        return word;
    }
    // Gives randomID (Complementary function to getWord)
    private int getID(){
        int size = global.id_tmp.size();

        // When we used all the words, we take all IDs from id_permanent
        if (size == 0) {
            for (Integer tmp : global.id_permanent) {
                global.id_tmp.add(tmp);
            }

            size = global.id_tmp.size();

        }

        // Taking random id from id_tmp
        Random rand = new Random();
        int tmp = rand.nextInt(size);
        int tmp2 = global.id_tmp.get(tmp);

        // Delete used ID in id_tmp
        global.id_tmp.remove(tmp);

        return tmp2;
    }

    // Gives 3 random words
    // Used in QuizGame
    public String[] getThreeWords(){

        DatabaseManager db = new DatabaseManager(activity);
        // 3 random words
        String[] words = new String[3];
        // IDs of 3 random words
        int[] randomWords = getThreeRandomID(global.current_id);


        if (font.isSyllables()){
            // if user wants to train with Katakana font
            if (font.isKatakana()){
                // For getting strings
                KatakanaSyllable tmp;

                // Romaji Training
                if (font.isRomaji()){

                    tmp = (KatakanaSyllable) db.getSyllable(randomWords[0], font.katakana);
                    words[0] = tmp.getKatakana();
                    tmp = (KatakanaSyllable) db.getSyllable(randomWords[1], font.katakana);
                    words[1] = tmp.getKatakana();
                    tmp = (KatakanaSyllable) db.getSyllable(randomWords[2], font.katakana);
                    words[2] = tmp.getKatakana();
                }
                // Katakana Training
                else {
                    tmp = (KatakanaSyllable) db.getSyllable(randomWords[0], font.katakana);
                    words[0] = tmp.getRomaji();
                    tmp = (KatakanaSyllable) db.getSyllable(randomWords[1], font.katakana);
                    words[1] = tmp.getRomaji();
                    tmp = (KatakanaSyllable) db.getSyllable(randomWords[2], font.katakana);
                    words[2] = tmp.getRomaji();
                }
            }

            // if user wants to train with Kanji or Hiragana fonts
            else {

                // For getting strings
                HiraganaSyllable tmp;

                // Hiragana
                if (font.isHiragana()){
                    // Romaji Training
                    if (font.isRomaji()){
                        tmp = (HiraganaSyllable) db.getSyllable(randomWords[0], font.hiragana);
                        words[0] = tmp.getHiragana();
                        tmp = (HiraganaSyllable) db.getSyllable(randomWords[1], font.hiragana);
                        words[1] = tmp.getHiragana();
                        tmp = (HiraganaSyllable) db.getSyllable(randomWords[2], font.hiragana);
                        words[2] = tmp.getHiragana();
                    }
                    // Hiragana Training
                    else {
                        tmp = (HiraganaSyllable) db.getSyllable(randomWords[0], font.hiragana);
                        words[0] = tmp.getRomaji();
                        tmp = (HiraganaSyllable) db.getSyllable(randomWords[1], font.hiragana);
                        words[1] = tmp.getRomaji();
                        tmp = (HiraganaSyllable) db.getSyllable(randomWords[2], font.hiragana);
                        words[2] = tmp.getRomaji();
                    }
                }
            }
        } // End of if syllables

        else {

            // if user wants to train with Katakana font
            if (font.isKatakana()) {
                // For getting strings
                KatakanaWord tmp;

                // Romaji Training
                if (font.isRomaji()) {

                    tmp = (KatakanaWord) db.getWord(randomWords[0], font.katakana);
                    words[0] = tmp.getKatakana();
                    tmp = (KatakanaWord) db.getWord(randomWords[1], font.katakana);
                    words[1] = tmp.getKatakana();
                    tmp = (KatakanaWord) db.getWord(randomWords[2], font.katakana);
                    words[2] = tmp.getKatakana();
                }
                // Katakana Training
                else {
                    tmp = (KatakanaWord) db.getWord(randomWords[0], font.katakana);
                    words[0] = tmp.getRomaji();
                    tmp = (KatakanaWord) db.getWord(randomWords[1], font.katakana);
                    words[1] = tmp.getRomaji();
                    tmp = (KatakanaWord) db.getWord(randomWords[2], font.katakana);
                    words[2] = tmp.getRomaji();
                }
            }

            // if user wants to train with Kanji or Hiragana fonts
            else {

                // For getting strings
                HiraganaWord tmp;

                // Hiragana
                if (font.isHiragana()) {
                    // Romaji Training
                    if (font.isRomaji()) {
                        tmp = (HiraganaWord) db.getWord(randomWords[0], font.hiragana);
                        words[0] = tmp.getHiragana();
                        tmp = (HiraganaWord) db.getWord(randomWords[1], font.hiragana);
                        words[1] = tmp.getHiragana();
                        tmp = (HiraganaWord) db.getWord(randomWords[2], font.hiragana);
                        words[2] = tmp.getHiragana();
                    }
                    // Hiragana Training
                    else {
                        tmp = (HiraganaWord) db.getWord(randomWords[0], font.hiragana);
                        words[0] = tmp.getRomaji();
                        tmp = (HiraganaWord) db.getWord(randomWords[1], font.hiragana);
                        words[1] = tmp.getRomaji();
                        tmp = (HiraganaWord) db.getWord(randomWords[2], font.hiragana);
                        words[2] = tmp.getRomaji();
                    }
                }

                // Kanji
                else {
                    // Romaji Training
                    if (font.isRomaji()) {
                        tmp = (HiraganaWord) db.getWord(randomWords[0], font.hiragana);
                        words[0] = tmp.getKanji();
                        tmp = (HiraganaWord) db.getWord(randomWords[1], font.hiragana);
                        words[1] = tmp.getKanji();
                        tmp = (HiraganaWord) db.getWord(randomWords[2], font.hiragana);
                        words[2] = tmp.getKanji();
                    }
                    // Kanji Training
                    else {
                        tmp = (HiraganaWord) db.getWord(randomWords[0], font.hiragana);
                        words[0] = tmp.getRomaji();
                        tmp = (HiraganaWord) db.getWord(randomWords[1], font.hiragana);
                        words[1] = tmp.getRomaji();
                        tmp = (HiraganaWord) db.getWord(randomWords[2], font.hiragana);
                        words[2] = tmp.getRomaji();
                    }
                }
            }
        }
        return words;
    }
    // Gives randomID (Complementary function to getThreeWords)
    private int[] getThreeRandomID(int id){
        int[] ids = new int[3];
        ArrayList<Integer> tmpList = new ArrayList<>();

        for (Integer tmp : global.id_permanent) {
            // Check
            if (tmp != id)
                tmpList.add(tmp);
        }

        Collections.shuffle(tmpList);

        ids[0] = tmpList.get(0);
        ids[1] = tmpList.get(1);
        ids[2] = tmpList.get(2);

        return ids;
    }
}
