package com.storeflow.repository;

import com.storeflow.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface ProductRepository
        extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}