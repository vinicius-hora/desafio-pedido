package com.btg_desafio.pedidos.api.dto;

import com.btg_desafio.pedidos.model.Item;
import lombok.Data;

@Data
public class ItemResponseApiDto {
    String produto;
    Integer quantidade;
    Double preco;

    public ItemResponseApiDto (Item item){
        this.produto = item.getProduto();
        this.quantidade = item.getQuantidade();
        this.preco = item.getPreco();
    }

    public ItemResponseApiDto() {

    }
}
