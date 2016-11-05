package udacity.nanodegree.android.p1;

import android.app.Application;
import android.os.StrictMode;
import android.util.Log;

/**
 * Created by alexandre on 05/11/2016.
 */

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private static final boolean DEV_MODE = true;
    @Override
    public void onCreate() {
        if (DEV_MODE){
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().penaltyDeath().build());
        }
        super.onCreate();

    }
}
