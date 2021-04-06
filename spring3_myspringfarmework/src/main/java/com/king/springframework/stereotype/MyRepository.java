package com.king.springframework.stereotype;


import javax.annotation.Resource;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRepository {

    String value() default "";
}
