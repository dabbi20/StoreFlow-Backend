package com.storeflow.repository;

import com.storeflow.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository
        extends JpaRepository<SaleEntity,Long>{
}
