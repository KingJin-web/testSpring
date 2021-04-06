package com.king.springframework.stereotype;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface MyComponentScan {
    String[] value() default {};

    String[] basePackages();
}
