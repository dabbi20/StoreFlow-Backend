package com.storeflow.mapper;

import com.storeflow.dto.ProductRequestDto;
import com.storeflow.dto.ProductResponseDto;
import com.storeflow.entity.ProductEntity;

public class ProductMapper {

    public ProductResponseDto toDto(ProductEntity productEntity){
        return  new ProductResponseDto(productEntity.getId(),productEntity.getNombre(),productEntity.getPrecio(),productEntity.getStock(),productEntity.getFechaCreacion(),productEntity.getFechaActualizacion());

    }

    public ProductEntity toEntity(ProductRequestDto dto){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setNombre(dto.nombre());
        productEntity.setPrecio(dto.precio());
        productEntity.setStock(dto.stock());
        return productEntity;
    }
}
