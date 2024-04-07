package com.popcorn.exception.advice;

import com.popcorn.exception.custom.ToDoNotFoundException;
import com.popcorn.exception.custom.ToDoUpdateFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ToDoRestExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleRootRuntimeException(Exception exception, WebRequest request) {
        log.info(exception.getMessage());
        return Map.of(
                        "timestamp", LocalDateTime.now().toString(),
                        "message", exception.getMessage()
                );
    }
    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleRootRuntimeException(RuntimeException exception, WebRequest request) {
        log.info(exception.getMessage());
        return Map.of(
                "timestamp", LocalDateTime.now().toString(),
                "message", exception.getMessage()
        );
    }
    @ExceptionHandler(value = ToDoNotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handleToDoNotFoundException(ToDoNotFoundException exception) {
        log.warn(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "timestamp", LocalDateTime.now().toString(),
                        "message", exception.getMessage()
                ));
    }

    @ExceptionHandler(value = ToDoUpdateFailedException.class)
    @ResponseBody
    public ResponseEntity<?> handleToDoUpdateFailedException(ToDoUpdateFailedException exception) {
        log.warn(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "timestamp", LocalDateTime.now().toString(),
                        "message", exception.getMessage()
                ));
    }
}
