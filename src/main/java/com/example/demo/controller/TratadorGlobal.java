package com.example.demo.controller;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class TratadorGlobal extends RuntimeException {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> tratarEntidadeNaoEncontrada(
            EntityNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("mensagem", exception.getMessage()));
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Map<String, String>> tratarEmailJaVinculado(EntityExistsException exception){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("mensagem",exception.getMessage()));
    }





}
