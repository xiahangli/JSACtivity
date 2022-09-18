package com.example.thirdapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Henry
 * @Date 2022/9/18  11:22 PM
 * @Email 2427417167@qq.com
 */
public  class ThirdApp1 extends Activity {
    CustomLayout customLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_app);
         customLayout=findViewById(R.id.ll);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyView myView1 = new MyView(this);
        myView1.viewWidth = 200;
        myView1.viewHeight = 100;
        myView1.loc[0] = 0;
        myView1.loc[1] = 0;
        myView1.size[0] = 1;
        myView1.size[1] = 2;
        myView1.setBackgroundColor(Color.RED);

        myView1.setLayoutParams(new ViewGroup.LayoutParams(myView1.viewWidth,myView1.viewHeight));
        customLayout.addView(myView1);


        MyView myView2 = new MyView(this);
        myView2.viewWidth = 200;
        myView2.viewHeight = 200;
        myView2.loc[0]=0;
        myView2.loc[1]=2;
        myView2.size[0] = 2;
        myView2.size[1] = 2;

        myView2.setBackgroundColor(Color.YELLOW);
        myView2.setLayoutParams(new ViewGroup.LayoutParams(myView2.viewWidth,myView2.viewHeight));
        customLayout.addView(myView2);

        MyView myView3 = new MyView(this);
        myView3.viewWidth = 100;
        myView3.viewHeight = 100;
        myView3.loc[0]=1;
        myView3.loc[1]=0;
        myView3.size[0] = 1;
        myView3.size[1] = 1;
        myView3.setBackgroundColor(Color.MAGENTA);
        myView3.setLayoutParams(new ViewGroup.LayoutParams(myView3.viewWidth,myView3.viewHeight));
        customLayout.addView(myView3);

        MyView myView4 = new MyView(this);
        myView4.viewWidth = 100;
        myView4.viewHeight = 100;
        myView4.loc[0]=1;
        myView4.loc[1]=1;
        myView4.size[0] = 1;
        myView4.size[1] = 1;
        myView4.setBackgroundColor(Color.CYAN);
        myView4.setLayoutParams(new ViewGroup.LayoutParams(myView4.viewWidth,myView4.viewHeight));
        customLayout.addView(myView4);

        MyView myView5 = new MyView(this);
        myView5.viewWidth = 300;
        myView5.viewHeight = 100;
        myView5.loc[0]=2;
        myView5.loc[1]=0;
        myView5.size[0] = 1;
        myView5.size[1] = 3;
        myView5.setBackgroundColor(Color.GREEN);
        myView5.setLayoutParams(new ViewGroup.LayoutParams(myView5.viewWidth,myView5.viewHeight));
        customLayout.addView(myView5);

        MyView myView6 = new MyView(this);
        myView6.viewWidth = 100;
        myView6.viewHeight = 300;
        myView6.loc[0]=2;// 行
        myView6.loc[1]=3;// 列
        myView6.size[0] = 2;//行高
        myView6.size[1] = 1;//列宽
        myView6.setBackgroundColor(Color.BLUE);
        myView6.setLayoutParams(new ViewGroup.LayoutParams(myView6.viewWidth,myView6.viewHeight));
        customLayout.addView(myView6);

        MyView myView7 = new MyView(this);
        myView7.viewWidth = 300;
        myView7.viewHeight = 100;
        myView7.loc[0]=3;// 行
        myView7.loc[1]=0;// 列
        myView7.size[0] = 1;//行高
        myView7.size[1] = 3;//列宽
        myView7.setBackgroundColor(Color.BLACK);
        myView7.setLayoutParams(new ViewGroup.LayoutParams(myView7.viewWidth,myView7.viewHeight));
        customLayout.addView(myView7);

    }
}
