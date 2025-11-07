package com.delivery_api.Projeto.de.Delivery.API.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivery_api.Projeto.de.Delivery.API.dto.RestauranteRequestDTO;
import com.delivery_api.Projeto.de.Delivery.API.dto.RestauranteResponseDTO;
import com.delivery_api.Projeto.de.Delivery.API.entity.Restaurante;
import com.delivery_api.Projeto.de.Delivery.API.repository.RestauranteRepository;

@Service
@Transactional
public class RestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;

    /**
     * Cadastrar novo restaurante
     */
    public RestauranteResponseDTO cadastrar(RestauranteRequestDTO dto){
    if (restauranteRepository.existsByTelefone(dto.getTelefone())) {
        throw new IllegalArgumentException("Telefone já cadastrado: " + dto.getTelefone());
    }
    

    Restaurante restaurante = new Restaurante();
    restaurante.setNome(dto.getNome());
    restaurante.setTelefone(dto.getTelefone());
    restaurante.setCategoria(dto.getCategoria());
    restaurante.setEndereco(dto.getEndereco());
    restaurante.setTaxa_entrega(dto.getTaxa_entrega());
    restaurante.setAvaliacao(dto.getAvaliacao());
    
    restaurante.setAtivo(true);
    restauranteRepository.save(restaurante);
        
        return new RestauranteResponseDTO(restauranteRepository.save(restaurante));
    }



    /**
     * Listar todos os restaurantes ativos
     */
    @Transactional(readOnly = true)
    public List<Restaurante> listarAtivos(){
        return restauranteRepository.findByAtivoTrue();
    }

    /**
     * Atualizar dados do restaurante
     */
    public Restaurante atualizar(Long id, Restaurante restauranteAtualizado) {
        Restaurante restaurante = buscarPorId(id)
                .orElseThrow(()-> new IllegalArgumentException("Restaurante não encontrado com ID: " + id));
        // verifica se o telefone não está sendo usado
        if(!restaurante.getTelefone().equals(restauranteAtualizado.getTelefone()) &&
                restauranteRepository.existsByTelefone(restauranteAtualizado.getTelefone())) {
            throw new IllegalArgumentException("Telefone já cadastrado: " + restauranteAtualizado.getTelefone());
            }
        // Atualizar campos
        restaurante.setNome(restauranteAtualizado.getNome());
        restaurante.setTelefone(restauranteAtualizado.getTelefone());
        restaurante.setCategoria(restauranteAtualizado.getCategoria());
        restaurante.setEndereco(restauranteAtualizado.getEndereco());

        return restauranteRepository.save(restaurante);
    }

    /**
     * Inativar restaurante (soft delete)
     */
    public void inativar(Long id) {
        Restaurante restaurante = buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado com ID: "+ id));
        restaurante.inativar();
        restauranteRepository.save(restaurante);
    
    }
    /*
     * Ativar restaurante
     */
    public void ativar(Long id) {
    Restaurante restaurante = buscarPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado com ID: "+ id));
    
    restaurante.setAtivo(true); 
    restauranteRepository.save(restaurante);
}

    

    /**
     * Buscar restaurantes por nome
     */
    @Transactional(readOnly = true)
    public List<Restaurante> buscarPorNome(String nome) {
        return restauranteRepository.findByNomeContainingIgnoreCase(nome);
    }

    // /**
    //  * Validações de negócio específicas do restaurante
    //  */
    // private void validarDadosRestaurante(Restaurante restaurante) {
    //     if (restaurante.getNome() == null || restaurante.getNome().trim().isEmpty()) {
    //         throw new IllegalArgumentException("Nome do restaurante é obrigatório.");
    //     }
        
    //     if (restaurante.getTelefone() == null || restaurante.getTelefone().trim().isEmpty()) {
    //         throw new IllegalArgumentException("Telefone do restaurante é obrigatório.");
    //     }

    //     if (restaurante.getNome().length() < 2){
    //         throw new IllegalArgumentException("Nome do restaurante deve ter pelo menos 2 caracteres.");
    //     }
    // }

    /**
     * Buscar restaurante por categoria
     */

    @Transactional(readOnly = true)
    public List<Restaurante> buscarPorCategoria(String categoria){
        return restauranteRepository.findByCategoriaIgnoreCase(categoria);
    }

    /**
     * Buscar restaurante por ID
     */
    @Transactional(readOnly = true)
    public Optional<Restaurante> buscarPorId(Long id){
        return restauranteRepository.findById(id);
    }

    /**
     * Ordem por avaliação
     */
    @Transactional(readOnly = true)
    public List<Restaurante> BuscarMelhoresAvaliados(){
        return restauranteRepository.findAllByOrderByAvaliacaoDesc();
    }

}

