package com.btg_desafio.pedidos.api.service;

import com.btg_desafio.pedidos.api.Exception.GenericRuntimeApiException;
import com.btg_desafio.pedidos.api.dto.PedidoRequestApiDto;
import com.btg_desafio.pedidos.api.repository.PedidoApiRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoApiService {

    private final PedidoApiRepository pedidoApiRepository;
    private final ItemApiService itemApiService;

    @Transactional(rollbackOn = GenericRuntimeApiException.class )
    public void salvarPedido(PedidoRequestApiDto pedidoRequestDto) {
        try{
            var pedido = pedidoRequestDto.PedidoRequestDtoToPedido();

            pedidoApiRepository.save(pedido);
            log.info("Pedido salvo, codigo: {}", pedidoRequestDto.getCodigoPedido());

            itemApiService.salvarItem(pedidoRequestDto);

        } catch (GenericRuntimeApiException e) {
            throw new GenericRuntimeApiException(e);
        }
    }
}
