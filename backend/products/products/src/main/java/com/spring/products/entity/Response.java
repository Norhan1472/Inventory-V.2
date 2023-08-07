package com.spring.products.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    protected String message;
    protected HttpStatus httpStatus;
    protected String developerMessage;
    protected int statusCode;
    protected LocalDateTime timeStamp;
    protected String reason;
    protected Map<?,?> data;

}
