package com.carloscastillodeveloper.tienda.producto.controlador;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
public class MensajeError {
    private String codigo;
    private List<Map<String, String>> mensajes;
}
