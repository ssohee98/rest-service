package com.example.restservice.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

    //예외 발생 클래스
    public UserNotFoundException(String message) {
        super(message);
    }
}
