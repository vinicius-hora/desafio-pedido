package com.btg_desafio.pedidos.api.dto;

import com.btg_desafio.pedidos.model.Pedido;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class PedidoRequestApiDto {

    @NotBlank(message = "Código do pedido não pode ser nulo ou vazio.")
    String codigoPedido;

    @NotBlank(message = "Código do cliente não pode ser nulo ou vazio.")
    String codigoCliente;
    List<ItemRequestApiDto> itens = new ArrayList<>();

    public Pedido PedidoRequestDtoToPedido() {
        Pedido pedido = new Pedido();
        pedido.setCodigoPedido(this.codigoPedido);
        pedido.setCodigoCliente(this.codigoCliente);

        return pedido;

    }
}
