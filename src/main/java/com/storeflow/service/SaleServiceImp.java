package com.storeflow.service;

import com.storeflow.entity.ProductEntity;
import com.storeflow.entity.SaleEntity;
import com.storeflow.exception.InsufficientStockException;
import com.storeflow.exception.InvalidQuantityException;
import com.storeflow.exception.ProductNotFoundException;
import com.storeflow.exception.SaleNotFoundException;
import com.storeflow.repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class SaleServiceImp implements SaleService{

  private final ProductService productService;
  private final SaleRepository saleRepository;


    public SaleServiceImp(ProductService productService,SaleRepository saleRepository
                         ){
      this.productService = productService;
      this.saleRepository = saleRepository;

    }

    @Transactional
    @Override
    public SaleEntity realizarVenta(Long productoId, int cantidad) {
        ProductEntity productEntity = productService.buscarPorId(productoId);
        //VALIDAR CANTIDAD
if (cantidad <= 0){
    throw new InvalidQuantityException("La cantidad debe ser mayor a 0");
}
        //VALIDAR STOCK
if (cantidad > productEntity.getStock()){
    throw new InsufficientStockException("El stock no cubre con todas las cantidades que el usuario requiere solo tenemos estas cantidades disponibles: " + productEntity.getStock());
}
        //OBTENER PRECIO DEL PRODUCTO
        BigDecimal precio = productEntity.getPrecio();
        //CALCULAR TOTAL
BigDecimal total = precio.multiply(BigDecimal.valueOf(cantidad));
        //DESONTAR STOCK
        productEntity.setStock(productEntity.getStock() - cantidad);

        //ACTUALIZAR PRODUCTO
        productService.actualizarProducto(productoId,productEntity);

        //CREAR VENTA



        SaleEntity venta = new SaleEntity(cantidad,precio,total,productEntity);
       return saleRepository.save(venta);

    }




    @Override
    public List<SaleEntity> listarVentas() {


        return saleRepository.findAll();
    }

    @Override
    public SaleEntity buscarVentaId(Long idVenta) {
        return saleRepository.findById(idVenta).orElseThrow(()-> new SaleNotFoundException("Venta no encontrada: " + idVenta));
    }
}
