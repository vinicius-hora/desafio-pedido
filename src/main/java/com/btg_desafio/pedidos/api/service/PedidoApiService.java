package com.btg_desafio.pedidos.api.service;

import com.btg_desafio.pedidos.api.Exception.GenericRuntimeApiException;
import com.btg_desafio.pedidos.api.dto.PedidoApiResponseDto;
import com.btg_desafio.pedidos.api.dto.PedidoRequestApiDto;
import com.btg_desafio.pedidos.api.dto.ValorPedidoApiResponse;
import com.btg_desafio.pedidos.api.repository.PedidoApiRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoApiService {

    private final PedidoApiRepository pedidoApiRepository;
    private final ItemApiService itemApiService;

    @Transactional(rollbackOn = GenericRuntimeApiException.class )
    public PedidoApiResponseDto salvarPedido(PedidoRequestApiDto pedidoRequestDto) {
        PedidoApiResponseDto responseDto = new PedidoApiResponseDto();
        try{
            validarPedido(pedidoRequestDto);
            var pedido = pedidoRequestDto.PedidoRequestDtoToPedido();

            pedidoApiRepository.save(pedido);
            log.info("Pedido salvo, codigo: {}", pedidoRequestDto.getCodigoPedido());

            itemApiService.salvarItem(pedidoRequestDto);


        responseDto = montarResponse(pedidoRequestDto);
        return responseDto;
        } catch (GenericRuntimeApiException e) {
            throw new GenericRuntimeApiException(e.getMessage());
        }
    }

    private void validarPedido(PedidoRequestApiDto pedidoRequestDto){
        var pedidoOptional = pedidoApiRepository.buscarPedidoPorCodigoPedido(pedidoRequestDto.getCodigoPedido());
        if(pedidoOptional.isPresent()){
            throw new GenericRuntimeApiException("Codigo de pedio ja cadastrado");
        }
    }

    private PedidoApiResponseDto montarResponse(PedidoRequestApiDto requestApiDto){
        PedidoApiResponseDto responseDto = new PedidoApiResponseDto();

        responseDto.setCodigoPedido(requestApiDto.getCodigoPedido());
        responseDto.setCodigoCliente(requestApiDto.getCodigoCliente());
        responseDto.setQuantidadeItens(requestApiDto.getItens().size());

        return responseDto;
    }

    public ValorPedidoApiResponse ValorTotalPedido(String numeroPedido) {
        ValorPedidoApiResponse valorPedidoApiResponse = new ValorPedidoApiResponse();
        AtomicReference<Double> valor = new AtomicReference<>(0.0);

        var itens = itemApiService.buscarItensPeloPedido(numeroPedido);
        itens.stream().forEach( i -> {
            valor.updateAndGet(v -> v + i.getPreco());
        });

        valorPedidoApiResponse.setCodigoPedido(numeroPedido);
        valorPedidoApiResponse.setValorTotal(valor.get());

        return valorPedidoApiResponse;
    }
}
