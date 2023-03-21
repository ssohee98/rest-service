package com.example.restservice.exception;

import com.example.restservice.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

//에러를 핸들링하기 위해 스프링부트에서 제공하는 클래스
//모든 컨트롤러가 실행될때 @ControllerAdvice 예외를 전담해서 처리
@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    //default: 모든 예외처리를 처리해주는 메서드
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR); //모든에러가 일어났을때
    }

    //UserNotFoundException.class => handleUserNotFoundException()

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND); //404발생
    }

    @Override
    //어떤 예외발생시 실행이 아니라(클래스따라) validation 조건 불만족시 발생하는 에러
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), "Validation Failed", ex.getBindingResult().toString()); //에러메시지 출력되도록 toString()
        
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST); //잘못된 요청 에러
        
    }
}
