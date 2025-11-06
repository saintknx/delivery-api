package com.delivery_api.Projeto.de.Delivery.API.dto;

import com.delivery_api.Projeto.de.Delivery.API.entity.Pedido;

import lombok.Data;

@Data
public class PedidoResponseDTO {
    private Long id;
    private String numeroPedido;
    private String status;
    private String observacoes;
    private Long clienteId;
    private Long restauranteId;
    private String itens;
    
    public PedidoResponseDTO(Pedido save) {
        this.id = save.getId();
        this.numeroPedido = save.getNumeroPedido();
        this.status = save.getStatus();
        this.observacoes = save.getObservacoes();
        this.clienteId = save.getClienteId();
        this.restauranteId = save.getRestaurante().getId();
        this.itens = save.getItens();
    }
}
