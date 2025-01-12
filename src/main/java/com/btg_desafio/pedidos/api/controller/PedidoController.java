package com.btg_desafio.pedidos.api.controller;

import com.btg_desafio.pedidos.api.dto.PedidoApiResponseDto;
import com.btg_desafio.pedidos.api.dto.PedidoRequestApiDto;
import com.btg_desafio.pedidos.api.service.PedidoApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
