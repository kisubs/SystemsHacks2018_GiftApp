package com.gazua.giftapp;

/**
 * Created by jonkim on 2018-02-10.
 */

import android.app.Application;


public class App extends Application {
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
