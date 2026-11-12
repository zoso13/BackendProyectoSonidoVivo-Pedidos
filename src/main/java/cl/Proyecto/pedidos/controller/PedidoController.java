package cl.Proyecto.pedidos.controller;

import cl.Proyecto.pedidos.model.Pedido;
import cl.Proyecto.pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Vendedor y Admin ven todos los pedidos de la tienda
    @GetMapping
    @PreAuthorize("hasAnyRole('VENDEDOR', 'ADMIN')")
    public ResponseEntity<List<Pedido>> obtenerTodosLosPedidos() {
        return ResponseEntity.ok(pedidoService.obtenerTodos());
    }

    // Cliente consulta sus propios pedidos por email
    @GetMapping("/usuario")
    public ResponseEntity<List<Pedido>> obtenerMisPedidos(@RequestParam String email) {
        return ResponseEntity.ok(pedidoService.obtenerPedidosEmail(email));
    }

    // Crear un pedido desde Checkout
    @PostMapping
    public ResponseEntity<Pedido> registrarPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.crearPedido(pedido));
    }

    // Vendedor o Admin cambian el estado del pedido (ej. pagado, enviado, entregado)
    @PatchMapping("/{id}/estado")
    @PreAuthorize("hasAnyRole('VENDEDOR', 'ADMIN')")
    public ResponseEntity<Pedido> actualizarEstado(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        String nuevoEstado = body.get("estado");
        return ResponseEntity.ok(pedidoService.actualizarEstado(id, nuevoEstado));
    }
}