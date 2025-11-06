package com.delivery_api.Projeto.de.Delivery.API.dto;

import com.delivery_api.Projeto.de.Delivery.API.entity.Restaurante;

import lombok.Data;
@Data
public class RestauranteResponseDTO {
    private Long id;
    private String nome;
    private String categoria;
    private String endereco;
    private String telefone;
    private Double taxa_entrega;
    private Double avaliacao;
    private Boolean ativo;

    public RestauranteResponseDTO(Restaurante save) {
        this.id = save.getId();
        this.nome = save.getNome();
        this.categoria = save.getCategoria();
        this.endereco = save.getEndereco();
        this.telefone = save.getTelefone();
        this.taxa_entrega = save.getTaxa_entrega();
        this.avaliacao = save.getAvaliacao();
        this.ativo = save.getAtivo();
    }
}
