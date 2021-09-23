package com.jo.practice.dagger2;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Factory
    interface Factory extends AndroidInjector
            .Factory<App>{
    }
}
