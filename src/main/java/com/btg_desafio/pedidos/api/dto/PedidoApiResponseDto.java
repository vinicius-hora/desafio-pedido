package com.btg_desafio.pedidos.api.dto;

import lombok.Data;

@Data
public class PedidoApiResponseDto {
    String codigoPedido;
    String codigoCliente;
    Integer quantidadeItens;
}
