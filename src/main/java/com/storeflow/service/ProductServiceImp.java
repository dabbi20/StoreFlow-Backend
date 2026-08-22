package com.storeflow.service;

import com.storeflow.entity.ProductEntity;
import com.storeflow.exception.ProductAlreadyExistsException;
import com.storeflow.exception.ProductNotFoundException;
import com.storeflow.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ProductEntity registrarProducto(ProductEntity product) {
   if (productRepository.existsByNombre(product.getNombre())){
       throw new ProductAlreadyExistsException("El producto ya existe: " + product.getNombre());
   }

   return productRepository.save(product);
    }

    @Override
    public List<ProductEntity> listarProductos() {
      return productRepository.findAll();
    }

    @Override
    public ProductEntity actualizarProducto(Long id, ProductEntity product) {
        ProductEntity productoExistente = buscarPorId(id);

   productRepository.findByNombre(product.getNombre()).ifPresent(valirdarId ->{
       if (!valirdarId.getId().equals(productoExistente.getId())){
           throw new ProductAlreadyExistsException("Ya existe un producto con el mismo nombre: " + product.getNombre());
       }
   });

   productoExistente.setNombre(product.getNombre());
   productoExistente.setStock(product.getStock());
   productoExistente.setPrecio(product.getPrecio());

   return productRepository.save(productoExistente);
    }

    @Override
    public ProductEntity buscarPorId(Long idProducto) {
        return productRepository.findById(idProducto).orElseThrow(()-> new ProductNotFoundException("Producto no encontrado con el ID: " + idProducto));
    }

    @Override
    public ProductEntity buscarProductoPorNombre(String nombre) {
       return productRepository.findByNombre(nombre).orElseThrow(()-> new ProductNotFoundException("Producto no encontrado con el nombre: " + nombre));
    }

    @Override
    public void eliminarProducto(Long idProducto) {
       productRepository.delete(buscarPorId(idProducto));

    }
}
