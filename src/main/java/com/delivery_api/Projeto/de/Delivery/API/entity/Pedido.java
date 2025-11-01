package com.delivery_api.Projeto.de.Delivery.API.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedidos")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "numero_pedido", nullable = false, unique = true)
    private String numeroPedido;

    @Column(name = "data_pedido", nullable = false)
    private LocalDateTime dataPedido;

    @Column(nullable = false)
    private String status;

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    private String observacoes;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @Column(columnDefinition = "TEXT")
    private String itens;
}