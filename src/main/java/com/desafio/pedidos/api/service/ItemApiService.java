package com.desafio.pedidos.api.service;

import com.desafio.pedidos.api.Exception.GenericRuntimeApiException;
import com.desafio.pedidos.api.dto.PedidoRequestApiDto;
import com.desafio.pedidos.api.repository.ItemApiRepository;
import com.desafio.pedidos.model.Item;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
            throw new GenericRuntimeApiException(e.getMessage());
        }
    }

    public List<Item> buscarItensPeloPedido(String numeroPedido) {
        var itemList = itemApiRepository.buscarItensPeloPedido(numeroPedido);
        if(itemList.isEmpty()){
            throw new GenericRuntimeApiException("nenhum pedido encontrado para o código informado.");
        }

        return itemList;
    }
}
