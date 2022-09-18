package com.example.thirdapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Henry
 * @Date 2022/9/18  11:20 PM
 * @Email 2427417167@qq.com
 */
public class MyView extends View {
    public int viewWidth;
    public int viewHeight;
    public int[] loc = new int[2];//loc[0]为第几行
    public int[] size = new int[2];//size[0]为行占有的数量

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


}
