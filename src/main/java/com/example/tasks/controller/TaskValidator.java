package com.example.tasks.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TaskValidator {
    public static void validateDescription(String description) {
        if(description == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
