package com.github.pablowinck.muralisdesafiospringboot.core.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DomainException extends RuntimeException {

    private final String code;

    private final HttpStatus status;

    public DomainException(String code, String message, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public static DomainException EX01_NotFound(String termo) {
        return new DomainException(
                "EX-01",
                "Recurso não encontrado para o termo '" + termo + "'",
                HttpStatus.NOT_FOUND
        );
    }

}
