package com.example.thirdapp;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * @author Henry
 * @Date 2022/9/15  1:29 AM
 * @Email 2427417167@qq.com
 */
public class MySubFrameLayout extends FrameLayout {
    public MySubFrameLayout(Context context) {
        super(context);
    }

    public MySubFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
