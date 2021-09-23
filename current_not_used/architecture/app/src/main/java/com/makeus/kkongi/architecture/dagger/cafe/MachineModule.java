package com.makeus.kkongi.architecture.dagger.cafe;

import dagger.Module;
import dagger.Provides;

@Module
public class MachineModule {
    @Provides
    CoffeeBean provideCoffeeBean(){
        return new CoffeeBean();
    }
}
