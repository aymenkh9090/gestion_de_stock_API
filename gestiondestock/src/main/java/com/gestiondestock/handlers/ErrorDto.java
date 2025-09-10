package com.gestiondestock.handlers;

import com.gestiondestock.exceptions.ErrorsCode;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @Builder @NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {
    private Integer httpCode;
    private ErrorsCode Code;
    private String message;
    private List<String> errors = new ArrayList<>();

}
