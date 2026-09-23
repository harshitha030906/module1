package com.coursecodingshuttle.module1introduction.advices;

import com.coursecodingshuttle.module1introduction.exceptions.ResourcenotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourcenotFoundException.class)
    public ResponseEntity<ApiError> handleNoSuchElementException(ResourcenotFoundException e){
        ApiError apiError = ApiError.builder()
                            .message(e.getMessage())
                            .status(HttpStatus.NOT_FOUND)
                            .build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e){
        ApiError apiError = ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(e.getMessage()).build();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleInputValidationException(MethodArgumentNotValidException e){
        List<String> errors = e.getBindingResult()
                                .getAllErrors()
                                .stream()
                                .map(error -> error.getDefaultMessage())
                                .collect(Collectors.toList());
        ApiError apiError = ApiError.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .message(errors.toString())
                            .build();

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
