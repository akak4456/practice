package com.jo.practice.dagger2;

import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class App extends Application implements HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Override
    public void onCreate(){
        super.onCreate();
        DaggerAppComponent.factory()
                .create(this)
                .inject(this);
    }

    @Override
    public AndroidInjector<Object> androidInjector(){
        return dispatchingAndroidInjector;
    }
}
