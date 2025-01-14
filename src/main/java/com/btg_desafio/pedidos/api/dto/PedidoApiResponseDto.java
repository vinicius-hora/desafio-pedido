package com.btg_desafio.pedidos.api.dto;

import com.btg_desafio.pedidos.model.Pedido;
import lombok.Data;

@Data
public class PedidoApiResponseDto {
    String codigoPedido;
    String codigoCliente;
    Integer quantidadeItens;
}
