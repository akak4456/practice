package com.makeus.kkongi.architecture_clone_code.di;

import androidx.lifecycle.ViewModelProvider;

import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class AppViewModelFactory implements ViewModelProvider.Factory {
    private Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;
}
