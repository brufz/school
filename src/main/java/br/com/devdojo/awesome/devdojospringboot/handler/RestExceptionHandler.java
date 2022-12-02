package br.com.devdojo.awesome.devdojospringboot.handler;

import br.com.devdojo.awesome.devdojospringboot.error.ResourceNotFoundDetails;
import br.com.devdojo.awesome.devdojospringboot.error.ResourceNotFoundException;
import br.com.devdojo.awesome.devdojospringboot.error.ValidationErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe) {
        ResourceNotFoundDetails resourceNotFound = ResourceNotFoundDetails
                .ResourceNotFoundDetailsBuilder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not found")
                .details(rnfe.getMessage())
                .developerMessage(rnfe.getClass().getName())
                .build();
        return new ResponseEntity<>(resourceNotFound, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException( MethodArgumentNotValidException
                                                                                manvException){
        List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();
        String field = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String messageError = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
        ValidationErrorDetails validationErrorDetails = ValidationErrorDetails
                .ValidationErrorDetailsBuilder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Validation error occurred")
                .details("Validation error occurred")
                .developerMessage(manvException.getClass().getName())
                .field(field)
                .fieldMessage(messageError)
                .build();

        return new ResponseEntity<>(validationErrorDetails,HttpStatus.BAD_REQUEST);
    }
}