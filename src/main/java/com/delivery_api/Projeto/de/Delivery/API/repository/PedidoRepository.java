package com.delivery_api.Projeto.de.Delivery.API.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery_api.Projeto.de.Delivery.API.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Buscar pedidos pelo cliente ID (ordenados por data decrescente)
    List<Pedido> findByClienteIdOrderByDataPedidoDesc(Long clienteId);

    // Buscar por número do pedido
    Pedido findByNumeroPedido(String numeroPedido);

    // Buscar pedidos restaurante ID (ordenados por data decrescente)
    List<Pedido> findByRestauranteIdOrderByDataPedidoDesc(Long restauranteId);
}