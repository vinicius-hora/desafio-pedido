package com.btg_desafio.pedidos.api.dto;

import lombok.Data;

@Data
public class ValorPedidoApiResponse {
    private String codigoPedido;
    private Double valorTotal;
}
