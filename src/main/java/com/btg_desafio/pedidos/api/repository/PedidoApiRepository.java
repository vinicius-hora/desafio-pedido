package com.btg_desafio.pedidos.api.repository;

import com.btg_desafio.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoApiRepository extends JpaRepository<Pedido, Long> {
    @Query("select p from Pedido p where p.codigoPedido =  :codigoPedido")
    Optional<Pedido> buscarPedidoPorCodigoPedido(@Param("codigoPedido") String codigoPedido);

    @Query("select p from Pedido p where p.codigoCliente =  :codigoCliente")
    List<Pedido> pedidosPorCodigoCliente(@Param("codigoCliente") String codigoCliente);
}
