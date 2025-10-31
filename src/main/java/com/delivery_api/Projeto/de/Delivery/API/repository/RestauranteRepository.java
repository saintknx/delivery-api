package com.delivery_api.Projeto.de.Delivery.API.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery_api.Projeto.de.Delivery.API.entity.Restaurante;


@Repository
public interface RestauranteRepository extends JpaRepository <Restaurante, Long>{
    
    // Buscar restaurante por nome
    Optional<Restaurante> findByNome(String nome);

    // Buscar restaurante por categoria
    List<Restaurante> findByCategoriaIgnoreCase(String categoria);

    // Buscar por nome ignorando maiusculas e minusculas
    List<Restaurante> findByNomeContainingIgnoreCase(String nome);

    // Buscar Restaurantes ativos
    List<Restaurante> findByAtivoTrue();

    //Buscar Restaurantes inativos
    List<Restaurante> findByAtivoFalse();    

    // Ordenação de restaurantes por avaliação
    List<Restaurante> findAllByOrderByAvaliacaoDesc();

    boolean existsByTelefone(String telefone);


}
