package com.popcorn.exception.custom;

import com.popcorn.exception.AppException;

public class ToDoNotFoundException extends AppException {
    public ToDoNotFoundException(String message) {
        super(message);
    }
}
