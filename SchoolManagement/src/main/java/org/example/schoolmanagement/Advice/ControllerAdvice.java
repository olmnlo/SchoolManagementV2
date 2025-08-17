package org.example.schoolmanagement.Advice;

import org.example.schoolmanagement.Api.ApiException;
import org.example.schoolmanagement.Api.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ApiResponse> ApiException(ApiException apiException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(apiException.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> MethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(methodArgumentNotValidException.getFieldError().getDefaultMessage()));
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> DataIntegrityViolationException (DataIntegrityViolationException dataIntegrityViolationException){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("duplicated element email"));
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse> HttpRequestMethodNotSupportedException (HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException){
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ApiResponse(httpRequestMethodNotSupportedException.getMessage()));
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity<ApiResponse> NoResourceFoundException (NoResourceFoundException noResourceFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(noResourceFoundException.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse> MethodArgumentTypeMismatchException (MethodArgumentTypeMismatchException methodArgumentTypeMismatchException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(methodArgumentTypeMismatchException.getMessage()));
    }
}
