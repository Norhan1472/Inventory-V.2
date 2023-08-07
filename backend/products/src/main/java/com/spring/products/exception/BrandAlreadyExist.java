package com.spring.products.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class BrandAlreadyExist extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public BrandAlreadyExist(String message) {
        super(message);
    }
}
