package com.btg_desafio.pedidos.api.dto;

import lombok.Data;

@Data
public class TotalPedidosPorClienteApiResponse {
    private String quantidadePedidos;
    private Double valorTotal;
}
