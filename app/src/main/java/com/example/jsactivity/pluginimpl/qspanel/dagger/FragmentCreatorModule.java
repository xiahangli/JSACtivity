package com.example.jsactivity.pluginimpl.qspanel.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * @author Henry
 * @Date 2022/3/20  6:49 AM
 * @Email 2427417167@qq.com
 */
@Module
public class FragmentCreatorModule {

    @Provides
    public String provideString() {
        return new String("ssss");
    }
}
