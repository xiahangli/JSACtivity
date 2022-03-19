package com.demo.plugin;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(value = Dependencies.class)
public @interface DependsOn {
    Class<?> target();

}