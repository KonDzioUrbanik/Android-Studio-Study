package com.konrados.testconstraintlayout;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

public class MyApp extends Application implements Application.ActivityLifecycleCallbacks {

    private int activeActivities = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activeActivities++;
        Log.d("ACTIVITY_TRACKER", "➕ Utworzono: " + activity.getLocalClassName() +
                " | Aktywne: " + activeActivities);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activeActivities--;
        Log.d("ACTIVITY_TRACKER", "❌ Zniszczono: " + activity.getLocalClassName() +
                " | Aktywne: " + activeActivities);
    }

    // Resztę callbacków zostaw pustą:
    @Override public void onActivityStarted(Activity activity) {}
    @Override public void onActivityResumed(Activity activity) {}
    @Override public void onActivityPaused(Activity activity) {}
    @Override public void onActivityStopped(Activity activity) {}
    @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

}
