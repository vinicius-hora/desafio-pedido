package com.desafio.pedidos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "item")
@ToString
public class Item extends AbstractDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo_pedido")
    private String codigoPedido;
    @Column(name = "produto")
    private String produto;
    @Column(name = "quantidade")
    private Integer quantidade;
    @Column(name = "preco")
    private Double preco;
}

/*
id
    codigoPedido
    produto
    quantidade
    preco
 */