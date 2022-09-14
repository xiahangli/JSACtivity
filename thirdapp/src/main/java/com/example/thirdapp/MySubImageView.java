package com.example.thirdapp;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @author Henry
 * @Date 2022/9/15  1:29 AM
 * @Email 2427417167@qq.com
 */
public class MySubImageView extends ImageView {
    public MySubImageView(Context context) {
        super(context);
    }

    public MySubImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
