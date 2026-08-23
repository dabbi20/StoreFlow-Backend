package com.storeflow.mapper;

import com.storeflow.dto.ProductRequestDto;
import com.storeflow.dto.SalesResponseDto;
import com.storeflow.entity.SaleEntity;

public class SaleMapper {

    public SalesResponseDto toDto(SaleEntity saleEntity) {
        return new SalesResponseDto(saleEntity.getId(), saleEntity.getProduct().getNombre(), saleEntity.getCantidad(),
                saleEntity.getPrecioUnitario(), saleEntity.getTotal(), saleEntity.getFechaVenta());
    }



}
