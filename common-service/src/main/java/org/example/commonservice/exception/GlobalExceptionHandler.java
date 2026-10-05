package org.example.commonservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.commonservice.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
         ErrorResponse response=new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Somethings went wrong", LocalDateTime.now().toString());
         log.error("Exception: ", ex);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserException(Exception ex){
        ErrorResponse response=new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(),LocalDateTime.now().toString());
        log.error("NotFoundException: ", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public  ResponseEntity<ErrorResponse> handleBadRequestException(Exception ex){
        ErrorResponse response=new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now().toString());
        log.error("BadRequestException: ", ex);
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

    @ExceptionHandler(UnAuthorizedException.class)
    public  ResponseEntity<ErrorResponse> handleUnAuthorizedException(Exception ex){
        ErrorResponse response= new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(),LocalDateTime.now().toString());
        log.error("UnAuthorizedException: ", ex);
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }


    // forbidden exception123
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbiddenException(Exception ex){
        ErrorResponse response= new ErrorResponse(HttpStatus.FORBIDDEN.value(), ex.getMessage(),LocalDateTime.now().toString());
        log.error("ForbiddenException: ", ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex){
        ErrorResponse response = new ErrorResponse(HttpStatus.FORBIDDEN.value(), "ACCESS_DENIED", LocalDateTime.now().toString());
        log.error("AccessDeniedException: ", ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }



}
