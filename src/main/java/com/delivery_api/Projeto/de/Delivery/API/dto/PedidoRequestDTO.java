package com.delivery_api.Projeto.de.Delivery.API.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PedidoRequestDTO {

    @NotBlank(message = "O campo numero do Pedido não pode estar vazio.")
    private String numeroPedido;

    @NotBlank(message = "O campo status não pode estar vazio.")
    private String status;

    
    private LocalDateTime dataPedido;
    
    private String observacoes;
    @NotBlank(message = "O campo clienteId não pode estar vazio.")
    private Long clienteId;
    @NotBlank(message = "O campo restauranteId não pode estar vazio.")
    private Long restauranteId;
    @NotBlank(message = "O campo itens não pode estar vazio.")
    private String itens;


}
