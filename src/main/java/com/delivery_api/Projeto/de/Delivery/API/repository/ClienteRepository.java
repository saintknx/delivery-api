package com.delivery_api.Projeto.de.Delivery.API.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery_api.Projeto.de.Delivery.API.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long> {
    
    
    Optional<Cliente> findByEmail(String email);

    
    boolean existsByEmail(String email);

    
    List<Cliente> findByAtivoTrue();

    
    List<Cliente> findByNomeContainingIgnoreCase(String nome);


}
