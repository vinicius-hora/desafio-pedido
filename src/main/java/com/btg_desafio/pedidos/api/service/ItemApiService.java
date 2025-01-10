package com.btg_desafio.pedidos.api.service;

import com.btg_desafio.pedidos.api.Exception.GenericRuntimeApiException;
import com.btg_desafio.pedidos.api.dto.PedidoRequestApiDto;
import com.btg_desafio.pedidos.api.repository.ItemApiRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemApiService {

    private final ItemApiRepository itemApiRepository;

    @Transactional(rollbackOn = GenericRuntimeApiException.class )
    public void salvarItem(PedidoRequestApiDto pedidoRequestDto) {
        try{

            var itens = pedidoRequestDto.getItens().stream()
                    .map(i -> i.itemRequestDtoToItem(pedidoRequestDto.getCodigoPedido())).toList();

            itemApiRepository.saveAll(itens);
            log.info("item salvo para o pedido: {}", pedidoRequestDto.getCodigoPedido());

        } catch (GenericRuntimeApiException e) {
            throw new GenericRuntimeApiException(e);
        }
    }
}
