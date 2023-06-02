package com.lazypostman.authentication.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExceptionRespuesta {
    private LocalDateTime fecha;
    private String mensaje;
    private String detalle;
}
