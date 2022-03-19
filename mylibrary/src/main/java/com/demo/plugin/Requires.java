package com.demo.plugin;

import java.lang.annotation.Repeatable;

@Repeatable(Requirements.class)
public @interface Requires {
    Class<?> target();
    int version();
}