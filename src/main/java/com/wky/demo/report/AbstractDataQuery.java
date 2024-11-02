package com.wky.demo.report;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author wuming
 * @date 2024/7/10/07/10 21:40
 */
public abstract class AbstractDataQuery<T>  implements DataQuery<T> {

    public abstract String bizType();

    @SuppressWarnings("unchecked")
    public Class<T> getGenericClass() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        return (Class<T>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
    }

}
