package net.asg.game;

import android.app.Application;

//import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.firebase.analytics.FirebaseAnalytics;
/**
 * Created by Blakbro2k on 6/25/2017.
 */

public class MainApplication extends Application {
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate() {
        super.onCreate();

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        //GoogleAnalytics.getInstance(this).newTracker(R.xml.app_tracker_config);


    }
}
