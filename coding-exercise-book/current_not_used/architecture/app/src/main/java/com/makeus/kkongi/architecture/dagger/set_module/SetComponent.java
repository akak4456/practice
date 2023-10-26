package com.makeus.kkongi.architecture.dagger.set_module;

import dagger.Component;

@Component(modules=SetModule.class)
public interface SetComponent {
    void inject(Foo foo);
}
