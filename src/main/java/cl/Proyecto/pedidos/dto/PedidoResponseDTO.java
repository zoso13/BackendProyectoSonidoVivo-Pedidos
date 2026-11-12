package cl.Proyecto.pedidos.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoResponseDTO {
    private Long id;
    private String email;
    private LocalDateTime fecha;
    private String entrega;
    private String metodoPago;
    private String direccion;
    private Double total;
    private String estado;
    private List<PedidoRequestDTO> productos;
}
