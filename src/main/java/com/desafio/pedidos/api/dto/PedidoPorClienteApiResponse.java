package com.desafio.pedidos.api.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PedidoPorClienteApiResponse {
    private String codigoPedido;
    List<ItemResponseApiDto> itens = new ArrayList<>();

    public PedidoPorClienteApiResponse(String codigoPedido, List<ItemResponseApiDto> itens) {
        this.codigoPedido = codigoPedido;
        this.itens = itens;
    }

    public PedidoPorClienteApiResponse() {
        this.codigoPedido = codigoPedido;
        this.itens = itens;
    }
}
