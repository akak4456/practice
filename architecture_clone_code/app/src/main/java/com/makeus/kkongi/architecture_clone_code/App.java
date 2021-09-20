package com.makeus.kkongi.architecture_clone_code;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import timber.log.Timber;

public class App extends DaggerApplication {
    @Override
    public void onCreate(){
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.factory().create(this);
    }
}
