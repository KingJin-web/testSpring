package com.king.springframework.stereotype;

import org.springframework.stereotype.Repository;

import java.lang.annotation.*;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-05 14:13
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Repository
public @interface MyQualifier {
    String value() default "";
}
