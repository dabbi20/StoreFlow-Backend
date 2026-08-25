package com.storeflow.controller;


import com.storeflow.dto.ErrorDto;
import com.storeflow.exception.InsufficientStockException;
import com.storeflow.exception.ProductAlreadyExistsException;
import com.storeflow.exception.ProductNotFoundException;
import com.storeflow.exception.SaleNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = ProductAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> globalExceptionHandler(ProductAlreadyExistsException exception, jakarta.servlet.http.HttpServletRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ErrorDto errorDto = ErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(errorDto, status);
    }


    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFound(ProductNotFoundException exception, jakarta.servlet.http.HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorDto errorDto = ErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(errorDto, status);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public  ResponseEntity<ErrorDto>argumentInvalid(MethodArgumentNotValidException exception, HttpServletRequest request){

        Map<String,String>erroresMappeador = new HashMap<>();
        List<FieldError>listaErrores = exception.getBindingResult().getFieldErrors();

        for (FieldError error : listaErrores){
            String campo = error.getField();
             String  mensaje = error.getDefaultMessage();

             erroresMappeador.put(campo,mensaje);

        }

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDto errorDto = ErrorDto.builder()
                .timestamp(LocalDateTime.now())

                .errors(erroresMappeador)
                .status(status.value())
                .error(status.getReasonPhrase())
                .message("Error de validación")
                .path(request.getRequestURI())
                .build();

return new ResponseEntity<>(errorDto,status);
    }


    @ExceptionHandler(value = SaleNotFoundException.class)
    public ResponseEntity<ErrorDto>saleNotFound(SaleNotFoundException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDto errorDto = ErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();


        return new ResponseEntity<>(errorDto,status);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorDto> insufficientStock(
            InsufficientStockException exception,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;

        ErrorDto errorDto = ErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();


        return new ResponseEntity<>(errorDto,status);
    }
    }


