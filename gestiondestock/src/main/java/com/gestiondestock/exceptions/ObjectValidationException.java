package com.gestiondestock.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@RequiredArgsConstructor
public class ObjectValidationException extends RuntimeException{

    @Getter
    private final Set<String> violations;
    @Getter
    private final String violationSource;

}
