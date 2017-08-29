package com.andronicuskim.newsapp;

import android.app.Activity;
import android.app.Application;

import timber.log.Timber;

public class NewsApplication extends Application {

    private NewsApplicationComponent mComponent;

    public NewsApplication(){}

    public static NewsApplication get(Activity activity){
        return (NewsApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        mComponent = DaggerNewsApplicationComponent.builder()
                .newsApplicationModule(new NewsApplicationModule(this))
                .build();
    }
    public NewsApplicationComponent getComponent(){
        return mComponent;
    }

}
