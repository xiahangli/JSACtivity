package com.example.jsactivity.pluginimpl.qspanel.dagger;

import android.view.View;

import com.example.jsactivity.pluginimpl.qspanel.QSFragment;

import dagger.Module;
import dagger.Provides;

/**
 * @author Henry
 * @Date 2022/3/20  5:26 AM
 * @Email 2427417167@qq.com
 */
@Module
public class QSFragmentModule {

    @Provides
    static View provideRootView(QSFragment qsFragment) {
        return qsFragment.getView();
    }
}
