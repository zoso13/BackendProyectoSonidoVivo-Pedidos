package cl.Proyecto.pedidos.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDetalle {
    private LocalDateTime marcaTiempo;
    private String mensaje;
    private String detalles;
}
