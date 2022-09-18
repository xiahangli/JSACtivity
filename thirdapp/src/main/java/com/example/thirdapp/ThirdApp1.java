package com.example.thirdapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import static com.example.thirdapp.CustomLayout.colSpacing;
import static com.example.thirdapp.CustomLayout.rowSpacing;
import static com.example.thirdapp.CustomLayout.sMinGridSize;

/**
 * @author Henry
 * @Date 2022/9/18  11:22 PM
 * @Email 2427417167@qq.com
 */
public class ThirdApp1 extends Activity {
    CustomLayout customLayout;
    private boolean isCurrentOldStyle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_app);
        customLayout = findViewById(R.id.ll);
    }


    public void toggleStyle(View view) {
        if (isCurrentOldStyle) {
            newStyle();
        } else {
            oldStyle();
        }
        isCurrentOldStyle = !isCurrentOldStyle;
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 400-10-10-10=370/3=92.5            1
        // 92.5*2+10=185+10=195               2
        // 92.5*3+20=277.5+20=297.5           3

        newStyle();

    }

    private void newStyle() {
        customLayout.removeAllViews();
        MyView myView11 = new MyView(this);
        myView11.loc[0] = 0;
        myView11.loc[1] = 0;
        myView11.size[0] = 1;
        myView11.size[1] = 2;
        myView11.viewHeight = sMinGridSize;
        myView11.viewWidth = (myView11.size[1] - 1) * colSpacing + sMinGridSize * myView11.size[1];
        myView11.setBackgroundColor(Color.RED);

        myView11.setLayoutParams(new ViewGroup.LayoutParams((int) myView11.viewWidth, (int) myView11.viewHeight));
        customLayout.addView(myView11);


        MyView myView22 = new MyView(this);
        myView22.loc[0] = 0;
        myView22.loc[1] = 2;
        myView22.size[0] = 2;
        myView22.size[1] = 2;
        myView22.viewWidth = myView22.size[1] * sMinGridSize + (myView22.size[1] - 1) * rowSpacing;
        myView22.viewHeight = myView22.size[0] * sMinGridSize + (myView22.size[0] - 1) * colSpacing;

        myView22.setBackgroundColor(Color.YELLOW);
        myView22.setLayoutParams(new ViewGroup.LayoutParams((int) myView22.viewWidth, (int) myView22.viewHeight));
        customLayout.addView(myView22);

        MyView myView3 = new MyView(this);
        myView3.loc[0] = 1;
        myView3.loc[1] = 0;
        myView3.size[0] = 1;
        myView3.size[1] = 1;
        myView3.viewHeight = sMinGridSize;
        myView3.viewWidth = sMinGridSize;
        myView3.setBackgroundColor(Color.MAGENTA);
        myView3.setLayoutParams(new ViewGroup.LayoutParams((int) myView3.viewWidth, (int) myView3.viewHeight));
        customLayout.addView(myView3);

        MyView myView4 = new MyView(this);
        myView4.loc[0] = 1;
        myView4.loc[1] = 1;
        myView4.size[0] = 1;
        myView4.size[1] = 1;
        myView4.viewWidth = sMinGridSize;
        myView4.viewHeight = sMinGridSize;
        myView4.setBackgroundColor(Color.CYAN);
        myView4.setLayoutParams(new ViewGroup.LayoutParams((int) myView4.viewWidth, (int) myView4.viewHeight));
        customLayout.addView(myView4);

        MyView myView5 = new MyView(this);
        myView5.loc[0] = 2;
        myView5.loc[1] = 0;
        myView5.size[0] = 1;
        myView5.size[1] = 3;
        myView5.viewWidth = sMinGridSize * myView5.size[1] + (myView5.size[1] - 1) * colSpacing;
        myView5.viewHeight = sMinGridSize;
        myView5.setBackgroundColor(Color.GREEN);
        myView5.setLayoutParams(new ViewGroup.LayoutParams((int) myView5.viewWidth, (int) myView5.viewHeight));
        customLayout.addView(myView5);

        MyView myView6 = new MyView(this);
        myView6.loc[0] = 2;// 行
        myView6.loc[1] = 3;// 列
        myView6.size[0] = 2;//行高
        myView6.size[1] = 1;//列宽
        myView6.viewWidth = sMinGridSize;
        myView6.viewHeight = (myView6.size[0] - 1) * colSpacing + myView6.size[0] * sMinGridSize;
        myView6.setBackgroundColor(Color.BLUE);
        myView6.setLayoutParams(new ViewGroup.LayoutParams((int) myView6.viewWidth, (int) myView6.viewHeight));
        customLayout.addView(myView6);

        MyView myView7 = new MyView(this);
        myView7.loc[0] = 3;// 行
        myView7.loc[1] = 0;// 列
        myView7.size[0] = 1;//行高
        myView7.size[1] = 3;//列宽
        myView7.viewWidth = myView7.size[1] * sMinGridSize + (myView7.size[1] - 1) * colSpacing;
        myView7.viewHeight = sMinGridSize;
        myView7.setBackgroundColor(Color.BLACK);
        myView7.setLayoutParams(new ViewGroup.LayoutParams((int) myView7.viewWidth, (int) myView7.viewHeight));
        customLayout.addView(myView7);
    }

    private void oldStyle() {
        customLayout.removeAllViews();
        MyView myView1 = new MyView(this);
        myView1.loc[0] = 0;
        myView1.loc[1] = 0;
        myView1.size[0] = 1;
        myView1.size[1] = 2;
        myView1.viewWidth = myView1.size[1] * sMinGridSize + (myView1.size[1] - 1) * colSpacing;
        myView1.viewHeight = myView1.size[0] * sMinGridSize + (myView1.size[0] - 1) * rowSpacing;
        myView1.setBackgroundColor(Color.RED);

        myView1.setLayoutParams(new ViewGroup.LayoutParams((int) myView1.viewWidth, (int) myView1.viewHeight));
        customLayout.addView(myView1);


        MyView myView2 = new MyView(this);
        myView2.loc[0] = 0;
        myView2.loc[1] = 2;
        myView2.size[0] = 1;
        myView2.size[1] = 1;
        myView2.viewWidth = myView2.size[1] * sMinGridSize + (myView2.size[1] - 1) * rowSpacing;
        myView2.viewHeight = myView2.size[0] * sMinGridSize + (myView2.size[0] - 1) * colSpacing;

        myView2.setBackgroundColor(Color.YELLOW);
        myView2.setLayoutParams(new ViewGroup.LayoutParams((int) myView2.viewWidth, (int) myView2.viewHeight));
        customLayout.addView(myView2);

        MyView myView33 = new MyView(this);
        myView33.loc[0] = 0;
        myView33.loc[1] = 3;
        myView33.size[0] = 1;
        myView33.size[1] = 1;
        myView33.viewWidth = (myView33.size[1] - 1) * colSpacing + sMinGridSize * myView33.size[1];
        myView33.viewHeight = myView33.size[0] * sMinGridSize + (myView33.size[0] - 1) * colSpacing;
        myView33.setBackgroundColor(Color.MAGENTA);
        myView33.setLayoutParams(new ViewGroup.LayoutParams((int) myView33.viewWidth, (int) myView33.viewHeight));
        customLayout.addView(myView33);

        MyView myView44 = new MyView(this);
        myView44.loc[0] = 1;
        myView44.loc[1] = 0;
        myView44.size[0] = 1;
        myView44.size[1] = 3;
        myView44.viewWidth = (myView44.size[1] - 1) * colSpacing + sMinGridSize * myView44.size[1];
        myView44.viewHeight = myView44.size[0] * sMinGridSize + (myView44.size[0] - 1) * colSpacing;
        myView44.setBackgroundColor(Color.CYAN);
        myView44.setLayoutParams(new ViewGroup.LayoutParams((int) myView44.viewWidth, (int) myView44.viewHeight));
        customLayout.addView(myView44);

        MyView myView55 = new MyView(this);
        myView55.loc[0] = 1; //第几行
        myView55.loc[1] = 3; //
        myView55.size[0] = 2;
        myView55.size[1] = 1;
        myView55.viewWidth = (myView55.size[1] - 1) * colSpacing + sMinGridSize * myView55.size[1];
        myView55.viewHeight = myView55.size[0] * sMinGridSize + (myView55.size[0] - 1) * colSpacing;

        myView55.setBackgroundColor(Color.GREEN);
        myView55.setLayoutParams(new ViewGroup.LayoutParams((int) myView55.viewWidth, (int) myView55.viewHeight));
        customLayout.addView(myView55);

        MyView myView66 = new MyView(this);
        myView66.loc[0] = 2;// 行
        myView66.loc[1] = 0;// 列
        myView66.size[0] = 1;//行高
        myView66.size[1] = 3;//列宽
        myView66.viewWidth = (myView66.size[1] - 1) * colSpacing + sMinGridSize * myView66.size[1];
        myView66.viewHeight = myView66.size[0] * sMinGridSize + (myView66.size[0] - 1) * colSpacing;
        myView66.setBackgroundColor(Color.BLUE);
        myView66.setLayoutParams(new ViewGroup.LayoutParams((int) myView66.viewWidth, (int) myView66.viewHeight));
        customLayout.addView(myView66);
    }
}
