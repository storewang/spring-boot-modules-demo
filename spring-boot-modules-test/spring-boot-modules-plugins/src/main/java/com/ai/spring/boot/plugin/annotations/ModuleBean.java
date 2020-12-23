package com.ai.spring.boot.plugin.annotations;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/8/20
 * @Version 1.0
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModuleBean {
    String value() default "";
    String parent() default "";
}
