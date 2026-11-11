package cl.Proyecto.pedidos.controller;

import cl.Proyecto.pedidos.model.Pedido;
import cl.Proyecto.pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/usuario")
    public ResponseEntity<List<Pedido>> obtenerMisPedidos(@RequestParam String email){
        List<Pedido> pedidos = pedidoService.obtenerPedidosEmail(email);
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping
    public ResponseEntity<Pedido> registrarPedido(@RequestBody Pedido pedido){
        Pedido nuevoPedido = pedidoService.crearPedido(pedido);
        return ResponseEntity.ok(nuevoPedido);
    }
}
