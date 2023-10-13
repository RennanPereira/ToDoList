package br.com.rennanpereira.todolist.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice//tratamento de exceptions
public class ExceptionHandlerController {

    //O campo title deve conter no máximo 50 caracteres
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> HandleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMostSpecificCause().getMessage());
    }
    
}
