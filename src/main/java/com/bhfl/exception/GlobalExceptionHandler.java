package com.bhfl.exception;

import com.bhfl.dto.BfhlResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            IllegalArgumentException.class,
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<BfhlResponse> handleBadRequest(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse(resolveMessage(exception)));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BfhlResponse> handleUnexpected(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse("Unexpected error occurred"));
    }

    private static BfhlResponse errorResponse(String message) {
        return BfhlResponse.builder()
                .success(false)
                .message(message)
                .build();
    }

    private static String resolveMessage(Exception exception) {
        if (exception instanceof MethodArgumentNotValidException validationException
                && validationException.getBindingResult().hasFieldErrors()) {
            return validationException.getBindingResult().getFieldErrors().getFirst().getDefaultMessage();
        }
        if (exception instanceof HttpMessageNotReadableException) {
            return "Invalid request payload";
        }
        return exception.getMessage() == null ? "Invalid request" : exception.getMessage();
    }
}
