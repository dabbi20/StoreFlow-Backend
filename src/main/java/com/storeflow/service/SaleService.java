package com.storeflow.service;

import com.storeflow.entity.SaleEntity;

import java.util.List;

public interface SaleService {
    SaleEntity realizarVenta(Long productoId,int cantidad);
    List<SaleEntity>listarVentas();
    SaleEntity buscarVentaId(Long idVenta);
}
