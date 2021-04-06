package com.king.springframework.stereotype;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-05 11:36
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyComponent {
}
