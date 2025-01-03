package com.btg_desafio.pedidos.mensageria.service;

import com.btg_desafio.pedidos.mensageria.dto.TestRabbitMqDto;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiverService {

    public void receiveMessage(TestRabbitMqDto message) {
        System.out.println("Mensagem recebida: " + message.getTeste());
    }

}
