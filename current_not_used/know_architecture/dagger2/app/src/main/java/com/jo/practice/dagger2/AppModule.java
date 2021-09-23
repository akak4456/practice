package com.jo.practice.dagger2;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = MainActivitySubcomponent.class)
public abstract class AppModule {
    @Named("app")
    @Provides
    @Singleton
    static String provideString(){
        return "String from AppModule";
    }

    @Binds
    @IntoMap
    @ClassKey(MainActivity.class)
    abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(MainActivitySubcomponent.Factory factory);
}
