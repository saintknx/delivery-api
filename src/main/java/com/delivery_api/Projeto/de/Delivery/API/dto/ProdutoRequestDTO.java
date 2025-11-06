package com.delivery_api.Projeto.de.Delivery.API.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoRequestDTO {
    @NotBlank(message = "O nome do produto é obrigatório.")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    private String descricao;

    @NotBlank(message = "O preço do produto é obrigatório.")
    @Positive(message = "Preço deve ser maior que zero")
    private Double preco;

    @NotBlank(message = "O ID do restaurante é obrigatório.")
    private Long restauranteId;

    @NotBlank(message = "A categoria do produto é obrigatória.")
    private String categoria;

}
