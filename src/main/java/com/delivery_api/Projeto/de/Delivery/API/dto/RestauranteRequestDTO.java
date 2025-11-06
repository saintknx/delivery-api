package com.delivery_api.Projeto.de.Delivery.API.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RestauranteRequestDTO {
    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "A categoria é obrigatória.")
    private String categoria;

    @NotBlank(message = "O endereço é obrigatório.")
    private String endereco;

    @NotBlank(message = "O telefone é obrigatório.")
    private String telefone;
    
    
    private Double taxa_entrega;
    
}
