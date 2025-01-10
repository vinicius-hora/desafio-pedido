package com.btg_desafio.pedidos.api.dto;

import com.btg_desafio.pedidos.model.Item;
import lombok.Data;

@Data
public class ItemRequestApiDto {
    String produto;
    Integer quantidade;
    Double preco;

    public Item itemRequestDtoToItem(String codigoPedido){
        Item item = new Item();
        item.setCodigoPedido(codigoPedido);
        item.setProduto(this.produto);
        item.setQuantidade(this.quantidade);
        item.setPreco(this.getPreco());

        return item;
    }
}
