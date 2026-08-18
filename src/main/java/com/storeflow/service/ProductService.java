package com.storeflow.service;

import com.storeflow.entity.ProductEntity;

import java.util.List;

public interface ProductService {

    ProductEntity registrarProducto(ProductEntity product);

    List<ProductEntity>listarProductos();

    ProductEntity buscarProductoPorNombre(String nombre);

    ProductEntity buscarPorId(Long idProducto);

    ProductEntity actualizarProducto(Long id,ProductEntity product);

    void eliminarProducto(Long idProducto);

}
