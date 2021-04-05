package com.king.springframework.stereotype;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface MyComponentScan {
    String[] value() default {};

    String[] basePackages();
}
