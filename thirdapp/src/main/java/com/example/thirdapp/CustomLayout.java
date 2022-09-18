package com.example.thirdapp;

import android.content.Context;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

/**
 * @author Henry
 * @Date 2022/9/9  12:11 AM
 * @Email 2427417167@qq.com
 */
public class CustomLayout extends ViewGroup {

    List<View> views = new ArrayList<>();
    private final int MAX_ROW = 4;
    private final int MAX_COL = 4;
    private int[][] loc = new int[MAX_ROW][MAX_COL];
    private int[][] layoutLoc = new int[MAX_ROW][MAX_COL];
    private final int minGridSize = 100;

    public CustomLayout(Context context) {
        super(context);
    }

    private static final String TAG = "CustomLayout";

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthDimen = 0;
        int heightDimen = 0;
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        int widthsize = MeasureSpec.getSize(widthMeasureSpec);
        int childCount = getChildCount();
        int childIndex = 0;
        boolean measureComplete = false;
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                loc[i][j] = 0;
            }
        }
        /**
         * ** *
         * ** *
         * ** *
         * ** *
         */
        int lastMaxHeightDimen = 0;
        for (int i = 0; i < MAX_ROW; i++) {//行
            if (measureComplete) {
                break;
            }
            int tempWidthDimen = 0;
            MyView child = null;
            for (int j = 0; j < MAX_COL; j++) {//列
                child = (MyView) getChildAt(childIndex);

                //当前的位置宽度摆放不下去，继续到下一行寻找
                if (child.size[1] + j > MAX_COL) continue;

                if (loc[i][j] == 1) continue;// 跳过已经占有的位置

                int rowSize = child.size[0];
                int colSize = child.size[1];
                int childLeft = child.loc[0];
                int childTop = child.loc[1];
                for (int k = 0; k < rowSize; k++) { // 当前测量的child占有的位置信息
                    for (int l = 0; l < colSize; l++) {
                        loc[childLeft + k][childTop + l] = 1;
                    }
                }
                child.measure(MeasureSpec.makeMeasureSpec(child.viewWidth, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(child.viewHeight, MeasureSpec.EXACTLY));
                tempWidthDimen += child.getMeasuredWidth();// 每一行都统计所有的item宽度和
                lastMaxHeightDimen = Math.max(lastMaxHeightDimen, (i * minGridSize) + child.getMeasuredHeight());// 当前行的最大行高

                childIndex++;
                if (childIndex >= childCount) {
                    measureComplete = true;
                    break;//已经布局完所有的child，退出
                }
            }
            widthDimen = Math.max(widthDimen, tempWidthDimen);// 取最大的行宽作为容器行宽
        }
        heightDimen = lastMaxHeightDimen; // 高度取 max(当前行+当前行最大的child高度,上一次最大的高度)

//        for (int i = 0; i < getChildCount(); i++) {
//
//        }
        Log.e(TAG, ">>>>> onMeasure: widthDimen " + widthDimen + " heightDimen " + heightDimen);
//        for (int i = 0; i < childCount; i++) {
//            MyView childAt = (MyView) getChildAt(i);
//            //todo 考虑layoutparam
//            int width = MeasureSpec.makeMeasureSpec(childAt.viewWidth, MeasureSpec.EXACTLY);
//            int height = MeasureSpec.makeMeasureSpec(childAt.viewHeight, MeasureSpec.EXACTLY);
//            childAt.measure(width,  height);

//            final int usedWidth =  0;
//            measureChildWithMargins(childAt,widthMeasureSpec/*parentWidthMeasureSPec*/,0/*widthUsed*/,
//                    heightMeasureSpec/*parentWidthMeasureSPec*/,0);
//            Log.e(TAG, "onMeasure: child at " + i + " width " + childAt.getMeasuredWidth());
//            widthT += (childAt.getMeasuredWidth());
//            heightT +=(childAt.getMeasuredHeight()); // 高度还是以child为准
//        }
        // TODO xiahangli 去掉这个方法，为300，300像素，customlayout边界，而保留这个方法，customlayout边界为410，410，为我们设置的alyoutparams
        widthDimen = resolveSizeAndState(widthDimen, widthMeasureSpec, 0);
        heightDimen = resolveSizeAndState(heightDimen, heightMeasureSpec, 0);
        Log.e(TAG, "<<<<< onMeasure: afterResolve widthDimen " + widthDimen + " heightDimen " + heightDimen);
        setMeasuredDimension(widthDimen, heightDimen);
//        Log.e(TAG, "onMeasure: width " + widthT);
    }


    public void addItem(View view) {
        views.add(view);
        addView(view, -1);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

//        int childCount = getChildCount();
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
        int childIndex = 0;
        int childCount = getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View childAt = getChildAt(i);
//            // 相对于父容器，也就是CustomLayout
//            childAt.layout(left, t, left + childAt.getMeasuredWidth(), top + childAt.getMeasuredHeight());
//            left += childAt.getMeasuredWidth();
//        }
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                loc[i][j] = 0;
            }
        }
        boolean layoutComplete = false;
        for (int i = 0; i < MAX_ROW; i++) {
            if (layoutComplete) break;
            for (int j = 0; j < MAX_COL; j++) {
                if (loc[i][j] == 1) continue;
                MyView child = (MyView) getChildAt(childIndex);
                int rowSize = child.size[0];
                int colSize = child.size[1];
                int childLeft = child.loc[0];
                int childTop = child.loc[1];
                for (int k = 0; k < rowSize; k++) { // 当前测量的child占有的位置信息
                    for (int m = 0; m < colSize; m++) {
                        loc[childLeft + k][childTop + m] = 1;
                    }
                }
                left = child.loc[1] * minGridSize;
                top = child.loc[0] * minGridSize;
                right = left + child.getMeasuredWidth();
                bottom = top + child.getMeasuredHeight();
                Log.e(TAG, "onLayout: layoutItem " + child
                        + "\n l " + left + " t " + top + " r " + right + " b " + bottom);
                child.layout(left, top, right, bottom);
                childIndex++;
                if (childIndex >= childCount) {
                    layoutComplete = true;
                    break;
                }
            }
        }
    }


}
