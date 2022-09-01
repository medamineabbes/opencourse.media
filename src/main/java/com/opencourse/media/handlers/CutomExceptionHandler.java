package com.opencourse.media.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.opencourse.media.exceptions.ImageNotFoundException;

import lombok.Data;

@RestControllerAdvice
public class CutomExceptionHandler {
    


    @ExceptionHandler({ImageNotFoundException.class})
    public ResponseEntity<ApiError> handleImageNotFoundException(ImageNotFoundException ex,WebRequest request){
        ApiError error=new ApiError();
        error.setError(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error,error.getStatus());
    }
}



@Data
class ApiError{
    private String error;
    private HttpStatus status;
}
