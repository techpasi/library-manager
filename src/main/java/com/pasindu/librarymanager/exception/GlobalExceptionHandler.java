package com.pasindu.librarymanager.exception;

import com.pasindu.librarymanager.dto.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponseDTO> handleValidationException(ValidationException ex) {
        ApiResponseDTO errorResponse = new ApiResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null, null);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        ApiResponseDTO errorResponse = new ApiResponseDTO(HttpStatus.BAD_REQUEST.value(), errors.toString(), null,null);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponseDTO> handleIllegalStateException(IllegalStateException ex) {
        ApiResponseDTO errorResponse = new ApiResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null,null);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponseDTO> handleNoSuchElementException(NoSuchElementException ex) {
        ApiResponseDTO errorResponse = new ApiResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null,null);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponseDTO> handleRuntimeException(AuthenticationException ex) {
        ApiResponseDTO errorResponse = new ApiResponseDTO(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), null,null);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponseDTO> handleRuntimeException(RuntimeException ex) {
        ApiResponseDTO errorResponse = new ApiResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null,null);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
