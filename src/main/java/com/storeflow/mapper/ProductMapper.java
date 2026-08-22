package com.storeflow.mapper;

import com.storeflow.dto.ProductRequestDto;
import com.storeflow.entity.ProductEntity;

public class ProductMapper {

    public ProductRequestDto toDto(ProductEntity productEntity){
        return  new ProductRequestDto(productEntity.getNombre(),productEntity.getPrecio(),productEntity.getStock());

    }

    public ProductEntity toEntity(ProductRequestDto dto){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setNombre(dto.nombre());
        productEntity.setPrecio(dto.precio());
        productEntity.setStock(dto.stock());
        return productEntity;
    }
}
