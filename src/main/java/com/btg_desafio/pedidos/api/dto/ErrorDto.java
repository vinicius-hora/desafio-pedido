package com.btg_desafio.pedidos.api.dto;

import lombok.Data;

@Data
public class ErrorDto {

    private static final long serialVersionUID = 1L;
    private String message;
    private String code;
}
