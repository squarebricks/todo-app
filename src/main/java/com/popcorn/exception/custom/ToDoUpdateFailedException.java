package com.popcorn.exception.custom;

import com.popcorn.exception.AppException;

public class ToDoUpdateFailedException extends AppException {
    public ToDoUpdateFailedException(String message) {
        super(message);
    }
}
