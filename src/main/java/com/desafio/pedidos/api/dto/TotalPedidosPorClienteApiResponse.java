package com.desafio.pedidos.api.dto;

import lombok.Data;

@Data
public class TotalPedidosPorClienteApiResponse {
    private String quantidadePedidos;
    private Double valorTotal;
}
