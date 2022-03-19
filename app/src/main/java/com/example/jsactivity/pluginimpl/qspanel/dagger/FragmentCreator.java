package com.example.jsactivity.pluginimpl.qspanel.dagger;

import com.example.jsactivity.pluginimpl.qspanel.QSFragment;

import dagger.Subcomponent;

/**
 * The subcomponent of dagger that holds all fragments that need injection.
 */
//TODO 移除module ,只是为了测试加入
@Subcomponent(modules = FragmentCreatorModule.class)
public interface FragmentCreator {
    /**
     * Factory for creating a FragmentCreator.
     */
    @Subcomponent.Builder
    interface Builder {
        FragmentCreator build();

    }
    String getString();

    /**
     * Inject a QSFragment.
     */
    QSFragment createQSFragment();
}