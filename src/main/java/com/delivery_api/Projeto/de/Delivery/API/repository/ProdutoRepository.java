package com.delivery_api.Projeto.de.Delivery.API.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery_api.Projeto.de.Delivery.API.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {


    //Buscar todos produtos de um restaurante
    List<Produto> findByRestauranteId(Long restauranteId);

    //Buscar por categoria
    List<Produto> findByCategoria(String categoria);
    
    // Buscar por disponibilidade - true
    List<Produto> findByDisponivelTrue();
    
    // Buscar por disponibilidade - false
    List<Produto> findByDisponivelFalse();
    
}
