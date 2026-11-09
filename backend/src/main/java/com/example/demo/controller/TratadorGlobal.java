package com.example.demo.controller;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> tratarResponseStatus(
            ResponseStatusException exception) {

        String mensagem = exception.getReason() != null
                ? exception.getReason()
                : "Erro ao processar a requisição.";

        return ResponseEntity
                .status(exception.getStatusCode())
                .body(Map.of("mensagem", mensagem));
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarValidacao(
            MethodArgumentNotValidException exception) {

        List<String> erros = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .toList();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("Dados Invalidos", "mensagem", "erros", erros));
    }


}
