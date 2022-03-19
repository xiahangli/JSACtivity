package com.example.thirdapp;

import android.os.Bundle;
import android.util.Log;

/**
 * @author Henry
 * @Date 2022/3/20  12:11 AM
 * @Email 2427417167@qq.com
 */
public class ThirdActivity extends ThirdApp {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void doFunc(String app) {
        Log.e("xia1", "doFunc:third " + app);
    }
}
