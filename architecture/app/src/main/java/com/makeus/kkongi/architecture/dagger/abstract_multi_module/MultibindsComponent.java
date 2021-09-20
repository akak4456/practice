package com.makeus.kkongi.architecture.dagger.abstract_multi_module;

import java.util.Set;

import dagger.Component;

@Component(modules = MultibindsModules.class)
public interface MultibindsComponent {
    Set<String> getStrings();
}
