package com.wky.demo.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

/**
 * 消息体
 *
 * @author lijiajia
 * @date 2021/6/23 20:07
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventMessage<T> implements ResolvableTypeProvider {

    private EventTypeEnum type;

    private T message;

    private Object source;

    public EventMessage(EventTypeEnum type, T message) {
        this.type = type;
        this.message = message;
    }

    public static <T> EventMessage<T> build(EventTypeEnum type, T message) {
        return new EventMessage<>(type, message);
    }


    public static <T> EventMessage<T> build(EventTypeEnum type, T message, Object source) {
        return new EventMessage<>(type, message, source);
    }

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(message));
    }
}
