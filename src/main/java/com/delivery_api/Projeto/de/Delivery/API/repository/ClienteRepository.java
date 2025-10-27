package com.delivery_api.Projeto.de.Delivery.API.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery_api.Projeto.de.Delivery.API.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long> {
    // Buscar cliente por email
    Optional<Cliente> findByEmail(String email);

    // Verificar existência de email
    boolean existsByEmail(String email);

    // Buscar clientes ativos
    List<Cliente> findByAtivoTrue();

    // Buscar clientes inativos
    List<Cliente> findByNomeContainingIgnoreCase(String nome);


}
