package com.desafio.pedidos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
public class Pedido extends AbstractDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo_pedido")
    private String codigoPedido;
    @Column(name = "codigo_cliente")
    private String codigoCliente;
}
