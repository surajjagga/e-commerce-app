package com.example.product_service.handler;

import com.example.product_service.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalProductExceptionHandler{

    @ExceptionHandler({ProductPurchaseException.class, EntityNotFoundException.class})
    public ResponseEntity<String> handleException(Exception e ){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException e){
        HashMap<String,String> errors = new HashMap<String,String>();
        e.getBindingResult().getFieldErrors().forEach((error)->{
            var fieldName = error.getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }

}
