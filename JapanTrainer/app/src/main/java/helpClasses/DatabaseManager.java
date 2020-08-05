// Manage database with words
// Created 04.08.2020

package helpClasses;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import helpClasses.wordClasses.HiraganaWord;
import helpClasses.wordClasses.KatakanaWord;
import helpClasses.wordClasses.Word;

public class DatabaseManager extends SQLiteOpenHelper {

    SQLiteDatabase db;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Words.db";

    // Both
    private static final String KEY_ID = "ID";
    private static final String ROMAJI = "ROMAJI";
    private static final String TRANSLATION = "TRANSLATION";
    private static final String IMAGE = "IMAGE";

    // Katakana
    // columns - ID (Primary key) | Katakana | Romaji | Translation | Image
    private static final String TABLE_NAME_KATAKANA = "katakana";
    private static final String KATAKANA = "KATAKANA";

    // Hiragana
    // columns - ID (Primary key) | Hiragana | Kanji | Romaji | Translation | Image
    private static final String TABLE_NAME_HIRAGANA = "hiragana";
    private static final String HIRAGANA = "HIRAGANA";
    private static final String KANJI = "KANJI";

    // Constructor
    public DatabaseManager (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.db = this.getWritableDatabase();
    }

    // Function that was in parent class
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Katakana
        String createTable = "create table " + TABLE_NAME_KATAKANA +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, KATAKANA TEXT, ROMAJI TEXT, " +
                " TRANSLATION TEXT, IMAGE BLOB)";
        db.execSQL(createTable);

        // Create Hiragana
        createTable = "create table " + TABLE_NAME_HIRAGANA +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, HIRAGANA TEXT, KANJI TEXT," +
                " ROMAJI TEXT, TRANSLATION TEXT, IMAGE BLOB)";
        db.execSQL(createTable);

    }

    // Function that was in parent class
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_KATAKANA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_HIRAGANA);
        onCreate(db);
    }

    // Inserts a word into database
    public boolean insertWord(Word word){

        if (word instanceof KatakanaWord){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            // putting values into columns
            contentValues.put(KATAKANA, ((KatakanaWord) word).getKatakana());
            contentValues.put(ROMAJI, word.getRomaji());
            contentValues.put(TRANSLATION, word.getTranslation());
            contentValues.put(IMAGE, word.getImageInfo());


            long result = db.insert(TABLE_NAME_KATAKANA, null, contentValues);

            if (result == -1){
                return false;
            }
            else{
                return true;
            }
        }

        else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            // putting values into columns
            contentValues.put(HIRAGANA, ((HiraganaWord) word).getHiragana());
            contentValues.put(KANJI, ((HiraganaWord) word).getKanji());
            contentValues.put(ROMAJI, word.getRomaji());
            contentValues.put(TRANSLATION, word.getTranslation());
            contentValues.put(IMAGE, word.getImageInfo());


            long result = db.insert(TABLE_NAME_HIRAGANA, null, contentValues);

            if (result == -1){
                return false;
            }
            else{
                return true;
            }
        }
    }

    // Takes a word from database with the ID
    public Word getWord(int id, String font){
        SQLiteDatabase db = this.getReadableDatabase();

        if (font.equals("katakana")){
            Cursor cursor = db.query(TABLE_NAME_KATAKANA, new String[] {KEY_ID,
                            KATAKANA, ROMAJI, TRANSLATION, IMAGE}, KEY_ID +
                            "=?", new String[]{String.valueOf(id)},
                    null, null, null, null);

            if (cursor != null){
                cursor.moveToFirst();
            }

            KatakanaWord word = new KatakanaWord(cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getBlob(4));

            return word;
        }
        else{
            Cursor cursor = db.query(TABLE_NAME_HIRAGANA, new String[] {KEY_ID,
                            HIRAGANA, KANJI, ROMAJI, TRANSLATION, IMAGE}, KEY_ID +
                            "=?", new String[]{String.valueOf(id)},
                    null, null, null, null);

            if (cursor != null){
                cursor.moveToFirst();
            }

            HiraganaWord word = new HiraganaWord(cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getBlob(5));

            return word;
        }



    }

    // Gives back all the IDs of the words in a Database
    public ArrayList<Integer> getAllId(String font){
        ArrayList<Integer> IdList = new ArrayList<>();

        if (font.equals("katakana")){
            String selectQuery = "SELECT ID FROM " + TABLE_NAME_KATAKANA ;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            if(cursor.moveToFirst()){
                do {

                    int tmp = Integer.parseInt(cursor.getString(0));

                    IdList.add(tmp);
                } while (cursor.moveToNext());
            }
            return IdList;
        }
        else {
            String selectQuery = "SELECT ID FROM " + TABLE_NAME_HIRAGANA ;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            if(cursor.moveToFirst()){
                do {

                    int tmp = Integer.parseInt(cursor.getString(0));

                    IdList.add(tmp);
                } while (cursor.moveToNext());
            }
            return IdList;
        }


    }

    // Delete the whole database
    public void deleteDB(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DROP TABLE " + TABLE_NAME_KATAKANA);
        db.execSQL("DROP TABLE " + TABLE_NAME_HIRAGANA);
        onCreate(db);
    }

    // Check if table Words is empty
    public boolean isEmpty(){
        SQLiteDatabase db = getWritableDatabase();
        String count = "SELECT count(*) FROM " + TABLE_NAME_KATAKANA;

        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount_katakana = mcursor.getInt(0);

        count = "SELECT count(*) FROM " + TABLE_NAME_HIRAGANA;

        mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount_hiragana = mcursor.getInt(0);

        if (icount_katakana > 0 && icount_hiragana > 0)
            return false;
        return true;
    }

}
