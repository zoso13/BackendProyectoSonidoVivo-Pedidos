package cl.Proyecto.pedidos.controller;

import cl.Proyecto.pedidos.model.Pedido;
import cl.Proyecto.pedidos.service.PedidoService;
<<<<<<< HEAD
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> bf24468cbf419ace9f65bce3deda7d0b71516028
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
<<<<<<< HEAD
@RequiredArgsConstructor
@Tag(name = "Pedidos", description = "Operaciones para crear, consultar y actualizar pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    @Operation(summary = "Crear un nuevo pedido", description = "Registra un pedido con sus ítems para el cliente autenticado")
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.crearPedido(pedido));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('VENDEDOR', 'ADMIN')")
    @Operation(summary = "Listar todos los pedidos", description = "Disponible solo para VENDEDOR o ADMIN")
    public ResponseEntity<List<Pedido>> obtenerTodos() {
        return ResponseEntity.ok(pedidoService.obtenerTodos());
    }

    @GetMapping("/cliente/{email}")
    @Operation(summary = "Listar pedidos por cliente", description = "Obtiene el historial de pedidos asociado a un correo electrónico")
    public ResponseEntity<List<Pedido>> obtenerPedidosEmail(@PathVariable String email) {
        return ResponseEntity.ok(pedidoService.obtenerPedidosEmail(email));
    }

    @PutMapping("/{id}/estado")
    @PreAuthorize("hasAnyRole('VENDEDOR', 'ADMIN')")
    @Operation(summary = "Actualizar estado del pedido", description = "Permite cambiar el estado (pendiente de pago, en preparación, listo para retiro/despacho, entregado)")
    public ResponseEntity<Pedido> actualizarEstado(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(pedidoService.actualizarEstado(id, body.get("estado")));
=======
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
>>>>>>> bf24468cbf419ace9f65bce3deda7d0b71516028
    }
}