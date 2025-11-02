package com.delivery_api.Projeto.de.Delivery.API.service;

import com.delivery_api.Projeto.de.Delivery.API.entity.Pedido;
import com.delivery_api.Projeto.de.Delivery.API.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    /**
     * Criar pedido
     */
    public Pedido criarPedido(Pedido pedido) {
        validarPedido(pedido);
        
        pedido.setNumeroPedido(gerarNumeroPedido());
        
        pedido.setDataPedido(LocalDateTime.now());
        
        pedido.setStatus("PENDENTE");
        
        validarValorTotal(pedido.getValorTotal());
        
        return pedidoRepository.save(pedido);
    }
    
    
    /**
     *  buscar pedido id cliente
     */
    @Transactional(readOnly = true)
    public List<Pedido> buscarPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteIdOrderByDataPedidoDesc(clienteId);
    }
    
    /**
     * Atualizar status
     */
    public Pedido atualizarStatus(Long id, String novoStatus) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com ID: " + id));
        
        validarTransicaoStatus(pedido.getStatus(), novoStatus);
        
        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }
    
    
    
    private void validarPedido(Pedido pedido) {
        if (pedido.getClienteId() == null) {
            throw new IllegalArgumentException("Cliente é obrigatório");
        }
        
        if (pedido.getRestaurante() == null) {
            throw new IllegalArgumentException("Restaurante é obrigatório");
        }
        
        if (pedido.getValorTotal() == null) {
            throw new IllegalArgumentException("Valor total é obrigatório");
        }
        
        if (pedido.getValorTotal().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor total deve ser maior que zero");
        }
    }
    
    private void validarValorTotal(BigDecimal valorTotal) {
        if (valorTotal.compareTo(new BigDecimal("1000")) > 0) {
            throw new IllegalArgumentException("Valor total não pode ser superior a R$ 1000,00");
        }
    }
    
    private void validarTransicaoStatus(String statusAtual, String novoStatus) {
        if ("ENTREGUE".equals(statusAtual) || "CANCELADO".equals(statusAtual)) {
            throw new IllegalArgumentException("Pedido finalizado não pode ter status alterado");
        }
        
        if ("CANCELADO".equals(novoStatus) && !pedidoPodeSerCancelado(statusAtual)) {
            throw new IllegalArgumentException("Pedido não pode ser cancelado no status atual");
        }
    }
    
    private boolean pedidoPodeSerCancelado(String status) {
        return "PENDENTE".equals(status) || "CONFIRMADO".equals(status);
    }
    
    /**
     * Gerar número do pedido
     */
    private String gerarNumeroPedido() {
        String numero;
        do {
            numero = "PED-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        } while (pedidoRepository.findByNumeroPedido(numero) != null); // 
        
        return numero;
    }
}