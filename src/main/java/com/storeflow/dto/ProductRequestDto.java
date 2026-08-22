package com.storeflow.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequestDto(@NotBlank
                         String nombre,
                                @NotNull
                         @Positive
                         BigDecimal precio,
                                @Min(0)
                         int stock) {


}
