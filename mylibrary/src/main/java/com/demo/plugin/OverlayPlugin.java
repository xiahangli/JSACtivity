package com.demo.plugin;

/**
 * @author Henry
 * @Date 2022/3/20  1:39 AM
 * @Email 2427417167@qq.com
 */
@ProvidesInterface(version = 1, action = "com.plugin.demo")
public interface OverlayPlugin extends Plugin {





    interface Callback {
        void onHoldStatusBarOpenChange();
    }
}
