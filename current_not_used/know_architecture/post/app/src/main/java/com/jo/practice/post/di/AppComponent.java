package com.jo.practice.post.di;

import com.jo.practice.post.App;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityModule.class,
        AppModule.class
})
public interface AppComponent extends AndroidInjector<App> {
    @Component.Factory
    abstract class Factory implements AndroidInjector.Factory<App>{

    }
}
