package com.wolfpack.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.net.URI;

@RestControllerAdvice
public class ResponseExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ErrorResponse handleAllExceptions(Exception ex, WebRequest request) {
        //ex.printStackTrace(); // O usa un logger
        // Opcional: puedes obtener la causa raíz y registrarla también
        //Throwable rootCause = ex.getCause() != null ? ex.getCause() : ex;
        //System.out.println("Root cause: " + rootCause.getClass().getName());

        return ErrorResponse.builder(ex, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage())
                .type(URI.create(request.getContextPath()))
                .build();
    }


    @ExceptionHandler(ModelNotFoundException.class)
    public ErrorResponse handleModelNotFoundException (ModelNotFoundException ex, WebRequest request){
        return ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage())
                .title("Model not found")
                .type(URI.create(request.getContextPath()))
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        return ErrorResponse.builder(ex, HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage())
                .title("Validation Failed")
                .type(URI.create(request.getContextPath()))
                .build();
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        return ErrorResponse.builder(ex, HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage())
                .title("Method Not Allowed")
                .type(URI.create(request.getContextPath()))
                .build();
    }


    @ExceptionHandler(NoResourceFoundException.class)
    public ErrorResponse handleNoResourceFoundException(NoResourceFoundException ex, WebRequest request) {
        return ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage())
                .title("Resource Not Found")
                .type(URI.create(request.getContextPath()))
                .build();
    }


    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ErrorResponse handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex, WebRequest request) {
        return ErrorResponse.builder(ex, HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage())
                .title("Unsupported Media Type")
                .type(URI.create(request.getContextPath()))
                .build();
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ErrorResponse handleMissingServletRequestPartException(MissingServletRequestPartException ex, WebRequest request) {
        return ErrorResponse.builder(ex, HttpStatus.BAD_REQUEST, ex.getMessage())
                .title("Missing Request Part")
                .type(URI.create(request.getContextPath()))
                .build();
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ErrorResponse handleMissingServletRequestPartException(InsufficientStockException ex, WebRequest request) {
        return ErrorResponse.builder(ex, HttpStatus.CONFLICT, ex.getMessage())
                .title("Insufficient Stock")
                .type(URI.create(request.getContextPath()))
                .build();
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        return ErrorResponse.builder(ex, HttpStatus.BAD_REQUEST, ex.getMessage())
                .title("Insufficient Stock")
                .type(URI.create(request.getContextPath()))
                .build();
    }

    @ExceptionHandler(InvalidAppointmentDateException.class)
    public ErrorResponse handleInvalidAppointmentDateException(InvalidAppointmentDateException ex, WebRequest request) {
        return ErrorResponse.builder(ex, HttpStatus.BAD_REQUEST, ex.getMessage())
                .title("Date not available")
                .type(URI.create(request.getContextPath()))
                .build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String detailMessage = ex.getMostSpecificCause().getMessage();
        HttpStatus status;
        String title;
        if (detailMessage.contains("could not execute statement")) {
            status = HttpStatus.CONFLICT;
            title = "Database Conflict";
        } else if (detailMessage.contains("cannot be null")) {
            status = HttpStatus.BAD_REQUEST;
            title = "Missing Required Field";
        } else if (detailMessage.contains("Duplicate entry")) {
            status = HttpStatus.CONFLICT;
            title = "Duplicate Entry";
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            title = "Data Integrity Violation";
        }

        // Construye la respuesta de error personalizada
        return  ErrorResponse.builder(ex, status, detailMessage)
                .title(title)
                .type(URI.create(request.getContextPath()))
                .build();

    }

}
