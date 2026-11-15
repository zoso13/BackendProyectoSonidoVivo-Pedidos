package cl.Proyecto.pedidos.repository;

import cl.Proyecto.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByEmailOrderByFechaDesc(String email);

}
