package com.btg_desafio.pedidos.mensageria.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "codigo_pedido")
    private String codigoPedido;
    @Column(name = "produto")
    private String produto;
    @Column(name = "quantidade")
    private int quantidade;
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