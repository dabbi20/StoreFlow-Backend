package com.storeflow.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponseDto(
        Long id,
        String nombre,
        BigDecimal precio,
        int stock,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaActualizacion
) {}