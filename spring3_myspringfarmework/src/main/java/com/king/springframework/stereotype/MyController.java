package com.king.springframework.stereotype;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-05 11:51
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyController {
    String value() default "";
}
