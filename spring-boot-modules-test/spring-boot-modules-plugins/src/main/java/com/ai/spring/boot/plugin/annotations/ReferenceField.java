package com.ai.spring.boot.plugin.annotations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/8/21
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceField {
    private Reference reference;
    private Field field;
    private Object bean;
}
