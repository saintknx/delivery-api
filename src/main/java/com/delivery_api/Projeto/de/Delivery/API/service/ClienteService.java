package com.delivery_api.Projeto.de.Delivery.API.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery_api.Projeto.de.Delivery.API.dto.ClienteRequestDTO;
import com.delivery_api.Projeto.de.Delivery.API.dto.ClienteResponseDTO;
import com.delivery_api.Projeto.de.Delivery.API.entity.Cliente;
import com.delivery_api.Projeto.de.Delivery.API.repository.ClienteRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Cadastrar novo cliente
     */
    public ClienteResponseDTO cadastrar(ClienteRequestDTO dto){
        //Validar email único
        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado: " + dto.getEmail());
        }

        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());


        //Definir como ativo por padrão
        cliente.setAtivo(true);
        cliente.setDataCadastro(LocalDateTime.now());
        clienteRepository.save(cliente);



        return new ClienteResponseDTO(clienteRepository.save(cliente));
    }

    /**
     * Buscar cliente por ID
     */
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorId(Long id){
        return clienteRepository.findById(id);
    }

    /**
     * Buscar cliente por email
     */
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorEmail(String email){
        return clienteRepository.findByEmail(email);
    }

    /**
     * Listar todos os clientes ativos
     */
    @Transactional(readOnly = true)
    public List<Cliente> listarAtivos(){
        return clienteRepository.findByAtivoTrue();
    }

    /**
     * Atualizar dados do cliente
     */

    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Cliente cliente = buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + id));

        // Verificar se email não esta sendo usado por outro cliente
        if (!cliente.getEmail().equals(clienteAtualizado.getEmail()) &&
                clienteRepository.existsByEmail(clienteAtualizado.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado: " + clienteAtualizado.getEmail());
        }
    
        // Atualizar campos
        cliente.setNome(clienteAtualizado.getNome());
        cliente.setEmail(clienteAtualizado.getEmail());
        cliente.setTelefone(clienteAtualizado.getTelefone());
        cliente.setEndereco(clienteAtualizado.getEndereco());

        return clienteRepository.save(cliente);
    }

    /**
     * Inativar cliente (soft delete)
     */
    public void inativar(Long id) {
        Cliente cliente = buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + id));

        cliente.inativar();
        clienteRepository.save(cliente);
    }

    /**
     * Buscar clientes por nome
     */
    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome);
    }

    /**
     * validações de negócio
     */

    // private void validarDadosCliente(ClienteRequestDTO cliente) {
    //     if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
    //         throw new IllegalArgumentException("Nome do cliente é obrigatório.");
    //     }
        
    //     if (cliente.getEmail() == null || cliente.getEmail().trim().isEmpty()) {
    //         throw new IllegalArgumentException("Email do cliente é obrigatório.");
    //     }

    //     if (cliente.getNome().length() < 2){
    //         throw new IllegalArgumentException("Nome do cliente deve ter pelo menos 2 caracteres.");
    //     }
    // }
}