package com.example.qlthuvien.database;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static Context applicationContext = null;

    public static Context getMyContext(){
        return applicationContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
    }
}
