package com.btg_desafio.pedidos.api.dto;

import com.btg_desafio.pedidos.model.Pedido;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PedidoRequestApiDto {
    String codigoPedido;
    String codigoCliente;
    List<ItemRequestApiDto> itens = new ArrayList<>();

    public Pedido PedidoRequestDtoToPedido() {
        Pedido pedido = new Pedido();
        pedido.setCodigoPedido(this.codigoPedido);
        pedido.setCodigoCliente(this.codigoCliente);

        return pedido;

    }
}
