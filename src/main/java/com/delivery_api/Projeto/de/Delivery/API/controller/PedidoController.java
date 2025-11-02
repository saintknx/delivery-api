package com.delivery_api.Projeto.de.Delivery.API.controller;

import com.delivery_api.Projeto.de.Delivery.API.entity.Pedido;
import com.delivery_api.Projeto.de.Delivery.API.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    /**
     * Criar novo pedido
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
     * Atualizar status do pedido
     */
    @PatchMapping("/{pedidod}/status")
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