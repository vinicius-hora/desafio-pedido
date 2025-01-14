package com.btg_desafio.pedidos.api.controller;

import com.btg_desafio.pedidos.api.dto.PedidoApiResponseDto;
import com.btg_desafio.pedidos.api.dto.PedidoRequestApiDto;
import com.btg_desafio.pedidos.api.dto.ValorPedidoApiResponse;
import com.btg_desafio.pedidos.api.service.PedidoApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoApiService pedidoApiService;

    @PostMapping
    public ResponseEntity<PedidoApiResponseDto> salvarPedido(@RequestBody @Validated PedidoRequestApiDto pedidoRequestDto){

        var response = pedidoApiService.salvarPedido(pedidoRequestDto);

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{numeroPedido}")
    public ResponseEntity<ValorPedidoApiResponse> getValorTotalPedido(@PathVariable String numeroPedido){

        var retorno = pedidoApiService.ValorTotalPedido(numeroPedido);

        return ResponseEntity.ok(retorno);
    }
}
