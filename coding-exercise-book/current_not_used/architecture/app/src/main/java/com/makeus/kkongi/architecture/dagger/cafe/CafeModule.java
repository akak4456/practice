package com.makeus.kkongi.architecture.dagger.cafe;

import javax.crypto.Mac;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = MachineComponent.class)
public class CafeModule {

    @Provides
    Water provideWater(){
        return new Water();
    }

    @Provides
    Machine provideMachine(MachineComponent.Builder builder){
        return new Machine(builder);
    }
}
