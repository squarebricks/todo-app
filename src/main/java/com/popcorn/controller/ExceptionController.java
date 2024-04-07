package com.popcorn.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exception")
public class ExceptionController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> exception(HttpServletRequest request) throws Exception {
        throw new Exception("a checked-exception is thrown");
    }
    @GetMapping(path = "/runtime", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> runtimeException(HttpServletRequest request) {
        throw new RuntimeException("an un-checked (runtime) is thrown");
    }
}
