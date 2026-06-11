package com.fullslab.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fullslab.utils.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler { 
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(
            new ApiResponse<>("Error de validación: " + ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage(), false, null)
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(404).body(
            new ApiResponse<>(ex.getMessage(), false, null)
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneralException(Exception ex) {
        ex.printStackTrace(); 
        return ResponseEntity.status(500).body(
            new ApiResponse<>("Ocurrió un error inesperado, intenta más tarde.", false, null)
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleConflict(DataIntegrityViolationException ex) {
        return ResponseEntity.status(409).body(
            new ApiResponse<>("El recurso ya existe o viola una restricción de integridad.", false, null)
        );
    }
}
