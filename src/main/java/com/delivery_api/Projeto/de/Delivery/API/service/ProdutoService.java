package com.delivery_api.Projeto.de.Delivery.API.service;

import com.delivery_api.Projeto.de.Delivery.API.entity.Produto;
import com.delivery_api.Projeto.de.Delivery.API.entity.Restaurante;
import com.delivery_api.Projeto.de.Delivery.API.repository.ProdutoRepository;
import com.delivery_api.Projeto.de.Delivery.API.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private RestauranteRepository restauranteRepository;
    
    // Cadastrar produto
    public Produto cadastrar(Produto produto) {
        // Validações
        validarRestaurante(produto.getRestaurante());
        validarPreco(produto.getPreco());
        configurarDisponibilidade(produto);
        validarDadosBasicos(produto);
        
        return produtoRepository.save(produto);
    }
    
    // Buscar por ID
    @Transactional(readOnly = true)
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }
    
    // Listar produtos
    @Transactional(readOnly = true)
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }
    
    // Buscar produtos por restaurante
    @Transactional(readOnly = true)
    public List<Produto> buscarPorRestaurante(Long restauranteId) {
        return produtoRepository.findByRestauranteId(restauranteId);
    }
    
    // Atualizar produto
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto produto = buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com ID: " + id));
        
        // Atualizar campos
        produto.setNome(produtoAtualizado.getNome());
        produto.setDescricao(produtoAtualizado.getDescricao());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setCategoria(produtoAtualizado.getCategoria());
        produto.setDisponivel(produtoAtualizado.getDisponivel());
        
        return produtoRepository.save(produto);
    }
    
    // Deletar
    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new IllegalArgumentException("Produto não encontrado com ID: " + id);
        }
        produtoRepository.deleteById(id);
    }
    
    // Validar restaurante
    private void validarRestaurante(Restaurante restaurante) {
        if (restaurante == null) {
            throw new IllegalArgumentException("Produto deve estar vinculado a um restaurante.");
        }
        
        Optional<Restaurante> restauranteExistente = restauranteRepository.findById(restaurante.getId());
        if (restauranteExistente.isEmpty()) {
            throw new IllegalArgumentException("Restaurante não encontrado com ID: " + restaurante.getId());
        }
        
        if (!restauranteExistente.get().getAtivo()) {
            throw new IllegalArgumentException("Não é possível cadastrar produto em restaurante inativo.");
        }
    }
    
    // validar preço
    private void validarPreco(Double preco) {
        if (preco == null) {
            throw new IllegalArgumentException("Preço do produto é obrigatório.");
        }
        
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço do produto deve ser maior que zero.");
        }
        
        if (preco > 1000) {
            throw new IllegalArgumentException("Preço do produto não pode ser superior a R$ 1000,00.");
        }
    }
    
    // Configurar disponibilidade
    private void configurarDisponibilidade(Produto produto) {
        if (produto.getDisponivel() == null) {
            produto.setDisponivel(true);
        }
    }
    
    // Validar dados basicos
    private void validarDadosBasicos(Produto produto) {
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório.");
        }
        
        if (produto.getNome().length() < 2) {
            throw new IllegalArgumentException("Nome do produto deve ter pelo menos 2 caracteres.");
        }
        
        if (produto.getCategoria() == null || produto.getCategoria().trim().isEmpty()) {
            throw new IllegalArgumentException("Categoria do produto é obrigatória.");
        }
    }
    //inativar produto
        public Produto inativar(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));

        if (!produto.getDisponivel()) {
            throw new IllegalArgumentException("Produto já está inativo: " + id);
        }

        produto.setDisponivel(false);
        return produtoRepository.save(produto);
    }
}