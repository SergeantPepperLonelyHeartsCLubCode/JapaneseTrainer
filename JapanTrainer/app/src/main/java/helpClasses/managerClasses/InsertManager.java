package helpClasses.managerClasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.japantrainer.R;

import java.io.ByteArrayOutputStream;

import helpClasses.DatabaseManager;
import helpClasses.wordClasses.HiraganaWord;
import helpClasses.wordClasses.KatakanaWord;

public class InsertManager {

    // context for converting images into bitmap
    private Context context;


    public InsertManager(Context current){
        this.context = current;
    }

    // Insert the words
    public void insertWords(){
        DatabaseManager db = new DatabaseManager(context);

        if (db.isEmpty())
            words(db);
    }


    // Here we have all the words
    public void words(DatabaseManager db){
        // Variables to store pictures in DB
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bitmap;

        // Katakana Section

        // this is the code of compressing jpeg to byte[]
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bread);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new KatakanaWord("ケーキ","kēki", "cake", stream.toByteArray()));
        stream.reset();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bread);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new KatakanaWord("ピザ","piza", "pizza", stream.toByteArray()));
        stream.reset();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bread);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new KatakanaWord("トースト","tōsuto", "toast", stream.toByteArray()));
        stream.reset();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bread);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new KatakanaWord("ライス","raisu", "rice", stream.toByteArray()));
        stream.reset();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bread);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new KatakanaWord("ランチ","ranchi", "lunch set", stream.toByteArray()));
        stream.reset();


        // Hiragana Section
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bread);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new HiraganaWord("さとう", "砂糖", "satou", "sugar", stream.toByteArray()));
        stream.reset();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bread);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new HiraganaWord("さとう", "砂糖", "satou", "sugar", stream.toByteArray()));
        stream.reset();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bread);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new HiraganaWord("すし", "鮓", "sushi", "sushi", stream.toByteArray()));
        stream.reset();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bread);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new HiraganaWord("かに", "蟹", "kani", "crab", stream.toByteArray()));
        stream.reset();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bread);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        db.insertWord(new HiraganaWord(" こしょう", "胡椒", "koshou", "pepper", stream.toByteArray()));
        stream.reset();
    }


}
