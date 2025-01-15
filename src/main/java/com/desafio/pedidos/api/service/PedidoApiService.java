package com.desafio.pedidos.api.service;

import com.desafio.pedidos.api.Exception.GenericRuntimeApiException;
import com.desafio.pedidos.api.dto.*;
import com.desafio.pedidos.api.repository.PedidoApiRepository;
import com.desafio.pedidos.model.Item;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

    public ValorPedidoApiResponse valorTotalPedido(String numeroPedido) {
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

    public TotalPedidosPorClienteApiResponse quantidadePedidosPorCliente(String codigoCliente) {
        TotalPedidosPorClienteApiResponse clienteApiResponse = new TotalPedidosPorClienteApiResponse();
        AtomicReference<Double> valor = new AtomicReference<>(0.0);
        List<Item> itemList = new ArrayList<>();

        var pedidoList = pedidoApiRepository.pedidosPorCodigoCliente(codigoCliente);
        pedidoList.stream().forEach(p ->{
            itemList.addAll(itemApiService.buscarItensPeloPedido(p.getCodigoPedido()));
        });

        itemList.stream().forEach( i -> {
            valor.updateAndGet(v -> v + i.getPreco());
        });

        clienteApiResponse.setQuantidadePedidos(String.valueOf(pedidoList.size()));
        clienteApiResponse.setValorTotal(formatarValor(valor.get()));

        return clienteApiResponse;
    }

    private Double formatarValor(Double valor){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.00", symbols);
        df.setGroupingUsed(false);

        String formattedValue = df.format(valor);

        return Double.parseDouble(formattedValue);
    }

    public List<PedidoPorClienteApiResponse> listPedidosPorCliente(String codigoCliente) {
        List<PedidoPorClienteApiResponse> clienteApiResponseList = new ArrayList<>();

        var pedidoList = pedidoApiRepository.pedidosPorCodigoCliente(codigoCliente);
        pedidoList.stream().forEach( p ->{
            var itemList = itemApiService.buscarItensPeloPedido(p.getCodigoPedido());
            var itensResponseDto = itemList.stream().map(ItemResponseApiDto::new).toList();
            var clienteApiResponse = new PedidoPorClienteApiResponse(p.getCodigoPedido(), itensResponseDto);

            clienteApiResponseList.add(clienteApiResponse);
        });

        return  clienteApiResponseList;
    }
}
