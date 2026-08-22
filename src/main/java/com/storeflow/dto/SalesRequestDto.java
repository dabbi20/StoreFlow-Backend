package com.storeflow.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SalesRequestDto(@NotNull Long productoId,
                              @Positive int cantidad) {
}
