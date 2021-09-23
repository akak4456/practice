package com.makeus.kkongi.architecture.dagger.cafe;

import dagger.Component;

@Component(modules = CafeModule.class)
public interface CafeComponent {
    void inject(Cafe cafe);
}
