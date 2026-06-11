package br.com.hitbox.infra.exception;

import lombok.Getter;

public class HitboxBusinessException extends RuntimeException {


    @Getter
    private Object entity;

    @Getter
    private Class<?> type;

    public HitboxBusinessException(String message, Object entity, Class<?> type) {
        super(message);
        this.entity = entity;
        this.type = type;
    }
}
