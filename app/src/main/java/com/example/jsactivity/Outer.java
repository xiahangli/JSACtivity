package com.example.jsactivity;

import android.util.Log;

import dagger.Module;

/**
 * @author Henry
 * @Date 2022/3/17  1:13 AM
 * @Email 2427417167@qq.com
 */

@Module
public class Outer {
    private static final String TAG = "Outer";
    public void setA() {
        Log.e(TAG, "setA: ");


    }

    Runnable r = () -> {

    };


}
