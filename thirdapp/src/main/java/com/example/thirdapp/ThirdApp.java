package com.example.thirdapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class ThirdApp extends AppCompatActivity {

    ArrayMap<Integer, View> cache = new ArrayMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_app);
        for (int i = 0; i < 3; i++) {
            ImageView view = new ImageView(this);
            ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(50, 50);
            layoutParams.topMargin = 30;
            view.setLayoutParams(layoutParams);
            view.setBackgroundColor(0xff00ff00);
            ((CustomLayout)findViewById(R.id.ll)).addView(view,view.getLayoutParams());
            cache.put(i, view);
        }

        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                Log.e("CustomLayout", "run: CacheSize " + cache.values().size());
                CustomLayout customLayout = (CustomLayout) findViewById(R.id.ll);
                customLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        customLayout.removeAllViews();
                        for (int i = 0; i < 3; i++) {
                            customLayout.addView(cache.get(i));
                        }
                    }
                }, 1000);
                cache.forEach((key, value) -> {
                    ViewGroup.LayoutParams layoutParams = value.getLayoutParams();
                    layoutParams.height = 200;
                    layoutParams.width = 200;
                    value.requestLayout();
                    Log.e("CustomLayout", "run: requestLayout key " + key + " value " + value);
                });

            }
        }, 1500);

    }
}