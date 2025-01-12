package com.btg_desafio.pedidos.mensageria.repository;

import com.btg_desafio.pedidos.model.Pedido;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query("select p from Pedido p where p.codigoPedido =  :codigoPedido")
    Optional<Pedido> buscarPedidoPorCodigoPedido(@Param("codigoPedido") String codigoPedido);
}
