package com.demo.plugin;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Henry
 * @Date 2022/3/20  1:31 AM
 * @Email 2427417167@qq.com
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Requirements {
    Requires[] value();
}
