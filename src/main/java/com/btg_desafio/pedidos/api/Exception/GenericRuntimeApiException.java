package com.btg_desafio.pedidos.api.Exception;

public class GenericRuntimeApiException extends RuntimeException{
    public GenericRuntimeApiException(GenericRuntimeApiException message) {
        super(message);
    }
}
