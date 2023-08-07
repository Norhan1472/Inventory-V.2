package com.spring.products.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class CategoryAlreadyExist extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CategoryAlreadyExist(String message) {
        super(message);
    }
}
