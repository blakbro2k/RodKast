package net.asg.game;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;
/**
 * Created by Blakbro2k on 6/25/2017.
 */

public class MainApplication extends Application {
    public FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate() {
        super.onCreate();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "net.asg.game.android.AndroidLauncher");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "HomeScreen");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "screen");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
}
