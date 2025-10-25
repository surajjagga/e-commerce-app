package com.example.orderservice.handler;

import com.example.orderservice.order.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalProductExceptionHandler{

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<String> handleException(BusinessException e ){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMsg());
    }

    @ExceptionHandler (EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityException(Exception e ){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException e){
        HashMap<String,String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach((error)->{
            var fieldName = error.getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }

}
