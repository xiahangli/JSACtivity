package com.example.secondapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

/**
 * @author Henry
 * @Date 2022/3/19  11:06 PM
 * @Email 2427417167@qq.com
 */
public class SecondApp extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void doFunc(String app) {
        Log.e("xia1", "doFunc: call from first app , " + app);
    }
}
