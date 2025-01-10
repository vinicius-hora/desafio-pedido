package com.btg_desafio.pedidos.api.repository;

import com.btg_desafio.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoApiRepository extends JpaRepository<Pedido, Long> {
}
