package com.gestiondestock.validators;

import com.gestiondestock.exceptions.ObjectValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectValidator<T> {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public void validate(T objectToValidate){
        if (objectToValidate == null) {
            throw new IllegalArgumentException("objectToValidate must not be null");
        }
          Set<ConstraintViolation<T>> violations =  validator.validate(objectToValidate);
          Set<String> errorMessages = null;
          if(!violations.isEmpty()){
              errorMessages = violations.stream()
                      .map(ConstraintViolation::getMessage)
                      .collect(Collectors.toSet());
              throw new ObjectValidationException(errorMessages,objectToValidate.getClass().getName());
          }
    }






}
