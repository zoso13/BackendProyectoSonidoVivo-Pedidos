package cl.Proyecto.pedidos.service;

import cl.Proyecto.pedidos.exception.ResourceNotFoundException;
import cl.Proyecto.pedidos.model.Pedido;
import cl.Proyecto.pedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findAll(Sort.by(Sort.Direction.DESC, "fecha"));
    }

    public List<Pedido> obtenerPedidosEmail(String email) {
        return pedidoRepository.findByEmailOrderByFechaDesc(email);
    }

    public Pedido crearPedido(Pedido pedido) {
        pedido.setFecha(LocalDateTime.now());
        if (pedido.getEstado() == null || pedido.getEstado().isBlank()) {
            pedido.setEstado("pendiente de pago");
        }
        return pedidoRepository.save(pedido);
    }

    public Pedido actualizarEstado(Long id, String nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con ID: " + id));
        pedido.setEstado(nuevoEstado);
        return pedidoRepository.save(pedido);
    }
}