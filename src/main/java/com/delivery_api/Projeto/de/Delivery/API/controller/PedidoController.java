package com.delivery_api.Projeto.de.Delivery.API.controller;

import com.delivery_api.Projeto.de.Delivery.API.entity.Pedido;
import com.delivery_api.Projeto.de.Delivery.API.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    /**
     * CRIAR NOVO PEDIDO
     */
    @PostMapping
    public ResponseEntity<?> criarPedido(@RequestBody Pedido pedido) {
        try {
            Pedido pedidoCriado = pedidoService.criarPedido(pedido);
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCriado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor");
        }
    }

    /**
     * BUSCAR PEDIDO POR ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Optional<Pedido> pedido = pedidoService.buscarPorId(id);
            return pedido.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor");
        }
    }

    /**
     * BUSCAR PEDIDOS POR CLIENTE
     */
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> buscarPorCliente(@PathVariable Long clienteId) {
        try {
            List<Pedido> pedidos = pedidoService.buscarPorCliente(clienteId);
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * BUSCAR PEDIDOS POR RESTAURANTE
     */
    @GetMapping("/restaurante/{restauranteId}")
    public ResponseEntity<List<Pedido>> buscarPorRestaurante(@PathVariable Long restauranteId) {
        try {
            List<Pedido> pedidos = pedidoService.buscarPorRestaurante(restauranteId);
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * BUSCAR PEDIDO POR NÚMERO
     */
    @GetMapping("/numero/{numeroPedido}")
    public ResponseEntity<?> buscarPorNumero(@PathVariable String numeroPedido) {
        try {
            Pedido pedido = pedidoService.buscarPorNumero(numeroPedido);
            if (pedido != null) {
                return ResponseEntity.ok(pedido);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor");
        }
    }

    /**
     * ATUALIZAR STATUS DO PEDIDO
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> atualizarStatus(@PathVariable Long id, @RequestBody StatusRequest statusRequest) {
        try {
            Pedido pedidoAtualizado = pedidoService.atualizarStatus(id, statusRequest.getStatus());
            return ResponseEntity.ok(pedidoAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor");
        }
    }

    /**
     * CANCELAR PEDIDO
     */
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarPedido(@PathVariable Long id) {
        try {
            Pedido pedidoCancelado = pedidoService.cancelarPedido(id);
            return ResponseEntity.ok(pedidoCancelado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor");
        }
    }

    /**
     * CLASSE AUXILIAR PARA RECEBER STATUS
     */
    public static class StatusRequest {
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}