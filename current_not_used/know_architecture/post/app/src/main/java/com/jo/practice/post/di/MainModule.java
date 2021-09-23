package com.jo.practice.post.di;

import android.content.Context;

import androidx.databinding.DataBindingUtil;

import com.jo.practice.post.R;
import com.jo.practice.post.databinding.ActivityMainBinding;
import com.jo.practice.post.ui.MainActivity;
import com.jo.practice.post.ui.post.PostFragment;
import com.jo.practice.post.ui.post.PostModule;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainModule {
    @Provides
    @ActivityScope
    static ActivityMainBinding provideBinding(MainActivity activity){
        return DataBindingUtil.setContentView(activity, R.layout.activity_main);
    }

    @Provides
    @ActivityContext
    static Context provideContext(MainActivity activity){
        return activity;
    }

    @FragmentScope
    @ContributesAndroidInjector(modules = PostModule.class)
    abstract PostFragment getPostFragment();
}
