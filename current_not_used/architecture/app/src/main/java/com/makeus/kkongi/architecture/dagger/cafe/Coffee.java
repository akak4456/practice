package com.makeus.kkongi.architecture.dagger.cafe;

import javax.inject.Inject;

public class Coffee {
    @Inject
    public Coffee(CoffeeBean coffeeBean,Water water){}
}
