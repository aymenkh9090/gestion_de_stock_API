package com.gestiondestock.exceptions;

import lombok.Getter;

public class EntityNotFoundException extends   RuntimeException {

    @Getter
    private ErrorsCode errorsCode;

    public EntityNotFoundException(String  msg) {
        super(msg);
    }

    public EntityNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public EntityNotFoundException(String msg, Throwable cause,ErrorsCode errorsCode) {
        super(msg, cause);
        this.errorsCode = errorsCode;
    }

    public EntityNotFoundException(String msg, ErrorsCode errorsCode) {
        super(msg);
        this.errorsCode = errorsCode;
    }











}
