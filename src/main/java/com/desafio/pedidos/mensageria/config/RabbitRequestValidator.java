package com.desafio.pedidos.mensageria.config;

import com.desafio.pedidos.mensageria.dto.PedidoRequestDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class RabbitRequestValidator {

    private final Validator validator;

    public void validate(PedidoRequestDto pedidoRequestDto) {
        Set<ConstraintViolation<PedidoRequestDto>> violations = validator.validate(pedidoRequestDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validação falhou para PedidoRequestDto", violations);
        }
    }

}
