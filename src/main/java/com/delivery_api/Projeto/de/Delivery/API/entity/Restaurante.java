package com.delivery_api.Projeto.de.Delivery.API.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Indica que esta classe é uma entidade JPA
@Data // Gera automaticamente getters, setters, toString, equals e hashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurantes") // Mapeia a entidade para a tabela "restaurantes" no banco de dados
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    private String nome;

    private String categoria;

    private String endereco;

    private String telefone;

    private Double taxa_entrega;

    private Double avaliacao;

    @Column(nullable = true)
    private Boolean ativo;

    public void inativar() {
        this.ativo = false;
    }
}
