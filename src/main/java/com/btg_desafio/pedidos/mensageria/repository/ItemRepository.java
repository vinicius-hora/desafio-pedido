package com.btg_desafio.pedidos.mensageria.repository;

import com.btg_desafio.pedidos.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
