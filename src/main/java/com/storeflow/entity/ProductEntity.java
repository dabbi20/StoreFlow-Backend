package com.storeflow.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre",nullable = false,unique = true,length = 150)
    private String nombre;
    @Column(name = "precio",nullable = false,precision = 12, scale = 2)
    private BigDecimal precio;
    @Column(name = "stock",nullable = false)
    private int stock;
    @Column(name = "fecha_creacion",nullable = false,updatable = false)
    private LocalDateTime fechaCreacion;
    @Column(name = "fecha_actualizacion",nullable = false)
    private LocalDateTime fechaActualizacion;


    //CAllBACKS
    @PrePersist
    public void prePersist(){
        LocalDateTime now = LocalDateTime.now();
    if (this.fechaCreacion == null){
        this.fechaCreacion = now;
    }

    if (this.fechaActualizacion == null){
        this.fechaActualizacion = now;
    }
    }

    @PreUpdate
    public void preUpdate(){
        this.fechaActualizacion = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "product")
    private List<SaleEntity> sales = new ArrayList<>();

    //CONSTRUCTOR VACIO

public ProductEntity(){}

    //CONSTRUCTOR CON PARAMETROS
    public ProductEntity(String nombre, BigDecimal precio, int stock) {
        setNombre(nombre);
       setPrecio(precio);
      setStock(stock);
    }

    //GETTERS

    public String getNombre() {
        return nombre;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public List<SaleEntity> getSales() {
        return sales;
    }

    //SETTERS


    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        this.nombre = nombre;
    }

    public void setPrecio(BigDecimal precio) {
    if (precio == null || precio.compareTo(BigDecimal.ZERO)< 0){
        throw  new IllegalArgumentException("El precio debe ser mayor a 0");
    }

    this.precio = precio;
    }


    public void setStock(int stock) {
        if (stock < 0){
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        this.stock = stock;
    }

    public void setSales(List<SaleEntity> sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}




