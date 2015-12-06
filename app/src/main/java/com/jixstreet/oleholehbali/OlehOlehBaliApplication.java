package com.jixstreet.oleholehbali;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.jixstreet.oleholehbali.utils.CommonConstants;

/**
 * Created by satryaway on 10/17/2015.
 * Singleton for application
 */
public class OlehOlehBaliApplication extends Application {
    private static OlehOlehBaliApplication instance;
    private SharedPreferences preferences;

    public synchronized static OlehOlehBaliApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        preferences = getSharedPreferences(CommonConstants.OOB_APP, Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPreferences(){
        return preferences;
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean(CommonConstants.IS_LOGGED_IN, false);
    }
}
