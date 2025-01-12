package com.btg_desafio.pedidos.mensageria.config;

import com.btg_desafio.pedidos.mensageria.dto.PedidoRequestDto;
import com.btg_desafio.pedidos.mensageria.dto.TestRabbitMqDto;
import com.btg_desafio.pedidos.mensageria.service.PedidoServiceMensageria;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitConsumerListener {
    private final RabbitRequestValidator requestValidator;

    private final PedidoServiceMensageria pedidoServiceMensageria;

    @RabbitListener(queues = {"pedido-request-queue"})
    public void receberPedido(@Payload PedidoRequestDto message){
        try{
            requestValidator.validate(message);
            pedidoServiceMensageria.salvarPedido(message);

        } catch (ConstraintViolationException e) {

            log.error("Erro de validação: " + e.getMessage());
            e.getConstraintViolations().forEach(violation ->
                    log.error("Campo: " + violation.getPropertyPath() + " - " + violation.getMessage())
            );

        }


    }

}
