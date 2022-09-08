package com.example.thirdapp;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Henry
 * @Date 2022/9/9  12:11 AM
 * @Email 2427417167@qq.com
 */
public class CustomLayout extends ViewGroup {

    List<View> views = new ArrayList<>();

    public CustomLayout(Context context) {
        super(context);
    }

    private static final String TAG = "CustomLayout";
    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        int widthT=0;
        int heightT=0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
//        Log.e(TAG, "onMeasure: mode " + mode);
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int width = MeasureSpec.makeMeasureSpec(100,MeasureSpec.EXACTLY);
            int height = MeasureSpec.makeMeasureSpec(200,MeasureSpec.EXACTLY);
            childAt.measure(width,  height);
            Log.e(TAG, "onMeasure: child at " + i + " width " + childAt.getMeasuredWidth());
            widthT += (MeasureSpec.getSize(width) +10);
            heightT +=(childAt.getMeasuredHeight() +10);

        }
        setMeasuredDimension(widthT,heightT);
        Log.e(TAG, "onMeasure: width " + widthT);
    }


    public void addItem(View view) {
        views.add(view);
        addView(view,-1);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            int left=0;
//            int top=t;
            Log.e(TAG, "onLayout: left " + left);
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                childAt.layout(left,t,left+childAt.getMeasuredWidth(),t+childAt.getMeasuredHeight());
                left+=childAt.getMeasuredWidth();
//                top+=childAt.getMeasuredHeight();
                Log.i(TAG, "onLayout: layout " + i +" left " + left );
            }
        }
    }


}
