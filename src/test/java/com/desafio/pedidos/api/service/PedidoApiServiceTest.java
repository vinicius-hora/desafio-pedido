package com.desafio.pedidos.api.service;

import com.desafio.pedidos.api.dto.ItemRequestApiDto;
import com.desafio.pedidos.api.dto.PedidoRequestApiDto;
import com.desafio.pedidos.api.repository.PedidoApiRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PedidoApiServiceTest {

    @Autowired
    private PedidoApiService pedidoApiService;
    @Autowired
    private PedidoApiRepository pedidoApiRepository;

    @Test
    @Order(0)
    void salvarPedido() {

        var retornoPedido = pedidoApiService.salvarPedido(montarPedido());
        var pedidoBanco = pedidoApiRepository.buscarPedidoPorCodigoPedido(montarPedido().getCodigoPedido());



        assertTrue(pedidoBanco.isPresent(), "Pedido não encontrado no banco de dados");
        Assertions.assertEquals(retornoPedido.getCodigoPedido(), pedidoBanco.get().getCodigoPedido(), "Não salvou o pedido corretamente");
        Assertions.assertEquals(retornoPedido.getQuantidadeItens(), montarPedido().getItens().size(), "Não salvou os itens corretamente");
    }

    @Test
    @Order(1)
    void valorTotalPedido() {

        var retorno = pedidoApiService.valorTotalPedido(montarPedido().getCodigoPedido());

        Assertions.assertEquals(valorTotalItens(), retorno.getValorTotal(), "Não buscou o valor dos itens corretamente");
    }

    @Test
    @Order(2)
    void quantidadePedidosPorCliente() {

        var retornoQuantidadePedidos = pedidoApiService.quantidadePedidosPorCliente(montarPedido().getCodigoCliente());
        Assertions.assertEquals("1", retornoQuantidadePedidos.getQuantidadePedidos(), "Não buscou os pedidos corretamente");
    }

    @Test
    @Order(3)
    void listPedidosPorCliente() {
        var retornoPedidosPorCliente = pedidoApiService.listPedidosPorCliente(montarPedido().getCodigoCliente());
        Assertions.assertEquals(1, retornoPedidosPorCliente.size(), "Não buscou os pedidos corretamente");
    }

    private PedidoRequestApiDto montarPedido(){
        PedidoRequestApiDto pedidoRequestDto = new PedidoRequestApiDto();

        pedidoRequestDto.setCodigoPedido("1001");
        pedidoRequestDto.setCodigoCliente("1");
        pedidoRequestDto.setItens(itensList());

        return pedidoRequestDto;
    }


    private List<ItemRequestApiDto> itensList(){
        List<ItemRequestApiDto> itemRequestDtoList = new ArrayList<>();
        ItemRequestApiDto itemRequestDto1 = new ItemRequestApiDto();
        ItemRequestApiDto itemRequestDto2 = new ItemRequestApiDto();

        itemRequestDto1.setQuantidade(10);
        itemRequestDto1.setProduto("lapis");
        itemRequestDto1.setPreco(1.00);

        itemRequestDto2.setQuantidade(20);
        itemRequestDto2.setProduto("caneta");
        itemRequestDto2.setPreco(1.50);

        itemRequestDtoList.add(itemRequestDto1);
        itemRequestDtoList.add(itemRequestDto2);

        return itemRequestDtoList;

    }

    private Double valorTotalItens(){
        var itens = itensList();
        AtomicReference<Double> valor = new AtomicReference<>(0.0);


        itens.stream().forEach( i -> {
            valor.updateAndGet(v -> v + i.getPreco());
        });

        return valor.get();
    }
}