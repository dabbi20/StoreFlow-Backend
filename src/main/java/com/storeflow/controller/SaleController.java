package com.storeflow.controller;

import com.storeflow.dto.SalesRequestDto;
import com.storeflow.dto.SalesResponseDto;
import com.storeflow.entity.SaleEntity;
import com.storeflow.mapper.SaleMapper;
import com.storeflow.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {
    private final SaleService saleService;
    private final SaleMapper saleMapper;


    public SaleController(SaleService service, SaleMapper saleMapper, SaleService saleService){

    this.saleMapper = saleMapper;
        this.saleService = saleService;
    }

@GetMapping
    List<SalesResponseDto>salesAll(){
    List<SaleEntity>entidades = saleService.listarVentas();
    List<SalesResponseDto> ventasRespuestas = new ArrayList<>();

    for (SaleEntity entidadVenta : entidades){
        ventasRespuestas.add(saleMapper.toDto(entidadVenta));
    }

    return ventasRespuestas;
}

@GetMapping("/{idVenta}")
    SalesResponseDto buscarSaleId(@PathVariable Long idVenta){
        SaleEntity venta = saleService.buscarVentaId(idVenta);

        return saleMapper.toDto(venta);

}

@PostMapping
    public ResponseEntity<SalesResponseDto>crearVenta(@Valid @RequestBody SalesRequestDto salesRequestDto){
    SaleEntity saleEntity = saleService.realizarVenta(salesRequestDto.productoId(),salesRequestDto.cantidad());

    SalesResponseDto dto = saleMapper.toDto(saleEntity);

return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(dto);

}



}
