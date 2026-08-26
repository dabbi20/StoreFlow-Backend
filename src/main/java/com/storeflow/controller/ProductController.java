package com.storeflow.controller;

import com.storeflow.dto.ProductRequestDto;
import com.storeflow.dto.ProductResponseDto;
import com.storeflow.entity.ProductEntity;
import com.storeflow.mapper.ProductMapper;
import com.storeflow.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductMapper productMapper;
    private final ProductService productService;

    public ProductController(ProductMapper productMapper, ProductService productService) {
        this.productMapper = productMapper;
        this.productService = productService;

    }


    @GetMapping
    List<ProductResponseDto> all() {
        List<ProductEntity> entidades = productService.listarProductos();
        List<ProductResponseDto> respuestas = new ArrayList<>();

        for (ProductEntity entidad : entidades) {
            respuestas.add(productMapper.toDto(entidad));
        }

        return respuestas;
    }

    @GetMapping("/{idProducto}")
    ProductResponseDto buscarPorId(@PathVariable Long idProducto) {
        ProductEntity product = productService.buscarPorId(idProducto);
        return productMapper.toDto(product);
    }


    @PostMapping

    public ResponseEntity<ProductResponseDto>crearProducto(@Valid @RequestBody ProductRequestDto productRequestDto) {


        ProductEntity product = productMapper.toEntity(productRequestDto);




        ProductEntity productoGuardado = productService.registrarProducto(product);
        ProductResponseDto dto = productMapper.toDto(productoGuardado);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dto);

    }

    @PutMapping("/{idProducto}")
    ProductResponseDto actualizarProducto(@PathVariable Long idProducto,@Valid @RequestBody ProductRequestDto productRequestDto){
ProductEntity producto = productMapper.toEntity(productRequestDto);

ProductEntity productoActualizado = productService.actualizarProducto(idProducto,producto);
ProductResponseDto dtoActualizar = productMapper.toDto(productoActualizado);


return  dtoActualizar;
    }


    @DeleteMapping("/{idProducto}")
    ResponseEntity<Void> borrarProducto(@PathVariable Long idProducto){
productService.eliminarProducto(idProducto);

return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .build();
    }

}
