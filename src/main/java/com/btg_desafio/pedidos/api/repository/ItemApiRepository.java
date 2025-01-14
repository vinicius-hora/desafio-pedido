package com.btg_desafio.pedidos.api.repository;

import com.btg_desafio.pedidos.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemApiRepository extends JpaRepository<Item, Long> {
    @Query("select i from Item i where i.codigoPedido =  :numeroPedido")
    List<Item> buscarItensPeloPedido(@Param("numeroPedido") String numeroPedido);
}
