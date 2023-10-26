package com.makeus.kkongi.architecture.dagger.abstract_multi_module;

import java.util.Set;

import dagger.Module;
import dagger.multibindings.Multibinds;

@Module
public abstract class MultibindsModules {
    @Multibinds
    abstract Set<String> strings();
}
