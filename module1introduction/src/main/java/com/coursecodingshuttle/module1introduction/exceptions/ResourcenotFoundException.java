package com.coursecodingshuttle.module1introduction.exceptions;

import org.springframework.http.HttpStatus;

public class ResourcenotFoundException extends RuntimeException{
    public ResourcenotFoundException(String message) {
        super(message);
    }
}
