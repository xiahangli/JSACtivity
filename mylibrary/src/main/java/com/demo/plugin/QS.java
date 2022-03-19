package com.demo.plugin;

/**
 * @author Henry
 * @Date 2022/3/20  1:25 AM
 * @Email 2427417167@qq.com
 */
public interface QS extends FragmentBase {

    String ACTION = "com.example.jsactivity.PLUGIN_QS";
    String TAG = "QS";

    void setPanelView(HeightListener notificationPanelView);

    @ProvidesInterface(version = HeightListener.VERSION)
    interface HeightListener {
        int VERSION = 1;
        void onQsHeightChanged();
    }
}
