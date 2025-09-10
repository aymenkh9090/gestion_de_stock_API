package com.gestiondestock.exceptions;

import lombok.Getter;

import java.util.List;

public class InvalidEntityException extends RuntimeException {

    @Getter
    private ErrorsCode errorsCode;
    @Getter
    private List<String> errors;

    public InvalidEntityException(String msg) {
        super(msg);
    }
    public InvalidEntityException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public InvalidEntityException(String msg, ErrorsCode errorsCode) {
        super(msg);
        this.errorsCode = errorsCode;
    }
    public InvalidEntityException(String msg, ErrorsCode errorsCode, Throwable cause) {
        super(msg, cause);
        this.errorsCode = errorsCode;
    }
    public InvalidEntityException(String msg,ErrorsCode errorsCode,List<String> errors) {
        super(msg);
        this.errorsCode = errorsCode;
        this.errors = errors;
    }





}
