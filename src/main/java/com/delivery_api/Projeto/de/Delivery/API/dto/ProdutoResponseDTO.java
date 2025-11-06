package com.delivery_api.Projeto.de.Delivery.API.dto;

import com.delivery_api.Projeto.de.Delivery.API.entity.Produto;

import lombok.Data;

@Data
public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private String categoria;
    private Boolean disponivel;
    private Long restauranteId;

    public ProdutoResponseDTO(Produto save) {
        this.id = save.getId();
        this.nome = save.getNome();
        this.descricao = save.getDescricao();
        this.preco = save.getPreco();
        this.categoria = save.getCategoria();
        this.disponivel = save.getDisponivel();
        this.restauranteId = save.getRestaurante().getId();
    }

}
