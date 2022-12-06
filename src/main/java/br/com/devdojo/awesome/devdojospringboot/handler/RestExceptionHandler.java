package br.com.devdojo.awesome.devdojospringboot.handler;

import br.com.devdojo.awesome.devdojospringboot.error.ErrorDetails;
import br.com.devdojo.awesome.devdojospringboot.error.ResourceNotFoundDetails;
import br.com.devdojo.awesome.devdojospringboot.error.ResourceNotFoundException;
import br.com.devdojo.awesome.devdojospringboot.error.ValidationErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

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

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  WebRequest request) {
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
    String field = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
    String messageField = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
    ValidationErrorDetails validationErrorDetails = ValidationErrorDetails
            .ValidationErrorDetailsBuilder
            .newBuilder()
            .timestamp(new Date().getTime())
            .status(HttpStatus.BAD_REQUEST.value())
            .title("Resource not found")
            .details(ex.getMessage())
            .developerMessage(ex.getClass().getName())
            .field(field)
            .fieldMessage(messageField)
            .build();
    return new ResponseEntity<>(validationErrorDetails, HttpStatus.BAD_REQUEST);

    }

    //Todos os erros que chamam o handleExceptionInternal vão estar padronizado da forma que defini abaixo
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             @Nullable Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus statusCode,
                                                             WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails
                .ErrorDetailsBuilder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Internal exception")
                .details(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();
        return new ResponseEntity<>(errorDetails, headers, statusCode);
        }
}
