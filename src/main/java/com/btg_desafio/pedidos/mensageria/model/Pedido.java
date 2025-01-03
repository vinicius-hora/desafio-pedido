package com.btg_desafio.pedidos.mensageria.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo_pedido")
    private String codigoPedido;
    @Column(name = "codigo_cliente")
    private String codigoCliente;
}
