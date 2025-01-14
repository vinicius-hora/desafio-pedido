package com.btg_desafio.pedidos.api.controller;

import com.btg_desafio.pedidos.api.dto.*;
import com.btg_desafio.pedidos.api.service.PedidoApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoApiService pedidoApiService;

    @PostMapping("/pedido")
    public ResponseEntity<PedidoApiResponseDto> salvarPedido(@RequestBody @Validated PedidoRequestApiDto pedidoRequestDto){

        var response = pedidoApiService.salvarPedido(pedidoRequestDto);

        return ResponseEntity.ok(response);

    }

    @GetMapping("pedido/{numeroPedido}")
    public ResponseEntity<ValorPedidoApiResponse> getValorTotalPedido(@PathVariable String numeroPedido){

        var retorno = pedidoApiService.valorTotalPedido(numeroPedido);

        return ResponseEntity.ok(retorno);
    }

    @GetMapping("cliente/{codigoCliente}")
    public ResponseEntity<TotalPedidosPorClienteApiResponse> getQuantidadePedidoPorCliente(@PathVariable String codigoCliente){

        var retorno = pedidoApiService.quantidadePedidosPorCliente(codigoCliente);

        return ResponseEntity.ok(retorno);
    }

    @GetMapping("pedido-cliente/{codigoCliente}")
    public ResponseEntity<List<PedidoPorClienteApiResponse>> getListPedidosPorCliente(@PathVariable String codigoCliente){

        var retorno = pedidoApiService.listPedidosPorCliente(codigoCliente);

        return ResponseEntity.ok(retorno);
    }
}
