package com.wiredbrain.friends.controller;

import com.wiredbrain.friends.util.ErrorMessage;
import com.wiredbrain.friends.util.FieldErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    ErrorMessage exceptionHandler(ValidationException e){
        return new ErrorMessage("400", e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError ->
                new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        return fieldErrorMessages;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchElementException.class)
    ErrorMessage exceptionHandler(NoSuchElementException e){
        return new ErrorMessage("400", "friend does not exists");
    }
}
