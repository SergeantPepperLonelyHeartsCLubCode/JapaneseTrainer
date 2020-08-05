package helpClasses.managerClasses;

import android.app.Activity;

public class PointsManager {

    // For initilialization of GlobalVariables
    Activity activity;
    GlobalVariables global;

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


}
