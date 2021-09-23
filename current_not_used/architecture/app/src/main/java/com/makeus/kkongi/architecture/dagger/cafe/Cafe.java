package com.makeus.kkongi.architecture.dagger.cafe;


import javax.inject.Inject;

public class Cafe {
    @Inject
    Machine coffeeMachine;

    public Cafe(){
        DaggerCafeComponent.create().inject(this);
    }

    public Coffee orderCoffee(){
        return coffeeMachine.extract();
    }
}
