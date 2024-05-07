package br.com.gc.houseofbook.exceptions.handler;

import br.com.gc.houseofbook.exceptions.ResourceAlreadyExistsException;
import br.com.gc.houseofbook.exceptions.ResourceNotFoundException;
import br.com.gc.houseofbook.exceptions.error.CustomError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> handleResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {

        CustomError customError = new CustomError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Resource not found",
                e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customError);

    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<CustomError> handleResourceAlreadyExistsException(ResourceAlreadyExistsException e, HttpServletRequest request) {

        CustomError customError = new CustomError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Resource already exists",
                e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customError);

    }


}
