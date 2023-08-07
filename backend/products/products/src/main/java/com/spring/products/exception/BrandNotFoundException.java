package com.spring.products.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BrandNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public BrandNotFoundException(String message) {
        super(message);
    }
}
