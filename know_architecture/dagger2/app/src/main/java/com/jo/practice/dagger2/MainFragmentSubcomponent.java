package com.jo.practice.dagger2;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@FragmentScope
@Subcomponent(modules = MainFragmentModule.class)
public interface MainFragmentSubcomponent extends AndroidInjector<MainFragment> {

    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<MainFragment>{

    }
}
