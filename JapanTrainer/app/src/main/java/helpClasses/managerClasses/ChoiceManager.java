package helpClasses.managerClasses;

import android.app.Activity;

public class ChoiceManager {

    // For initilialization of GlobalVariables
    Activity activity;
    GlobalVariables global;

    // Final variables for fonts
    final static String katakana = "katakana";
    final static String hiragana = "hiragana";
    final static String kanji = "kanji";
    //Final variables for letters
    private final static String romaji = "romaji";
    private final static String japanese = "japanese";



    public ChoiceManager(Activity activity){
        this.activity = activity;
        global = (GlobalVariables) activity.getApplication();
    }

    // Setters for font
    public void setKatakana(){
        global.font = new String(katakana);
    }
    public void setHiragana(){
        global.font = new String(hiragana);
    }
    public void setKanji(){
        global.font = new String(kanji);
    }

    // Setters for letter
    public void setRomaji(){
        global.letter =  new String(romaji);
    }
    public void setJapanese(){
        global.letter = new String(japanese);
    }

    // Checks for fonts
    public boolean isKatakana(){
        if (global.font.equals(katakana))
            return true;
        else
            return false;
    }
    public boolean isHiragana(){
        if (global.font.equals(hiragana))
            return true;
        else
            return false;
    }
    public boolean isKanji(){
        if (global.font.equals(kanji))
            return true;
        else
            return false;
    }

    // Checks for letters
    public boolean isRomaji(){
        if (global.font.equals(romaji))
            return true;
        else
            return false;
    }
    public boolean isJapanese(){
        if (global.font.equals(japanese))
            return true;
        else
            return false;
    }

}
