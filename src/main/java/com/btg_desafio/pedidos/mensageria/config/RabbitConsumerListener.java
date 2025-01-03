package com.btg_desafio.pedidos.mensageria.config;

import com.btg_desafio.pedidos.mensageria.dto.TestRabbitMqDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsumerListener {

    @RabbitListener(queues = {"pedido-request-queue"})
    public void receberPedido(@Payload TestRabbitMqDto message){
        System.out.printf("Mensagem recebida: %s", message);

    }

}
