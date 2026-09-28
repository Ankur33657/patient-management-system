package org.example.commonservice.exception;

import org.example.commonservice.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
         ErrorResponse response=new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Somethings went wrong", LocalDateTime.now().toString());

         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserException(Exception ex){
        ErrorResponse response=new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(),LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public  ResponseEntity<ErrorResponse> handleBadRequestException(Exception ex){
        ErrorResponse response=new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now().toString());
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

    @ExceptionHandler(UnAuthorizedException.class)
    public  ResponseEntity<ErrorResponse> handleUnAuthorizedException(Exception ex){
        ErrorResponse response= new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(),LocalDateTime.now().toString());
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    // forbidden exception
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbiddenException(Exception ex){
        ErrorResponse response= new ErrorResponse(HttpStatus.FORBIDDEN.value(), ex.getMessage(),LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(response);
    }



}
