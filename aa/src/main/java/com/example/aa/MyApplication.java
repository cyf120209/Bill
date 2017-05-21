package com.example.aa;

import android.app.Application;

import com.example.aa.utils.DBManager;

/**
 * Created by Administrator on 2017/4/16.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DBManager.getInstance(getApplicationContext());
    }
}
