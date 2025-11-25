package com.siemens.policyholderapi.exceptions;

import com.siemens.policyholderapi.dtos.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(PolicyHolderNotFoundException.class)
    public ResponseEntity<GenericResponse> handlePolicyHolderNotFoundException(PolicyHolderNotFoundException policyHolderNotFoundException) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse(policyHolderNotFoundException.getMessage()));
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GenericResponse> handleRuntimeException(RuntimeException runtimeException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse(runtimeException.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest httpServletRequest) {
        List<FieldValidationError> fieldValidationErrors= methodArgumentNotValidException
                .getBindingResult().getFieldErrors().stream().map(this::mapToFieldValidationError).toList();

        ApiError apiError = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                fieldValidationErrors,
                httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse(apiError));

    }

    private FieldValidationError mapToFieldValidationError(FieldError fieldError) {
        return new FieldValidationError(fieldError.getField(), fieldError.getDefaultMessage(),fieldError.getRejectedValue());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GenericResponse>
    handleConstraintViolationException(ConstraintViolationException constraintViolationException,HttpServletRequest httpServletRequest) {
        List<FieldValidationError> fieldValidationErrors= constraintViolationException
                .getConstraintName().lines().map(fieldName -> new FieldValidationError(fieldName, constraintViolationException.getMessage(),null)).toList();
        ApiError apiError = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Constraint Violation",
                fieldValidationErrors,
                httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse(apiError));
    }
}
