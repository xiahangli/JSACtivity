package com.demo.plugin;

import android.content.Context;

/**
 * @author Henry
 * @Date 2022/3/20  1:22 AM
 * @Email 2427417167@qq.com
 */
public interface Plugin {

    default void onCreate(Context pluginContext) {}

    default void onDestroy() {}
 }
