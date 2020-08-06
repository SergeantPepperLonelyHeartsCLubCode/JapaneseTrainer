package helpClasses.managerClasses;

import android.app.Activity;
import android.content.SharedPreferences;

public class PointsManager {


    // For initilialization of GlobalVariables
    Activity activity;
    GlobalVariables global;

    public final static String preferencesName = "score";

    public PointsManager(Activity activity){
        this.activity = activity;
        global = (GlobalVariables) activity.getApplication();
    }

    public int getPoints(){
        return global.points;
    }
    public void incrementPoints(){
        global.points = global.points + 1;
    }

    public int getPointsPreferences() {
        SharedPreferences score = activity.getSharedPreferences(preferencesName, activity.MODE_PRIVATE);
        int tmp = score.getInt(preferencesName, 0);

        return tmp;
    }

    public void storePoints() {
        SharedPreferences score = activity.getSharedPreferences(preferencesName, activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = score.edit();
        editor.putInt(preferencesName, getPoints());
        editor.commit();
    }

    public void setPointsNull(){
        SharedPreferences score = activity.getSharedPreferences(preferencesName, activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = score.edit();
        editor.putInt(preferencesName, 0);
        editor.commit();
    }


}
