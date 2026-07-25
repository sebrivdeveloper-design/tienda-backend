package com.tienda.backend.controller;

import com.tienda.backend.dto.CostoProductoRequestDTO;
import com.tienda.backend.entity.CostoProducto;
import com.tienda.backend.service.CostoProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/costos-productos")
public class CostoProductoController {

    private final CostoProductoService costoProductoService;

    public CostoProductoController(CostoProductoService costoProductoService) {
        this.costoProductoService = costoProductoService;
    }

    @PostMapping
    public CostoProducto registrarCostoProducto(@RequestBody CostoProductoRequestDTO dto) {
        return costoProductoService.registrarCostoProducto(dto);
    }

    @GetMapping
    public List<CostoProducto> listarCostosProductos() {
        return costoProductoService.listarCostosProductos();
    }
}