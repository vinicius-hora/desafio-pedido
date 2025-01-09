package com.btg_desafio.pedidos.mensageria.config;

import com.btg_desafio.pedidos.mensageria.dto.PedidoRequestDto;
import com.btg_desafio.pedidos.mensageria.dto.TestRabbitMqDto;
import com.btg_desafio.pedidos.mensageria.service.PedidoServiceMensageria;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitConsumerListener {

    private final PedidoServiceMensageria pedidoServiceMensageria;

    @RabbitListener(queues = {"pedido-request-queue"})
    public void receberPedido(@Payload PedidoRequestDto message){
        pedidoServiceMensageria.salvarPedido(message);

    }

}
