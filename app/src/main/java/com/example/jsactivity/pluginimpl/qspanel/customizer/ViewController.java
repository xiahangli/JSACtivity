package com.example.jsactivity.pluginimpl.qspanel.customizer;

import android.view.View;

/**
 * @author Henry
 * @Date 2022/3/20  4:02 AM
 * @Email 2427417167@qq.com
 */
public abstract class ViewController<T extends View> {

    T mView;

    public ViewController(T mView) {
        this.mView = mView;
        init();
    }

    public void init() {
        mView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                onViewAttached();
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                onViewDetached();
            }
        });
    }

    abstract void  onViewAttached();

    abstract void onViewDetached();

}
