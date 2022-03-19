package com.example.jsactivity;

import android.util.Log;

import javax.inject.Inject;

/**
 * @author Henry
 * @Date 2022/3/17  3:40 AM
 * @Email 2427417167@qq.com
 */
public class NextSubModuleElement {
    private static final String TAG = "NextSubModuleElement";

    @Inject
    public NextSubModuleElement(SubModuleElement element) {
        Log.e(TAG, "NextSubModuleElement: ");
    }
}
