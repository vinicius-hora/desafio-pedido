package com.desafio.pedidos.api.config;

import com.desafio.pedidos.api.Exception.GenericRuntimeApiException;
import com.desafio.pedidos.api.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class ExceptionHandlerConfig {

    @ExceptionHandler({ GenericRuntimeApiException.class})
    protected ResponseEntity<Object> genericRuntimeApiException(Exception e) {
        String msg = "" + e.getMessage();
        ErrorDto erroDto = new ErrorDto();
        erroDto.setMessage(msg);
        erroDto.setCode(HttpStatus.BAD_REQUEST.value() + "==> " + HttpStatus.BAD_REQUEST.getReasonPhrase());
        log.error("Erro Exception: {} ", e.getMessage());
        return new ResponseEntity<>(erroDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ RuntimeException.class})
    protected ResponseEntity<Object> tokenException(Exception e) {
        String msg = "" + e.getMessage();
        ErrorDto erroDto = new ErrorDto();
        erroDto.setMessage(msg);
        erroDto.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value() + "==> " + HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        log.error("Erro Exception: {} ", e.getMessage());
        return new ResponseEntity<>(erroDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ErrorDto erroDto = new ErrorDto();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        erroDto.setMessage(errors.toString());
        erroDto.setCode(HttpStatus.BAD_REQUEST.value() + "==> " + HttpStatus.BAD_REQUEST.getReasonPhrase());
        log.error("Erro Exception: {} ", ex.getMessage());
        return new ResponseEntity<>(erroDto, HttpStatus.BAD_REQUEST);
    }

}
