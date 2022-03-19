package com.example.jsactivity.pluginimpl;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;

public class InterestingConfigChanges {
    private final Configuration mLastConfiguration = new Configuration();
    private final int mFlags;
    private int mLastDensity;

    public InterestingConfigChanges() {
        this(ActivityInfo.CONFIG_LOCALE
                | ActivityInfo.CONFIG_UI_MODE | ActivityInfo.CONFIG_SCREEN_LAYOUT);
    }

    public InterestingConfigChanges(int flags) {
        mFlags = flags;
    }

    public boolean applyNewConfig(Resources res) {
//        int configChanges = mLastConfiguration.updateFrom(
//                Configuration.generateDelta(mLastConfiguration, res.getConfiguration()));
//        boolean densityChanged = mLastDensity != res.getDisplayMetrics().densityDpi;
//        if (densityChanged || (configChanges & (mFlags)) != 0) {
//            mLastDensity = res.getDisplayMetrics().densityDpi;
//            return true;
//        }
        return false;
    }
}