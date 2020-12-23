package com.ai.spring.sofa.test.mogo;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/6/20
 * @Version 1.0
 **/

public class ClassUtil {
    private ClassUtil() {
    }
    private static boolean isPrimitives(Class<?> cls) {
        if (cls.isArray()) {
            return isPrimitive(cls.getComponentType());
        }
        return isPrimitive(cls);
    }
    private static boolean isPrimitive(Class<?> cls) {
        return cls.isPrimitive() || cls == String.class || cls == Boolean.class || cls == Character.class
                || Number.class.isAssignableFrom(cls) || Date.class.isAssignableFrom(cls);
    }

    public static Map<String, String> beanToMap(Object obj) throws Exception{
        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        String value;
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            value = getFieldStringValue(field, obj);
            if (!StringUtils.isEmpty(value)) {
                result.put(field.getName(), value);
            }
        }
        return result;
    }

    private static String getFieldStringValue(Field field, Object obj) throws Exception{
        field.setAccessible(true);
        Object o = field.get(obj);
        if (o != null && ClassUtil.isPrimitives(o.getClass())) {
            if (o.getClass().isArray()) {
                return JSON.toJSONString(o);
            } else {
                return o.toString();
            }
        }
        return null;
    }
}
