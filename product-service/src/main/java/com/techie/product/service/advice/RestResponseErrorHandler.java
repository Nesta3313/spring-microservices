package com.techie.product.service.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorMessage> errorHandler(ProductServiceCustomException exception) {
        return new ResponseEntity<>(new ErrorMessage().builder()
                .errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build(), HttpStatus.NOT_FOUND);

    }
}
