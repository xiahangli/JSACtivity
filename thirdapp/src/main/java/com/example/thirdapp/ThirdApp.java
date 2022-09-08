package com.example.thirdapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
            FrameLayout viewGroup = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.sublayout,null, false);
            ImageView view = viewGroup.findViewById(R.id.iv);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(200, 100);
            layoutParams.topMargin = 30;
            view.setLayoutParams(layoutParams);
            view.setBackgroundColor(0xff00ff00);
            ((CustomLayout)findViewById(R.id.ll)).addView(viewGroup,view.getLayoutParams());
            cache.put(i, viewGroup);
        }

        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                Log.e("CustomLayout", "run: CacheSize " + cache.values().size());
                cache.forEach((key, value) -> {
                    ViewGroup viewGroup = (ViewGroup) value;
                    View child = viewGroup.getChildAt(0);
                    ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
                    layoutParams.width = 100;
                    layoutParams.height = 100;
                    child.requestLayout();
                    child.setLayoutParams(layoutParams);
                    Log.e("CustomLayout", "run: requestLayout key " + key + " value " + value);
                });//,

                CustomLayout customLayout = (CustomLayout) findViewById(R.id.ll);
//                customLayout.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
                        customLayout.removeAllViews();
                        for (int i = 0; i < 3; i++) {
                            if (cache.get(i).getParent() != null){
                                ((ViewGroup)cache.get(i).getParent()).removeView(cache.get(i));
                            }
                            customLayout.addView(cache.get(i));
                            Log.e("CustomLayout", "add view");

                        }
//                    }
//                }, 1000);
//                customLayout.postDelayed(() ->

//                10);

//                customLayout.postDelayed(() -> {
//                    ViewGroup viewGroup = (ViewGroup) customLayout.getChildAt(0);
//                    View child = viewGroup.getChildAt(0);
//                    ViewGroup.LayoutParams lp = child.getLayoutParams();
//                    lp.height = 100;
//                    lp.width = 100;
//
//                    child.requestLayout();
//                    child.setLayoutParams(lp);
//                    Log.e("CustomLayout", "run: chid " + child);
//                },6000);


//                cache.forEach((key, value) -> {
//                    ViewGroup.LayoutParams layoutParams = value.getLayoutParams();
//                    layoutParams.height = 400;
//                    layoutParams.width = 200;
//                    value.requestLayout();
//                    Log.e("CustomLayout", "run: requestLayout key " + key + " value " + value);
//                });

            }
        }, 1000);

    }
}