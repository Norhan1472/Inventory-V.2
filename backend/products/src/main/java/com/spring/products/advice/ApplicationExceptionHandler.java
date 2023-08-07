package com.spring.products.advice;

import com.spring.products.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.FOUND)
    @ExceptionHandler(BrandAlreadyExist.class)
    public Map<String,String>exceptionBrandNames(BrandAlreadyExist exception){
        Map<String,String >result=new HashMap<>();
        result.put("message",exception.getMessage());
        return result;
    }

    @ResponseStatus(HttpStatus.FOUND)
    @ExceptionHandler(CategoryAlreadyExist.class)
    public Map<String,String>exceptionCategoryName(CategoryAlreadyExist exception){
        Map<String,String>result=new HashMap<>();
        result.put("message",exception.getMessage());
        return result;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BrandNotFoundException.class)
    public Map<String,String>exceptionBrandNotExist(BrandNotFoundException exception){
        Map<String,String>result=new HashMap<>();
        result.put("message",exception.getMessage());
        return result;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public Map<String,String>exceptionCategoryNotExist(CategoryNotFoundException exception){
        Map<String,String>result=new HashMap<>();
        result.put("message",exception.getMessage());
        return result;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public Map<String,String>exceptionNotFoundProduct(ProductNotFoundException exception){
        Map<String,String>result = new HashMap<>();
        result.put("message error",exception.getMessage());
        return result;
    }
}
