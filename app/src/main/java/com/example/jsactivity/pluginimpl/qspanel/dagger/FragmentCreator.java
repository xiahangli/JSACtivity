package com.example.jsactivity.pluginimpl.qspanel.dagger;

import com.example.jsactivity.pluginimpl.qspanel.QSFragment;

import dagger.Subcomponent;

/**
 * The subcomponent of dagger that holds all fragments that need injection.
 */
@Subcomponent
public interface FragmentCreator {
    /**
     * Factory for creating a FragmentCreator.
     */
    @Subcomponent.Builder
    interface Builder {
        FragmentCreator build();
    }

    // TODO 取消注释
    /**
     * Inject a QSFragment.
     */
    QSFragment createQSFragment();
}