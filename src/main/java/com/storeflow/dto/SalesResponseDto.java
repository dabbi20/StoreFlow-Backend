package com.storeflow.dto;



import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SalesResponseDto(
        Long id,
        String nombreProducto,
        int cantidad,
        BigDecimal precioUnitario,
        BigDecimal total,
        LocalDateTime fechaVenta
) {}