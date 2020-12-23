package com.ai.spring.boot.plugin.annotations;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/8/21
 * @Version 1.0
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Reference {
    String value() default "";
    ReferenceType type() default ReferenceType.JVM;
}
