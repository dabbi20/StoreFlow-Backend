package com.storeflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "cantidad",nullable = false)
    private int cantidad;
    @Column(name = "precio_unitario",nullable = false,precision = 12, scale = 2)
    private BigDecimal precioUnitario;
    @Column(name = "total",nullable = false,precision = 12, scale = 2)
    private BigDecimal total;
    @Column(name = "fecha_venta",nullable = false)
    private LocalDateTime fechaVenta;

    //CAllBACKS

    @PrePersist
    public  void prePersist(){
        LocalDateTime now = LocalDateTime.now();
        if (this.fechaVenta == null){
            this.fechaVenta = now;
        }
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private ProductEntity product;

//CONSTRUCTOR VACIO

    public SaleEntity(){}

    //CONSTRUCTOR CON PARAMETROS



    public SaleEntity(
            int cantidad,
            BigDecimal precioUnitario,
            BigDecimal total,
            ProductEntity product
    ) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
        this.product = product;
    }

    //GETTERS


    public Long getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public ProductEntity getProduct() {
        return product;
    }

    //SETTERS



    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }



    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
