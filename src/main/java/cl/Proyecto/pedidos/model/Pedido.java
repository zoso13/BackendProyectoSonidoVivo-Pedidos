package cl.Proyecto.pedidos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private LocalDateTime fecha;
    private String entrega;

    @Column(name = "metodo_pago")
    private String metodoPago;

    private String direccion;
    private Double total;
    private String estado;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private List<PedidoItem> productos;
}