package com.desafio.pedidos.api.dto;

import lombok.Data;

@Data
public class PedidoApiResponseDto {
    String codigoPedido;
    String codigoCliente;
    Integer quantidadeItens;
}
