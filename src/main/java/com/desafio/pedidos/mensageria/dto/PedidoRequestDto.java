package com.desafio.pedidos.mensageria.dto;

import com.desafio.pedidos.model.Pedido;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PedidoRequestDto {
    @NotBlank(message = "Código do pedido não pode ser nulo ou vazio.")
    String codigoPedido;

    @NotBlank(message = "Código do cliente não pode ser nulo ou vazio.")
    String codigoCliente;
    List<ItemRequestDto> itens = new ArrayList<>();

    public Pedido PedidoRequestDtoToPedido() {
        Pedido pedido = new Pedido();
        pedido.setCodigoPedido(this.codigoPedido);
        pedido.setCodigoCliente(this.codigoCliente);

        return pedido;

    }


}
