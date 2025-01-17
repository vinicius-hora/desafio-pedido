package com.desafio.pedidos.mensageria.service;

import com.desafio.pedidos.mensageria.dto.PedidoRequestDto;
import com.desafio.pedidos.mensageria.exception.GenericRuntimeException;
import com.desafio.pedidos.mensageria.repository.ItemRepository;
import com.desafio.pedidos.mensageria.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PedidoServiceMensageria {

    private final PedidoRepository pedidoRepository;
    private final ItemRepository itemRepository;

    @Transactional(rollbackOn = GenericRuntimeException.class )
    public void salvarPedido(PedidoRequestDto pedidoRequestDto){

        try{
            validarPedido(pedidoRequestDto);
            var pedido = pedidoRequestDto.PedidoRequestDtoToPedido();
            var itens = pedidoRequestDto.getItens().stream()
                    .map(i -> i.itemRequestDtoToItem(pedidoRequestDto.getCodigoPedido())).toList();

            pedidoRepository.save(pedido);
            itemRepository.saveAll(itens);

            log.info("Mensagem Recebida e processada com sucesso, pedido: {}", pedidoRequestDto.getCodigoPedido());

        }catch (GenericRuntimeException e){
            log.error("Erro ao salvar pedido: " + e.getMessage());

        }
    }

    private void validarPedido(PedidoRequestDto pedidoRequestDto){
        var pedidoOptional = pedidoRepository.buscarPedidoPorCodigoPedido(pedidoRequestDto.getCodigoPedido());
        if(pedidoOptional.isPresent()){
            throw new GenericRuntimeException("Codigo de pedio ja cadastrado");
        }
    }


}
