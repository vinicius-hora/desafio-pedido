package com.desafio.pedidos.api.Exception;

public class GenericRuntimeApiException extends RuntimeException{
    public GenericRuntimeApiException(String message) {
        super(message);
    }
}
