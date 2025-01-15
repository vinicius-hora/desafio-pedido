package com.desafio.pedidos.api.controller;

import com.desafio.pedidos.api.dto.*;
import com.desafio.pedidos.api.service.PedidoApiService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido Salvo",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = PedidoApiResponseDto.class)) }),
        @ApiResponse(responseCode = "400", description = "Erro ao salvar pedido",
            content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/pedido")
    public ResponseEntity<PedidoApiResponseDto> salvarPedido(@RequestBody @Validated PedidoRequestApiDto pedidoRequestDto){

        var response = pedidoApiService.salvarPedido(pedidoRequestDto);

        return ResponseEntity.ok(response);

    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Valor total do pedido retornado",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = ValorPedidoApiResponse.class)) })
    })
    @GetMapping("pedido/{numeroPedido}")
    public ResponseEntity<ValorPedidoApiResponse> getValorTotalPedido(@PathVariable("numeroPedido") String numeroPedido){

        var retorno = pedidoApiService.valorTotalPedido(numeroPedido);

        return ResponseEntity.ok(retorno);
    }
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Quantidade total de pedidos por cliente retornado",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = TotalPedidosPorClienteApiResponse.class)) })
    })
    @GetMapping("cliente/{codigoCliente}")
    public ResponseEntity<TotalPedidosPorClienteApiResponse> getQuantidadePedidoPorCliente(@PathVariable("codigoCliente") String codigoCliente){

        var retorno = pedidoApiService.quantidadePedidosPorCliente(codigoCliente);

        return ResponseEntity.ok(retorno);
    }
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pedidos por cliente retornado",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = PedidoPorClienteApiResponse.class)) })
    })
    @GetMapping("pedido-cliente/{codigoCliente}")
    public ResponseEntity<List<PedidoPorClienteApiResponse>> getListPedidosPorCliente(@PathVariable("codigoCliente") String codigoCliente){

        var retorno = pedidoApiService.listPedidosPorCliente(codigoCliente);

        return ResponseEntity.ok(retorno);
    }
}
