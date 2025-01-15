package com.btg_desafio.pedidos.mensageria.service;

import com.btg_desafio.pedidos.mensageria.dto.ItemRequestDto;
import com.btg_desafio.pedidos.mensageria.dto.PedidoRequestDto;
import com.btg_desafio.pedidos.mensageria.repository.ItemRepository;
import com.btg_desafio.pedidos.mensageria.repository.PedidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class PedidoServiceMensageriaTest {

    @Autowired
    private PedidoServiceMensageria pedidoServiceMensageria;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void salvarPedidoMensageria() {
        pedidoServiceMensageria.salvarPedido(montarPedido());

        var pedido = pedidoRepository.buscarPedidoPorCodigoPedido(montarPedido().getCodigoPedido());
        var items = itemRepository.findAll();

        assertTrue(pedido.isPresent(), "Pedido não encontrado no banco de dados");
        Assertions.assertEquals(pedido.get().getCodigoPedido(), montarPedido().getCodigoPedido(), "Não salvou o pedido corretamente");
        Assertions.assertEquals(items.get(0).getCodigoPedido(), montarPedido().getCodigoPedido(), "Não salvou os itens corretamente");

    }

    private PedidoRequestDto montarPedido(){
        PedidoRequestDto pedidoRequestDto = new PedidoRequestDto();
        List<ItemRequestDto> itemRequestDtoList = new ArrayList<>();

        itemRequestDtoList.add(item1());
        itemRequestDtoList.add(item2());

        pedidoRequestDto.setCodigoPedido("1001");
        pedidoRequestDto.setCodigoCliente("1");
        pedidoRequestDto.setItens(itemRequestDtoList);

        return pedidoRequestDto;
    }

    private ItemRequestDto item1(){
        ItemRequestDto itemRequestDto = new ItemRequestDto();
        itemRequestDto.setQuantidade(10);
        itemRequestDto.setProduto("lapis");
        itemRequestDto.setPreco(1.00);

        return itemRequestDto;
    }

    private ItemRequestDto item2(){
        ItemRequestDto itemRequestDto = new ItemRequestDto();
        itemRequestDto.setQuantidade(20);
        itemRequestDto.setProduto("caneta");
        itemRequestDto.setPreco(1.50);

        return itemRequestDto;
    }
}